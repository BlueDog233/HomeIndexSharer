package cn.bluedog2333.blueorginbackinit.utils.beanutils;

import cn.bluedog2333.blueorginbackinit.mapper.StoreuserMapper;
import cn.bluedog2333.blueorginbackinit.model.pojo.StoreUser;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import cn.bluedog2333.blueorginbackinit.properties.FileStoreProperties;
import cn.bluedog2333.blueorginbackinit.service.StoreUserService;
import cn.bluedog2333.blueorginbackinit.service.UserService;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;


@Component
public class PojoUtil {
    @Resource
    ContextUtil contextUtil;
    @Resource
    StoreUserService storeUserService;
    @Resource
    UserService userService;
    @Autowired
    private StoreuserMapper storeuserMapper;

    public void refreshUser(StoreUser storeuserCache) throws IllegalAccessException {
        User user=contextUtil.getUser();
        QueryWrapper<StoreUser> queryWrapper=new QueryWrapper();
        String username=user.getUsername();//之前的username
        queryWrapper.eq("username",username);
        StoreUser storeUser=storeUserService.getOne(queryWrapper,true);
        //更新差异化数据
        for (Field declaredField : StoreUser.class.getDeclaredFields()) {
            declaredField.setAccessible(true);
            if(declaredField.get(storeuserCache)!=null){
                declaredField.set(storeUser,declaredField.get(storeuserCache));
            }
        }
        //如果昵称有更新,更新回user表
        if (!storeUser.getUsername().equals(username)) {
            File file1= new File(FileStoreProperties.storePath+"user/"+username+".html");
            File file2= new File(FileStoreProperties.storePath+"user/"+username+".json");
            FileUtil.rename(file1,storeUser.getUsername()+".html",true);
            FileUtil.rename(file2,storeUser.getUsername()+".json",true);
            user.setUsername(storeUser.getUsername());
            userService.saveOrUpdate(user);
        }
        //数据库保存
        storeuserMapper.updateById(storeUser);
    }

}
