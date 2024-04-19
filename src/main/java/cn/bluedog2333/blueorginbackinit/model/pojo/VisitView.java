package cn.bluedog2333.blueorginbackinit.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitView {
    // "template1" "custom"
    String template;
    //若是custom
    String html; //若为custom 则有这个字段
    String data; //组装json返回
}
