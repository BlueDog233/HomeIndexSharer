package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.common.Result;
import cn.bluedog2333.blueorginbackinit.common.errors.CommonError;
import cn.bluedog2333.blueorginbackinit.model.dto.login.LoginDTO;
import cn.bluedog2333.blueorginbackinit.model.dto.login.RegisterDTO;
import cn.bluedog2333.blueorginbackinit.model.pojo.StoreUser;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import cn.bluedog2333.blueorginbackinit.service.StoreUserService;
import cn.bluedog2333.blueorginbackinit.service.UserService;
import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    UserService userService;
    @Resource
    StoreUserService storeUserService;
    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterDTO dto){
        if (BeanUtil.hasNullField(dto)) {
            return Result.error(CommonError.PARAMETER_ERROR.toString());
        }
        String token=userService.register(dto);
        return Result.success(token);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO dto){
        if (BeanUtil.hasNullField(dto)) {
            return Result.error(CommonError.PARAMETER_ERROR.toString());
        }
        return Result.success(userService.login(dto));
    }
    @PostMapping("/publish")
    public Result publishOrNo(){
        //todo 改变ispublished 为相反的状态 0->1 1->0
        return Result.success("");
    }
    @PostMapping("/upload")
    public Result refreshUser(StoreUser user){
        //todo
        return Result.success("");
    }

}
