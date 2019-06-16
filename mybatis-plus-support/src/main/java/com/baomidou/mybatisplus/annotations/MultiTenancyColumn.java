package com.baomidou.mybatisplus.annotations;


import java.lang.annotation.*;


/**
 * 当 MultiTenantType = {@code MultiTenantType.COLUMN}时，需指定租户ID所在列
 *
 * @author walkman
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiTenantColumn {

    String value() default "tenantId";

}
