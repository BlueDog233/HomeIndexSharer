package cn.bluedog2333.blueorginbackinit.aop;


import cn.bluedog2333.blueorginbackinit.annotation.NeedPerm;
import cn.bluedog2333.blueorginbackinit.common.errors.CommonError;
import cn.bluedog2333.blueorginbackinit.common.errors.CustomException;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthAOP {
    @Autowired
    private ContextUtil contextUtil;
    @Around("@annotation(needPerm)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, NeedPerm needPerm) throws Throwable {
        int mustRole = needPerm.value().getI();
        int userRole=contextUtil.getUser().getRole();
        if(mustRole>userRole){
            throw new CustomException(CommonError.NO_PERMISSION.toString());
        }
        return joinPoint.proceed();
    }
}
