package com.log.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnn {

//这里可以设置更多方法，看你需求


    /**
     * 是否记录返回参数
     * @return
     */
    boolean saveResponse() default true;



}
