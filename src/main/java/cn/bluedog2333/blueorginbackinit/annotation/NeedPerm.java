package cn.bluedog2333.blueorginbackinit.annotation;


import cn.bluedog2333.blueorginbackinit.common.enums.UserPermEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验@
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedPerm {
    UserPermEnum value();
}
