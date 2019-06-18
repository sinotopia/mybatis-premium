package com.ascrud.mybatisplus.test.h2.entity.persistent;

import java.math.BigDecimal;

import com.ascrud.mybatisplus.annotations.KeySequence;
import com.ascrud.mybatisplus.annotations.TableField;
import com.ascrud.mybatisplus.annotations.TableId;
import com.ascrud.mybatisplus.annotations.TableName;
import com.ascrud.mybatisplus.annotations.Version;
import com.ascrud.mybatisplus.enums.FieldStrategy;
import com.ascrud.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * </p>
 *
 * @author yuxiaobin
 * @date 2017/6/26
 */
@Data
@Accessors(chain = true)
@TableName("h2user")
@KeySequence("SEQ_TEST")
public class H2UserSequence {


    public H2UserSequence() {
    }

    public H2UserSequence(String name, Integer version) {
        this.name = name;
        this.version = version;
    }

    /**
     * 主键ID
     */
    @TableId(value = "TEST_ID", type = IdType.INPUT)
    private Long id;

    private String name;

    private Integer age;

    /*BigDecimal 测试*/
    private BigDecimal price;

    /* 测试下划线字段命名类型, 字段填充 */
    @TableField(value = "test_type", strategy = FieldStrategy.IGNORED)
    private Integer testType;

    private String desc;

    @Version
    private Integer version;


}
