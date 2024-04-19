package cn.bluedog2333.blueorginbackinit.service;

import cn.bluedog2333.blueorginbackinit.model.dto.login.*;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;

public interface UserService extends IService<User> {
    List<User> pageSearch(PagesearchDTO pagesearchDTO);

    /**
     * > String register(DTO dto)
     * >
     * > String login(DTO dto)
     */
    String register(RegisterDTO dto);
    String login(LoginDTO dto);
    User getUser(String nickname);

    User getUserByEmail(String email);

    long total(String keyword);

    String sendVerifyImg(String nickname) throws IOException;

    void sendVerifyCode(String email);
    boolean verify(String key,String code);
    String modifyPassword(ChangePasswordDTO dto);

    String refindPassword(RefindPasswordDTO dto);
}
