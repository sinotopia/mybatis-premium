package com.ascrud.mybatisplus.test.oracle.entity;

import java.io.Serializable;

import com.ascrud.mybatisplus.annotations.KeySequence;
import com.ascrud.mybatisplus.annotations.TableField;
import com.ascrud.mybatisplus.annotations.TableId;
import com.ascrud.mybatisplus.enums.IdType;

/**
 * 用户表
 */
@KeySequence("SEQ_TEST")
public class BaseTestEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "TEST_ID", type = IdType.INPUT)
    private Long id;

    //    public BaseTestEntity() {
//
//    }
//
//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
