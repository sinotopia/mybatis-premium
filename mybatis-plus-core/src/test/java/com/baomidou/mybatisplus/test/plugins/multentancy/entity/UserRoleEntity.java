package com.baomidou.mybatisplus.test.plugins.multentancy.entity;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.MultitenancyStrategy;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色表
 *
 * @author walkman
 * @since 2018-08-28
 */
@TableName("ucc_user_role")
@Multitenancy(strategy = MultitenancyStrategy.COLUMN)
public class UserRoleEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    @MultiTenancyColumn
    private Integer tenantId;

    /**
     * 用户ID
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "roleId")
    private Long roleId;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    private Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    private Date creationTime;

}
