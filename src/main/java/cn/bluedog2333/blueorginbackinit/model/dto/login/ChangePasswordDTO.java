package cn.bluedog2333.blueorginbackinit.model.dto.login;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordDTO {
    String oldPassword; //旧密码
    String newPassword; //新密码
    String checkPassword; //重新输入新密码
    String verifyCode; //验证码
}
