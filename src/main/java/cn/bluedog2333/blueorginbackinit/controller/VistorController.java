package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.common.Result;
import cn.bluedog2333.blueorginbackinit.model.pojo.VisitView;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/visit")
public class VistorController {
    @PostMapping("/{id}")
    public Result<VisitView> visit(@PathVariable("id") Integer id){
        //todo 其他人访问页面逻辑
    }

}
