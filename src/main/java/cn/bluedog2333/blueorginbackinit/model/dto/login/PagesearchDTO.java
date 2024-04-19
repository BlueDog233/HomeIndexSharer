package cn.bluedog2333.blueorginbackinit.model.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagesearchDTO {
    private String keyword;
    private int pageNum;
    private int pageSize;
}
