package cn.bluedog2333.blueorginbackinit.service.impl;

import cn.bluedog2333.blueorginbackinit.model.pojo.Info;
import cn.bluedog2333.blueorginbackinit.model.pojo.Template;
import cn.bluedog2333.blueorginbackinit.service.OpenAIService;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.bluedog2333.blueorginbackinit.model.pojo.StoreUser;
import cn.bluedog2333.blueorginbackinit.service.StoreUserService;
import cn.bluedog2333.blueorginbackinit.mapper.StoreuserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author BlueDog
* @description 针对表【storeuser】的数据库操作Service实现
* @createDate 2024-04-24 14:35:22
*/
@Service
public class StoreUserServiceImpl extends ServiceImpl<StoreuserMapper, StoreUser>
    implements StoreUserService {
    @Resource
    OpenAIService openAIService;
    @Resource
    ContextUtil contextUtil;
    @Resource
    StoreUserService storeUserService;
    @Autowired
    private StoreuserMapper storeuserMapper;
    @Autowired
    private TemplateServiceImpl templateServiceImpl;


    @Override
    public String uploadInfo(Info info) {
        String response=openAIService.mixedPicture(info);
        StoreUser user=contextUtil.getStoreUser();
        user.getPhotoData().add(info);
        storeuserMapper.uploadPhoto(user);
        return response;
    }
    @Override
    public void useTemplate(Integer templateId){
        Template template=templateServiceImpl.getById(templateId);
        final StoreUser storeUser = contextUtil.getStoreUser();
        storeUser.setHtml(template.getHtml());
        var json=openAIService.miexedJson(template.getJson());
        storeUser.setJson(json);
    }
}




