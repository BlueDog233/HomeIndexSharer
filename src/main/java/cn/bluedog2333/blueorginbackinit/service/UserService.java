package cn.bluedog2333.blueorginbackinit.service;

import cn.bluedog2333.blueorginbackinit.model.dto.login.LoginDTO;
import cn.bluedog2333.blueorginbackinit.model.dto.login.RegisterDTO;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author BlueDog
* @description 针对表【user】的数据库操作Service
* @createDate 2024-04-24 14:35:26
*/
public interface UserService extends IService<User> {

    String login(LoginDTO dto);

    String register(RegisterDTO dto);
}
