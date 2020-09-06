package com.security.learn.config;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    double limitNum() default 20;  //默认每秒放入桶中的token
}