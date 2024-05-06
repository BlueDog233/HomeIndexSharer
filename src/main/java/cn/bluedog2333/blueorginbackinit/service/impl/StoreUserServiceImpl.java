package cn.bluedog2333.blueorginbackinit.service.impl;

import cn.bluedog2333.blueorginbackinit.model.pojo.Info;
import cn.bluedog2333.blueorginbackinit.model.pojo.Template;
import cn.bluedog2333.blueorginbackinit.service.OpenAIService;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import cn.bluedog2333.blueorginbackinit.utils.staticutils.TextProcessor;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.bluedog2333.blueorginbackinit.model.pojo.StoreUser;
import cn.bluedog2333.blueorginbackinit.service.StoreUserService;
import cn.bluedog2333.blueorginbackinit.mapper.StoreuserMapper;
import jakarta.annotation.Resource;
import org.springframework.ai.openai.client.OpenAiClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.*;

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
    public String uploadInfo(@RequestBody Info info) {
        StoreUser user = contextUtil.getStoreUser();
        if (info.getUrl().isEmpty()) {
            //上传文字信息
            String newTD = user.getTextData() + "\n\n @"+info.getDescribe();
            user.setTextData(newTD);
            storeuserMapper.updatePojo(user);
            return newTD;
        } else {
            user.getPhotoData().add(info);
            storeuserMapper.uploadPhoto(user);
            return user.getTextData();
        }

    }

    @Override
    public String aiMixed() {
        StoreUser user = contextUtil.getStoreUser();
        String response = openAIService.mixedPicture();
        user.setTextData(response);
        storeuserMapper.updatePojo(user);
        return response;
    }

    ExecutorService executor = Executors.newFixedThreadPool(2); // 创建线程池

    @Override
    public void useTemplate(Integer templateId) {
        Template template = templateServiceImpl.getById(templateId);
        final StoreUser storeUser = contextUtil.getStoreUser();
        String HTML=template.getHtml();
        List<String> waitReplaces= TextProcessor.split(HTML);
        var index=1;
        for (String waitReplace : waitReplaces) {
            String modifiedHtml= openAIService.modiefyHtml(waitReplace);
            HTML=TextProcessor.replace(HTML,modifiedHtml,index);
            index++;
        }

        String mixedJson = openAIService.miexedJson(template.getJson());
        storeUser.setHtml(HTML);
        storeUser.setJson(mixedJson);
        shutdown();
    }

    public void shutdown() {
        executor.shutdown(); // 关闭线程池
    }


}




