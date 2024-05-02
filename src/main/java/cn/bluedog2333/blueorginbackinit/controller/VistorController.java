package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.common.Result;
import cn.bluedog2333.blueorginbackinit.common.errors.CustomException;
import cn.bluedog2333.blueorginbackinit.model.pojo.StoreUser;
import cn.bluedog2333.blueorginbackinit.properties.FileStoreProperties;
import cn.bluedog2333.blueorginbackinit.service.StoreUserService;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.Charset;

@CrossOrigin
@RestController
@RequestMapping("/visit")
public class VistorController {
    @Resource
    StoreUserService storeUserService;
    @Autowired
    private FileStoreProperties fileStoreProperties;
    @Autowired
    private ContextUtil contextUtil;

    //获取Json信息
    @RequestMapping("/json/{username}")
    public String getJson(@PathVariable("username")String username){
        String str="";
        QueryWrapper<StoreUser> storeUserQueryWrapper=new QueryWrapper<>();
        storeUserQueryWrapper.eq("username",username);
        var user=storeUserService.getOne(storeUserQueryWrapper);
        if(user==null){
            return null;
        }
        str=user.getJson();
        return str;
    }
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = "text/html")
    public String visit(@PathVariable("username")String username){
        String str="";
        QueryWrapper<StoreUser> storeUserQueryWrapper=new QueryWrapper<>();
        storeUserQueryWrapper.eq("username",username);
        var user=storeUserService.getOne(storeUserQueryWrapper);
        if(user==null){
            str=FileUtil.readString(fileStoreProperties.getHtmlStorePath()+"base/404.html", Charset.defaultCharset());
            return str;
        }
        Integer isPublish = user.getIsPublish();
        try {
            if (contextUtil.getStoreUser().getUsername().equals(username)) {
                str= user.getHtml();
                return str;
            }
        }catch (Exception e){
            if(isPublish==0){
                str=FileUtil.readString(fileStoreProperties.getHtmlStorePath()+"base/404.html", Charset.defaultCharset());
            }else{
                str=user.getHtml();
            }
        }
        return str;
    }
}
