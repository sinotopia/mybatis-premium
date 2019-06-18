package com.ascrud.mybatisplus.test.h2.entity.persistent;

import com.ascrud.mybatisplus.annotations.TableField;
import com.ascrud.mybatisplus.annotations.TableId;
import com.ascrud.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * h2address entity.
 * </p>
 *
 * @author yuxiaobin
 * @date 2017/5/25
 */
@Data
@Accessors(chain = true)
@TableName("h2address")
public class H2Addr {

    @TableId("addr_id")
    private Long addrId;

    @TableField("addr_name")
    private String addrName;

    @TableField("test_id")
    private Long testId;

}
