package cn.bluedog2333.blueorginbackinit.model.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("users")
public class User {
    @TableId
    private Integer id;
    private String nickname;
    private String password;
    private String email;
    private int gender;
    private int isVaild;
    private int isDelete;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String imgUrl;
    private int role;
    private int spring;
}
