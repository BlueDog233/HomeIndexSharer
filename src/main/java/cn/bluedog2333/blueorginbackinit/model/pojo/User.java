package cn.bluedog2333.blueorginbackinit.model.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId
    private Integer id;
    private String username;
    private String password;
    private String mail;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String avatar;
    private String html;
    private int ispublished;
    private int isbaned;
    private int role;
}
