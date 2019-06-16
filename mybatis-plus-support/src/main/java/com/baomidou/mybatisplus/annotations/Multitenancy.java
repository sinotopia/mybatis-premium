package com.baomidou.mybatisplus.annotations;


import java.lang.annotation.*;

/**
 * 多租户数据表注解，通过ThreadLocal来实现
 *
 * @author walkman
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiTenant {

    String defaultContextProperty = "tenantId";

    MultitenantStrategy strategy() default MultitenantStrategy.COLUMN;

    String contextProperty() default defaultContextProperty;

}
