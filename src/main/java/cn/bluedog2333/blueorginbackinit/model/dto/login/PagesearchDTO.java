package cn.bluedog2333.blueorginbackinit.model.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagesearchDTO {
    //关键词
    private String keyword;
    //页数
    private int pageNum;
    //每页数
    private int pageSize;
}
