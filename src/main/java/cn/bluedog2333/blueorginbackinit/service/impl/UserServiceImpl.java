package cn.bluedog2333.blueorginbackinit.service.impl;

import cn.bluedog2333.blueorginbackinit.common.errors.CustomException;
import cn.bluedog2333.blueorginbackinit.mapper.StoreuserMapper;
import cn.bluedog2333.blueorginbackinit.model.dto.login.LoginDTO;
import cn.bluedog2333.blueorginbackinit.model.dto.login.RegisterDTO;
import cn.bluedog2333.blueorginbackinit.model.pojo.StoreUser;
import cn.bluedog2333.blueorginbackinit.properties.FileStoreProperties;
import cn.bluedog2333.blueorginbackinit.properties.JwtProperties;
import cn.bluedog2333.blueorginbackinit.service.StoreUserService;
import cn.bluedog2333.blueorginbackinit.utils.staticutils.JwtTokenUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import cn.bluedog2333.blueorginbackinit.service.UserService;
import cn.bluedog2333.blueorginbackinit.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
* @author BlueDog
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-04-24 14:35:26
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    StoreuserMapper storeuserMapper;
    @Autowired
    private FileStoreProperties fileStoreProperties;

    public String register(RegisterDTO registerDTO){
        var userOptional=getOneOpt(new QueryWrapper<User>().eq("username",registerDTO.getNickname()));
        userOptional.ifPresentOrElse((user)->{
            throw new CustomException("已经有一个用户存在");
        },()->{
            save(User.builder().username(registerDTO.getNickname()).password(MD5.create().digestHex(registerDTO.getPassword())).build());
            StoreUser build = StoreUser.builder().username(registerDTO.getNickname()).build();
            storeuserMapper.insert(build);
            // 在指定路径下创建新文件
            File newFile = FileUtil.touch(new File(fileStoreProperties.getHtmlStorePath()+"user/"+registerDTO.getNickname()+".html"));
            File newFile2 = FileUtil.touch(new File(fileStoreProperties.getHtmlStorePath()+"user/"+registerDTO.getNickname()+".json"));
        });

        var user=getOne(new QueryWrapper<User>().eq("username",registerDTO.getNickname()));
        String token=JwtTokenUtil.getToken(user.getId(),registerDTO.getNickname(),user.getPassword());
        return token;

    }
    public String login(LoginDTO loginDTO){
        var userOptional=getOneOpt(new QueryWrapper<User>().eq("username",loginDTO.getNickname()));
        userOptional.ifPresentOrElse(user -> {

        },()->{
            throw new CustomException("未找到该用户");
        });
        var user=getOne(new QueryWrapper<User>().eq("username",loginDTO.getNickname()));
        String token=JwtTokenUtil.getToken(user.getId(),loginDTO.getNickname(),user.getPassword());
        return token;
    }
}




