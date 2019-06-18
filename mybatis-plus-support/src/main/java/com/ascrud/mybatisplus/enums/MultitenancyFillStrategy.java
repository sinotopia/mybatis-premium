package com.ascrud.mybatisplus.enums;


/**
 * <p>
 * 租户空值填充策略
 * </p>
 *
 * @author walkman
 * @since 2016-09-09
 */
public enum MultitenancyFillStrategy {

    /**
     * 忽略，如果租户未提供，忽略租户拦截
     */
    IGNORED(0, "忽略"),
    /**
     * 默认值填充，如果租户未提供，则默认值填充
     */
    DEFAULT(1, "默认值"),
    /**
     * NULL值填充，用于COLUMN策略下
     * 如果租户和默认值均未提供，则用NULL值填充
     */
    NULL(2, "NULL值"),
    /**
     * 如果租户未提供,则抛出异常
     */
    NOT_NULL(3, "非空");

    /**
     * 主键
     */
    private final int key;

    /**
     * 描述
     */
    private final String desc;

    MultitenancyFillStrategy(final int key, final String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static MultitenancyFillStrategy getFieldStrategy(int key) {
        MultitenancyFillStrategy[] fss = MultitenancyFillStrategy.values();
        for (MultitenancyFillStrategy fs : fss) {
            if (fs.getKey() == key) {
                return fs;
            }
        }
        return MultitenancyFillStrategy.NOT_NULL;
    }

    public int getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }
}
