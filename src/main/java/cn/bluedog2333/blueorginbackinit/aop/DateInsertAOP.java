package cn.bluedog2333.blueorginbackinit.aop;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.*;
import java.time.LocalDateTime;

@Aspect
@Component
public class DateInsertAOP {
    @Around("execution(* com.baomidou.mybatisplus.core.mapper.*.*(..)) ")
    public Object doInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        Method method=methodSignature.getMethod();
        if(method.getParameterCount() == 0){
            return joinPoint.proceed();
        }

        if(method.getParameters()[0].getName().equals("entity")){
            Object obj=joinPoint.getArgs()[0];
            Field updatetime= obj.getClass().getDeclaredField("updateTime");
            Field createtime= obj.getClass().getDeclaredField("createTime");
            updatetime.setAccessible(true);
            updatetime.set(obj, LocalDateTime.now());
            createtime.setAccessible(true);

            if(ObjectUtil.isNull(createtime.get(obj))){
                createtime.set(obj, LocalDateTime.now());
            }

        }
        return joinPoint.proceed();
    }
}
