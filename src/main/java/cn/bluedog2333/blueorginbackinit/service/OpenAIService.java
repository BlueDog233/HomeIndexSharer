package cn.bluedog2333.blueorginbackinit.service;

import cn.bluedog2333.blueorginbackinit.model.pojo.Info;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.ai.openai.client.OpenAiClient;
import org.springframework.ai.prompt.Prompt;
import org.springframework.stereotype.Component;

@Component
public class OpenAIService {
    @Resource
    OpenAiClient openAiClient;
    @Resource
    ContextUtil contextUtil;
    public String mixedPicture(Info info){
        var str = ResourceUtil.readUtf8Str("template/mixedphototext.template");
        if(StrUtil.isBlankIfStr(info.getUrl())){
            str=str.replace("{info}","描述信息  "+info.getPersonality()+"   "+"   "+info.getDescribe());
        }else{
            str=str.replace("{info}","图片信息 "+info.getPersonality()+"   "+"   "+info.getDescribe());
        }
        var user=contextUtil.getStoreUser();
        str=str.replace("{textData}",user.getTextData()).replace("{style}",user.getStyle());
        return openAiClient.generate(str);
    }
    public String miexedJson(String templateJson){
        var str=ResourceUtil.readUtf8Str("template/mixedtojson.template");
        var user=contextUtil.getStoreUser();
        str=str.replace("{textData}",user.getTextData()).replace("{json}",templateJson).replace("{style}",user.getStyle());
        return openAiClient.generate(str);
    }
    public String command(String view,String message){
        var str=ResourceUtil.readUtf8Str("response_command.template");
        var user=contextUtil.getStoreUser();
        str=str.replace("{view}",view).replace("{userdata}",user.getTextData()).replace("{message}",message);
        return openAiClient.generate(str);
    }
    public String chat(String message,String view){
        var str=ResourceUtil.readUtf8Str("response_command.template");
        var user=contextUtil.getStoreUser();
        str=str.replace("{view}",view).replace("{userdata}",user.getTextData()).replace("{message}",message);
        return openAiClient.generate(str);
    }

}
