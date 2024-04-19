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

    /**
     * 分页搜索
     * @param pagesearchDTO
     * @return
     */
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

    /**
     * 注册
     * @param dto
     * @return
     */
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
        user.setMail(dto.getEmail());
        user.setRole(1);
        user.setUsername(dto.getNickname());
        user.setPassword(MD5.create().digestHex(dto.getPassword()));
        save(user);
        String token= JwtTokenUtil.getToken(user.getId(),user.getUsername(),user.getPassword());
        return token;
    }

    /**
     * 登录
     * @param dto
     * @return
     */
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
            String token = JwtTokenUtil.getToken(user.getId(), user.getUsername(), user.getPassword());
            return token;
        }
        throw new CustomException("密码错误");
    }

    /**
     * 通过nickname 获得User
     * @param nickname
     * @return
     */
    @Override
    public User getUser(String nickname) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.eq("username",nickname);
        return getOne(queryWrapper,false);
    }

    @Override
    public User getUserByEmail(String email) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.eq("mail",email);
        return getOne(queryWrapper,false);
    }

    /**
     * 模糊查询有多少用户(根据用户名)
     * @param keyword
     * @return
     */
    @Override
    public long total(String keyword) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.like("username",keyword);
        return count(queryWrapper);
    }

    /**
     * 给用户发送图片验证码
     * @param nickname
     * @return 图片url
     * @throws IOException
     */
    @Override
    public String sendVerifyImg(String nickname) throws IOException {
        return verifyCodeUtil.sendVerifyImg(nickname);
    }

    /**
     * 发送验证码,如果传入的email字段不是@,尝试通过把字段当username进行查找
     * @param email
     */
    @Override
    public void sendVerifyCode(String email) {
        if(!email.contains("@")){
            final var user = getUser(email);
            if(user==null){
                throw new CustomException(CommonError.USERNAME_NOEXIST.toString());
            }else{
                email=user.getMail();
            }
        }
        verifyCodeUtil.sendVerifyCode(email);
    }

    @Override
    public boolean verify(String key, String code) {
        return verifyCodeUtil.verify(key,code);
    }

    /**
     * 修改密码
     * @param dto
     * @return
     */
    @Override
    public String modifyPassword(ChangePasswordDTO dto) {
        if(dto.getNewPassword().equals(dto.getNewPassword())){
            throw new CustomException("两次输入新密码不一致");
        }
        final var user = contextUtil.getUser();
        if(!dto.getVerifyCode().equals(verifyCodeUtil.getVerifyCode(user.getUsername()))){
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
        return JwtTokenUtil.getToken(user.getId(),user.getUsername(),user.getPassword());
    }

    /**
     * 找回密码
     * @param dto
     * @return
     */
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
        return JwtTokenUtil.getToken(user.getId(),user.getUsername(),user.getPassword());

    }

}
