package cn.bluedog2333.blueorginbackinit.model.pojo;

import lombok.Data;

import java.util.List;

@Data
public class MessageContainer {
    private List<Message> messages;
    private String describe;
    private Integer status;  // 0 为空闲, 1 为繁忙
}
