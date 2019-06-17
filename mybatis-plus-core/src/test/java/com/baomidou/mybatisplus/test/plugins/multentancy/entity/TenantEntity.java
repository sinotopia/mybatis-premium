package com.baomidou.mybatisplus.test.plugins.multentancy.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 租户表
 *
 * @author walkman
 * @since 2018-08-28
 */
@TableName("ucc_tenant")
public class TenantEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 租户编码
     */
    @TableField("code")
    private String code;

    /**
     * 租户名称
     */
    @TableField("name")
    private String name;

    /**
     * 商户类型：1 企业； 2 其他组织
     */
    @TableField("type")
    private Integer type;

    /**
     * 运营商状态：0 新增；1 待审核；2 审核不通过；3 上线；4 下线；5 已删除
     */
    @TableField("status")
    private Integer status;

    /**
     * 域名
     */
    @TableField("domain")
    private String domain;

    /**
     * logo图片地址
     */
    @TableField("logo")
    private String logo;

    /**
     * 网址
     */
    @TableField("url")
    private String url;

    /**
     * 简介
     */
    @TableField("description")
    private String description;

    /**
     * 超级管理员用户ID
     */
    @TableField("adminUserId")
    private Long adminUserId;

    /**
     * 租户生效时间
     */
    @TableField("beginTime")
    private Date beginTime;

    /**
     * 租户到期时间
     */
    @TableField("endTime")
    private Date endTime;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    protected Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    protected Date creationTime;

    /**
     * 更新者
     */
    @TableField(value = "lastModifierUserId", fill = FieldFill.INSERT_UPDATE)
    protected Long lastModifierUserId;

    /**
     * 更新日期
     */
    @TableField(value = "lastModificationTime", fill = FieldFill.INSERT_UPDATE)
    protected Date lastModificationTime;

    /**
     * 删除者
     */
    @TableField(value = "deleterUserId", fill = FieldFill.UPDATE)
    protected Long deleterUserId;

    /**
     * 删除日期
     */
    @TableField(value = "deletionTime", fill = FieldFill.UPDATE)
    protected Date deletionTime;

    /**
     * 删除标记（0：正常；1：删除 ）
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT_UPDATE)
    @TableLogic
    protected Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Long getLastModifierUserId() {
        return lastModifierUserId;
    }

    public void setLastModifierUserId(Long lastModifierUserId) {
        this.lastModifierUserId = lastModifierUserId;
    }

    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(Date lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public Long getDeleterUserId() {
        return deleterUserId;
    }

    public void setDeleterUserId(Long deleterUserId) {
        this.deleterUserId = deleterUserId;
    }

    public Date getDeletionTime() {
        return deletionTime;
    }

    public void setDeletionTime(Date deletionTime) {
        this.deletionTime = deletionTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
