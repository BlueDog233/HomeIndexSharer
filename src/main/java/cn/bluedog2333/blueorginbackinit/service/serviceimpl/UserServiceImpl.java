package cn.bluedog2333.blueorginbackinit.service.serviceimpl;

import cn.bluedog2333.blueorginbackinit.common.errors.CommonError;
import cn.bluedog2333.blueorginbackinit.common.errors.CustomException;
import cn.bluedog2333.blueorginbackinit.mapper.UserMapper;
import cn.bluedog2333.blueorginbackinit.model.dto.login.*;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.VerifyCodeUtil;
import cn.bluedog2333.blueorginbackinit.utils.staticutils.JwtTokenUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements cn.bluedog2333.blueorginbackinit.service.UserService{
    @Autowired
    private VerifyCodeUtil verifyCodeUtil;
    @Autowired
    private ContextUtil contextUtil;

    @Override
    public List<User> pageSearch(PagesearchDTO pagesearchDTO) {
        if(pagesearchDTO==null){
            pagesearchDTO=new PagesearchDTO("",1, 20);
        }
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("nickname", pagesearchDTO.getKeyword());

        IPage<User> page=new Page<>(pagesearchDTO.getPageNum(), pagesearchDTO.getPageSize());
        List<User> list= getBaseMapper().selectList(page, queryWrapper);
        return list;

    }
    @Override
    public String register(RegisterDTO dto) {
        if(dto.getNickname().length()>32 || dto.getPassword().length()>32 || dto.getEmail().length()>32 || dto.getPassword().length()<6){
            throw new CustomException(CommonError.LENGTH_TO_LONG.toString());
        }
        if (!dto.getPassword().equals(dto.getCheckPassword())) {
            throw new CustomException(CommonError.PASSWORD_NOT_EQUAL.toString());
        }
        if (getUser(dto.getNickname()) != null) {
            throw new CustomException(CommonError.USERNAME_EXIST.toString());
        }
        if(!verifyCodeUtil.verify(dto.getEmail(),dto.getVerifyCode())){
            throw new CustomException(CommonError.INVALID_VERIFY_CODE.toString());
        }
        User user=new User();
        user.setEmail(dto.getEmail());
        user.setRole(1);
        user.setGender(0);
        user.setNickname(dto.getNickname());
        user.setPassword(MD5.create().digestHex(dto.getPassword()));
        save(user);
        String token= JwtTokenUtil.getToken(user.getId(),user.getNickname(),user.getPassword());
        return token;
    }

    @Override
    public String login(LoginDTO dto) {

        if(dto.getNickname().length()>16 || dto.getPassword().length()>16 ){
            throw new CustomException(CommonError.LENGTH_TO_LONG.toString());
        }
        if(!verifyCodeUtil.verify(dto.getNickname(),dto.getVerifyCode())){
            throw new CustomException(CommonError.INVALID_VERIFY_CODE.toString());
        }
        User user=getUser(dto.getNickname());
        if(MD5.create().digestHex(dto.getPassword()).equals(user.getPassword())) {
            String token = JwtTokenUtil.getToken(user.getId(), user.getNickname(), user.getPassword());
            return token;
        }
        throw new CustomException("密码错误");
    }

    @Override
    public User getUser(String nickname) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.eq("nickname",nickname);
        return getOne(queryWrapper,false);
    }@Override
    public User getUserByEmail(String email) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.eq("email",email);
        return getOne(queryWrapper,false);
    }


    @Override
    public long total(String keyword) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.like("nickname",keyword);
        return count(queryWrapper);
    }

    @Override
    public String sendVerifyImg(String nickname) throws IOException {
        return verifyCodeUtil.sendVerifyImg(nickname);
    }

    @Override
    public void sendVerifyCode(String email) {
        if(!email.contains("@")){
            final var user = getUser(email);
            if(user==null){
                throw new CustomException(CommonError.USERNAME_NOEXIST.toString());
            }else{
                email=user.getEmail();
            }
        }
        verifyCodeUtil.sendVerifyCode(email);
    }

    @Override
    public boolean verify(String key, String code) {
        return verifyCodeUtil.verify(key,code);
    }

    @Override
    public String modifyPassword(ChangePasswordDTO dto) {
        if(dto.getNewPassword().equals(dto.getNewPassword())){
            throw new CustomException("两次输入新密码不一致");
        }
        final var user = contextUtil.getUser();
        if(!dto.getVerifyCode().equals(verifyCodeUtil.getVerifyCode(user.getNickname()))){
            throw new CustomException("验证码错误");
        }
        if(dto.getNewPassword().length()<6){
            throw new CustomException("密码长度不能小于6位");
        }
        if(!MD5.create().digestHex16(dto.getOldPassword()).equals(user.getPassword())){
            throw new CustomException("密码错误");
        }
        user.setPassword(MD5.create().digestHex(dto.getNewPassword()));
        updateById(user);
        return JwtTokenUtil.getToken(user.getId(),user.getNickname(),user.getPassword());
    }

    @Override
    public String refindPassword(RefindPasswordDTO dto) {
        if(!dto.getPassword().equals(dto.getCheckPassword())){
            throw new CustomException("两次输入密码不一致");
        }
        if(dto.getPassword().length()<6){
            throw new CustomException("密码长度不能小于6位");
        }
        if(!verifyCodeUtil.verify(dto.getEmail(),dto.getVerifyCode())){
            throw new CustomException("验证码错误");
        }
        User user=contextUtil.getUser();
        user.setPassword(MD5.create().digestHex(dto.getPassword()));
        updateById(user);
        return JwtTokenUtil.getToken(user.getId(),user.getNickname(),user.getPassword());

    }

}
