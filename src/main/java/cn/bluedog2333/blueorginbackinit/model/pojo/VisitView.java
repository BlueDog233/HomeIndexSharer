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
    String html;
    String data; //组装json返回
}
