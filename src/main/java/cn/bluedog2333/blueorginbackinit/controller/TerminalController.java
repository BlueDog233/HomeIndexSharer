package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.common.Result;
import cn.bluedog2333.blueorginbackinit.model.dto.AiRequestDTO;
import cn.bluedog2333.blueorginbackinit.service.OpenAIService;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/terminal")
@RestController
public class TerminalController {
    private final OpenAIService openAIService;

    public TerminalController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/chat")
    public JSONObject chat(@RequestBody AiRequestDTO aiRequestDTO){
        String str= openAIService.command(JSONUtil.toJsonStr(aiRequestDTO.getMessage()),aiRequestDTO.getMessage());
        return JSONUtil.parseObj(str);
    }
}
