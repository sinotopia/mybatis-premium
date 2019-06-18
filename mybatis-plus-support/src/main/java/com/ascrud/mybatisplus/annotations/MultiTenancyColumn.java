package com.ascrud.mybatisplus.annotations;


import java.lang.annotation.*;


/**
 * 当 MultiTenantType = {@code MultiTenantType.COLUMN}时，需指定租户ID所在列
 *
 * @author walkman
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiTenancyColumn {

    /**
     * <p>
     * 字段名
     * </p>
     */
    String value() default "tenantId";

}
