package com.only4play.codegen.vo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Gim
 * @Date: 2019/11/25 14:07
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenVo {

    String pkgName();

    String sourcePath() default "src/main/java";

    boolean overrideSource() default false;
}
