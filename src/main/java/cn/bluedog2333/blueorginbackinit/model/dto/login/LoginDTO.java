package cn.bluedog2333.blueorginbackinit.model.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    //用户名
    private String nickname;
    //密码
    private String password;
    //验证码
    private String verifyCode;
}
