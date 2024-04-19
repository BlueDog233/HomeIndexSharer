package cn.bluedog2333.blueorginbackinit.model.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String nickname;
    private String password;
    private String checkPassword;
    private String email;
    private String verifyCode;
}
