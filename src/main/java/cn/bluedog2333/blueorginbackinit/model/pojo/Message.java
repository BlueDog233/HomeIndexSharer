package cn.bluedog2333.blueorginbackinit.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String from;  // "User" 或 "System"
    private String message;
    @Data
    @AllArgsConstructor
    class AIMessage extends Message {
        private Integer type;  // 消息类型，1. 执行成功 2. 执行失败 3. INFO 4. WARNING
    }
    @Data
    @AllArgsConstructor
    public class UserMessage extends Message {
    }
}
