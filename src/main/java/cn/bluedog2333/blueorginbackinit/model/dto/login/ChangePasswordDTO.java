package cn.bluedog2333.blueorginbackinit.model.dto.login;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordDTO {
    String oldPassword;
    String newPassword;
    String checkPassword;
    String verifyCode;
}
