package com.ascrud.mybatisplus.annotations;


import com.ascrud.mybatisplus.enums.MultitenancyStrategy;

import java.lang.annotation.*;

/**
 * 多租户数据表注解，通过ThreadLocal来实现
 *
 * @author walkman
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Multitenancy {

    MultitenancyStrategy strategy() default MultitenancyStrategy.NONE;

}
