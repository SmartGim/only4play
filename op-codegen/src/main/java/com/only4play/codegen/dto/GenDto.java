package com.only4play.codegen.dto;

/**
 * @Author: Gim
 * @Date: 2019/11/25 14:13
 * @Description:
 */
public @interface GenDto {

    String pkgName();

    String sourcePath() default "src/main/java";

    boolean overrideSource() default false;
}
