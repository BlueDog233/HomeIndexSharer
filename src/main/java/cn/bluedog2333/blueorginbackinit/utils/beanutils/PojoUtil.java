package cn.bluedog2333.blueorginbackinit.utils.beanutils;

import cn.bluedog2333.blueorginbackinit.model.pojo.StoreUser;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import cn.bluedog2333.blueorginbackinit.service.StoreUserService;
import cn.bluedog2333.blueorginbackinit.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;


@Component
public class PojoUtil {
    @Resource
    ContextUtil contextUtil;
    @Resource
    StoreUserService storeUserService;
    @Resource
    UserService userService;
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
            //todo username 用户名是否重复
            user.setUsername(storeUser.getUsername());
            userService.save(user);
        }
        //数据库保存
        storeUserService.saveOrUpdate(storeUser);
    }

}
