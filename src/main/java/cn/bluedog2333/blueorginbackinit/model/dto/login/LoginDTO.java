package cn.bluedog2333.blueorginbackinit.model.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String nickname;
    private String password;
    private String verifyCode;
}
