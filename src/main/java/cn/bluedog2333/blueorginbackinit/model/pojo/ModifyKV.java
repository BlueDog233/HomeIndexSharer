package cn.bluedog2333.blueorginbackinit.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ModifyKV {
    private String key;
    //为Json
    private String value;
}
