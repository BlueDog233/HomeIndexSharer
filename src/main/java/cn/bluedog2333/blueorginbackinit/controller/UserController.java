package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.common.Result;
import cn.bluedog2333.blueorginbackinit.common.errors.CommonError;
import cn.bluedog2333.blueorginbackinit.model.dto.login.LoginDTO;
import cn.bluedog2333.blueorginbackinit.model.dto.login.RegisterDTO;
import cn.bluedog2333.blueorginbackinit.model.pojo.Info;
import cn.bluedog2333.blueorginbackinit.model.pojo.StoreUser;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import cn.bluedog2333.blueorginbackinit.service.StoreUserService;
import cn.bluedog2333.blueorginbackinit.service.UserService;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.PojoUtil;
import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    UserService userService;
    @Resource
    StoreUserService storeUserService;
    @Autowired
    private ContextUtil contextUtil;
    @Autowired
    private PojoUtil pojoUtil;

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterDTO dto){
//        if (BeanUtil.hasNullField(dto)) {
//            return Result.error(CommonError.PARAMETER_ERROR.toString());
//        }

        String token=userService.register(dto);
        return Result.success(token);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO dto){
//        if (BeanUtil.hasNullField(dto)) {
//            return Result.error(CommonError.PARAMETER_ERROR.toString());
//        }
        return Result.success(userService.login(dto));
    }
    @PostMapping("/publish")
    public Result publishOrNo(){
        //todo 改变ispublished 为相反的状态 0->1 1->0
        return Result.success("");
    }
    @PostMapping("/upload")
    public Result refreshUser(@RequestBody StoreUser user) throws IllegalAccessException {
        pojoUtil.refreshUser(user);
        return Result.success("");
    }
    @PostMapping("/get")
    public Result<StoreUser> getUser(){
        StoreUser storeUser=contextUtil.getStoreUser();
        return Result.success(storeUser);
    }
//    @PostMapping("/info")
//    public Result<Info> uploadInfo(){
//
//
//    }
}
