package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/template/{id}")
public class TemplateController {
    @PostMapping("/choose")
    public Result choose(@PathVariable("id") Integer id,String template){
        //todo 清空原先模板里的数据,并新增数据到新的模板表
        return Result.success("");
    }

    @PostMapping("/query")
    public Result<VisitView> query(@PathVariable("id") Integer id){
        //todo 组装json返回
        return Result.success(new VisitView());
    }

    @PostMapping("/modify")
    public Result modify(@PathVariable("id") Integer id, @RequestBody List<ModifyKV> modifyKVs) {
        //todo save
        return Result.success("");
    }

    @PostMapping("/customupload")
    public Result modify(@PathVariable("id")Integer id,String custom){
        //todo 判断是否为自定义上传并更改用户的html
        return Result.success("");
    }

}
