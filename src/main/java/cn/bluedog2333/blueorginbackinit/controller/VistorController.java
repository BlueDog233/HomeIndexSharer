package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.common.Result;
import cn.bluedog2333.blueorginbackinit.service.StoreUserService;
import cn.hutool.core.io.resource.ResourceUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/visit")
public class VistorController {
    @Resource
    StoreUserService storeUserService;
//    @PostMapping("/{id}")
//    public Result<VisitView> visit(@PathVariable("id") Integer id){
//        //todo 其他人访问页面逻辑
//        return Result.success(new VisitView());
//    }
    @PostMapping("/{username}")
    public Result<String> visit(@PathVariable("username")String username){
        String str= ResourceUtil.readUtf8Str("html/"+username+".html");
        return Result.success(str);
    }
}
