package cn.bluedog2333.blueorginbackinit.model.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefindPasswordDTO {
    String verifyCode;
    String password;
    String checkPassword;
    String email;//虽然是email,但是可以nickname
    String nickname;
}
