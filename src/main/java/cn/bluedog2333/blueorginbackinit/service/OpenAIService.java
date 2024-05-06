package cn.bluedog2333.blueorginbackinit.service;

import cn.bluedog2333.blueorginbackinit.model.pojo.Info;
import cn.bluedog2333.blueorginbackinit.properties.BaseProperties;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.ai.openai.client.OpenAiClient;
import org.springframework.ai.prompt.Prompt;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class OpenAIService implements InitializingBean {
    @Resource
    OpenAiClient openAiClient;
    @Resource
    ContextUtil contextUtil;
    @Autowired
    private BaseProperties baseProperties;

    public String mixedPicture(){
        var str = ResourceUtil.readUtf8Str("template/mixedphototext.template");
        var user=contextUtil.getStoreUser();
        StringJoiner infos=new StringJoiner("\n");
        for(Info info:user.getPhotoData()){
            infos.add(info.toString());
        }
        str=str.replace("{info}",infos.toString()).replace("{textData}", user.getTextData()==null?"该用户没有设置TextData,你可以根据这张图片生成textdata": user.getTextData()).replace("{style}",user.getStyle()==null?"该用户没有设置style":user.getStyle()).replace("{userdata}",user.toString());
        return openAiClient.generate(str);
    }
    public String miexedJson(String templateJson){
        var str=ResourceUtil.readUtf8Str("template/mixedtojson.template");
        var user=contextUtil.getStoreUser();
        str=str.replace("{textData}", user.getTextData()==null?"该用户没有设置TextData,你可以根据这张图片生成textdata": user.getUsername()).replace("{style}",user.getStyle()==null?"该用户没有设置style":user.getStyle()).replace("{json}",templateJson==null?"无需合成,输出空json":templateJson);
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
    public String modiefyHtml(String html){
        var str=ResourceUtil.readUtf8Str("template/modifyhtml.template");
        var user=contextUtil.getStoreUser();
        str=str.replace("{html}",html).replace("{base_url}", baseProperties.getUrl()+"api/visit/json/"+user.getUsername()).replace("{text_data}",user.getTextData());
        return openAiClient.generate(str);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        openAiClient.setModel("gpt-4-turbo-2024-04-09");
    }
}
