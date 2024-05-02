package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.annotation.NeedPerm;
import cn.bluedog2333.blueorginbackinit.common.Result;
import cn.bluedog2333.blueorginbackinit.common.enums.UserPermEnum;
import cn.bluedog2333.blueorginbackinit.common.errors.CommonError;
import cn.bluedog2333.blueorginbackinit.mapper.StoreuserMapper;
import cn.bluedog2333.blueorginbackinit.model.dto.login.LoginDTO;
import cn.bluedog2333.blueorginbackinit.model.dto.login.RegisterDTO;
import cn.bluedog2333.blueorginbackinit.model.pojo.Info;
import cn.bluedog2333.blueorginbackinit.model.pojo.StoreUser;
import cn.bluedog2333.blueorginbackinit.model.pojo.Template;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import cn.bluedog2333.blueorginbackinit.service.StoreUserService;
import cn.bluedog2333.blueorginbackinit.service.UserService;
import cn.bluedog2333.blueorginbackinit.service.impl.TemplateServiceImpl;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.PojoUtil;
import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.client.AiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    UserService userService;
    @Resource
    StoreUserService storeUserService;
    @Autowired
    private ContextUtil contextUtil;
    @Autowired
    private PojoUtil pojoUtil;
    @Autowired
    private StoreuserMapper storeuserMapper;
    @Autowired
    private TemplateServiceImpl templateServiceImpl;

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterDTO dto) {
//        if (BeanUtil.hasNullField(dto)) {
//            return Result.error(CommonError.PARAMETER_ERROR.toString());
//        }

        String token = userService.register(dto);
        return Result.success(token);
    }
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO dto) {
//        if (BeanUtil.hasNullField(dto)) {
//            return Result.error(CommonError.PARAMETER_ERROR.toString());
//        }
        return Result.success(userService.login(dto));
    }

    @NeedPerm(UserPermEnum.USER)
    @PostMapping("/publish")
    public Result publishOrNo() {
        var user = contextUtil.getStoreUser();
        user.setIsPublish(user.getIsPublish() == 1 ? 0 : 1);
        storeuserMapper.updatePojo(user);
        return Result.success("");
    }

    @NeedPerm(UserPermEnum.USER)
    @PostMapping("/upload")
    public Result refreshUser(@RequestBody StoreUser user) throws IllegalAccessException {
        pojoUtil.refreshUser(user);
        return Result.success("");
    }

    @NeedPerm(UserPermEnum.USER)
    @PostMapping("/get")
    public Result<StoreUser> getUser() {
        StoreUser storeUser = contextUtil.getStoreUser();
        return Result.success(storeUser);
    }

    @NeedPerm(UserPermEnum.USER)
    @PostMapping("/info")
    public Result<String> uploadInfo(@RequestBody Info info) {
        String result = storeUserService.uploadInfo(info);
        return Result.success(result);
    }

    @NeedPerm(UserPermEnum.USER)
    @PostMapping("/use{tid}")
    public Result useTemplate(@PathVariable("tid") Integer id) {
        storeUserService.useTemplate(id);
        return Result.success(null);
    }

    @PostMapping("/star")
    public Result star(Integer id) {
        var user=contextUtil.getStoreUser();
        if(user.getStars().contains(id)){
            user.getStars().remove(id);
        }else {
            user.getStars().add(id);
        }
        storeuserMapper.updatePojo(user);
        return Result.success(null);
    }

    @GetMapping("/stars")
    public Result<List<Template>> templateResult(){
        var starIds=contextUtil.getStoreUser().getStars();
        List<Template> ts=new ArrayList<>();
        for(int id:starIds){
            var top=templateServiceImpl.getOptById(id);
            top.ifPresent((e->ts.add(e)));
        }
        return Result.success(ts);
    }

}
