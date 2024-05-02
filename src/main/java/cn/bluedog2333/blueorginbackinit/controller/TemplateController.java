package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.common.Result;
import cn.bluedog2333.blueorginbackinit.model.dto.TemplateUploadDTO;
import cn.bluedog2333.blueorginbackinit.model.pojo.Info;
import cn.bluedog2333.blueorginbackinit.model.pojo.Template;
import cn.bluedog2333.blueorginbackinit.properties.FileStoreProperties;
import cn.bluedog2333.blueorginbackinit.service.OpenAIService;
import cn.bluedog2333.blueorginbackinit.service.impl.TemplateServiceImpl;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import cn.bluedog2333.blueorginbackinit.utils.staticutils.OSSUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/template")
public class TemplateController {
    @Resource
    ContextUtil contextUtil;
    @Resource
    FileStoreProperties fileStoreProperties;
    @Resource
    TemplateServiceImpl templateServiceImpl;
    @Resource
    OpenAIService openAIService;


    @GetMapping("/queryall")
    public Result<List<Template>> list() {
        var user = contextUtil.getStoreUser();
        QueryWrapper<Template> templateWrapper = new QueryWrapper<>();
        templateWrapper.not(e -> {
            e.like("author", "_");
        }).or().eq("author", "_" + user.getUsername());
        List<Template> templates = new ArrayList<>();
        templates.addAll(templateServiceImpl.list(templateWrapper));
        return Result.success(templates);
    }

    @PostMapping("/upload")
    public Result upload(@RequestBody TemplateUploadDTO templateUploadDTO) {
        var user = contextUtil.getStoreUser();
        File file = new File(fileStoreProperties.getHtmlStorePath() + "/cache/" + user.getUsername() + ".zip");
        // 指定解压目标目录
        File targetDirectory = FileUtil.file(fileStoreProperties.getHtmlStorePath() + "/template/" + templateUploadDTO.getName());
        // 解压操作
        try {
            ZipUtil.unzip(file, targetDirectory);
            System.out.println("解压完成！");
        } catch (Exception e) {
            System.err.println("解压失败：" + e.getMessage());
        }
        File html = new File(fileStoreProperties.getHtmlStorePath() + "/template/" + templateUploadDTO.getName() + "/index.html");
        String htmlC = FileUtil.readString(html, Charset.defaultCharset());
        Template template = new Template();
        template.setAuthor(templateUploadDTO.isPrivate() ? "_" + user.getUsername() : user.getUsername());
        template.setName(templateUploadDTO.getName());
        template.setDescribe(templateUploadDTO.getDescribe());
        for (File fi : targetDirectory.listFiles()) {
            int index = 0;
            if (FileUtil.getSuffix(fi).equals("png") || FileUtil.getSuffix(fi).equals("jpg")) {
                String url = OSSUtil.upload(FileUtil.readBytes(fi), templateUploadDTO.getName() + index++ + ".jpg");
                Info info = new Info();
                info.setUrl(url);
                template.getPhoto().add(info);
            }
        }
        String modifyHtml = openAIService.modiefyHtml(htmlC);
        template.setHtml(modifyHtml);
        templateServiceImpl.save(template);

        return Result.success(null);
    }

//    @PostMapping("/query")
//    public Result<VisitView> query(@PathVariable("id") Integer id){
//        //todo 组装json返回
//        return Result.success(new VisitView());
//    }

//    @PostMapping("/modify")
//    public Result modify(@PathVariable("id") Integer id, @RequestBody List<ModifyKV> modifyKVs) {
//        //todo save
//        return Result.success("");
//    }

//    @PostMapping("/customupload")
//    public Result modify(@PathVariable("id")Integer id,String custom){
//        //todo 判断是否为自定义上传并更改用户的html
//        return Result.success("");
//    }

}
