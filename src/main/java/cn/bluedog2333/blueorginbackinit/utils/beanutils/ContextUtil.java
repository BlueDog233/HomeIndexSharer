package cn.bluedog2333.blueorginbackinit.utils.beanutils;


import cn.bluedog2333.blueorginbackinit.common.errors.CustomException;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import cn.bluedog2333.blueorginbackinit.service.UserService;
import cn.bluedog2333.blueorginbackinit.utils.staticutils.JwtTokenUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 上下文处理工具,用于获取 Bean以及 通过当前token获取User对象
 */
@Component
public class ContextUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Lazy
    @Autowired
    private UserService userService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public User getUser() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        try {
            System.out.println(JwtTokenUtil.getId(request.getHeader("token")));
            int id = JwtTokenUtil.getId(request.getHeader("token"));
            User user = userService.getById(id);
            if (ObjectUtil.isNull(user)) {
                throw new CustomException("id异常");
            }
            return user;

        } catch (Exception e) {
            throw new CustomException("token异常");

        }


    }

}
