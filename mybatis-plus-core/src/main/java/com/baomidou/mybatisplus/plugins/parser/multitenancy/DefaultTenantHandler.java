package com.baomidou.mybatisplus.plugins.parser.multitenancy;

import com.baomidou.mybatisplus.entity.TableFieldInfo;
import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.enums.MultitenancyFillStrategy;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 多租户处理器
 * </p>
 *
 * @author willenfoo
 * @since 2018-03-09
 */
public class DefaultTenantHandler implements TenantHandler {

    /**
     * 缓存表名与多租户表的映射
     */
    private static final Map<String, TableInfo> TABLE_TENANT_MAP = new ConcurrentHashMap<>();

    /**
     * 缓存表名与多租户列的映射
     */
    private static final Map<String, TableFieldInfo> TABLE_TENANT_COLUMN_MAP = new ConcurrentHashMap<>();

    /**
     * 默认忽略
     */
    private MultitenancyFillStrategy fillStrategy = MultitenancyFillStrategy.IGNORED;

    public DefaultTenantHandler() {
        init();
    }

    @Override
    public MultitenancyFillStrategy getFillStrategy() {
        return fillStrategy;
    }

    @Override
    public void setFillStrategy(MultitenancyFillStrategy fillStrategy) {
        this.fillStrategy = fillStrategy;
    }

    private void init() {
        if (TABLE_TENANT_MAP.isEmpty()) {
            List<TableInfo> tableInfos = TableInfoHelper.getTableInfos();
            for (TableInfo tableInfo : tableInfos) {
                if (tableInfo.isMultitenancy()) {
                    TABLE_TENANT_MAP.put(tableInfo.getTableName(), tableInfo);
                }
            }
        }
        if (TABLE_TENANT_COLUMN_MAP.isEmpty()) {
            List<TableInfo> tableInfos = TableInfoHelper.getTableInfos();
            for (TableInfo tableInfo : tableInfos) {
                if (tableInfo.isMultitenancy()) {
                    List<TableFieldInfo> tableFieldInfos = tableInfo.getFieldList();
                    for (TableFieldInfo tableFieldInfo : tableFieldInfos) {
                        if (tableFieldInfo.isMultiTenancyColumn()) {
                            TABLE_TENANT_COLUMN_MAP.put(tableInfo.getTableName(), tableFieldInfo);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 是否多租户表
     *
     * @param tableName tableName
     * @return isMultitenancy
     */
    @Override
    public boolean isMultitenancy(String tableName) {
        init();
        return TABLE_TENANT_MAP.get(tableName) != null;
    }

    /**
     * 获取多租户表信息
     *
     * @param tableName tableName
     * @return TableInfo
     */
    @Override
    public TableInfo getTenantTable(String tableName) {
        init();
        return TABLE_TENANT_MAP.get(tableName);
    }

    /**
     * 获取多租户列信息
     *
     * @param tableName tableName
     * @return TableFieldInfo
     */
    @Override
    public TableFieldInfo getTenantColumn(String tableName) {
        init();
        return TABLE_TENANT_COLUMN_MAP.get(tableName);
    }

    @Override
    public String getColumn(String tableName) {
        init();
        TableFieldInfo tableFieldInfo = TABLE_TENANT_COLUMN_MAP.get(tableName);
        return tableFieldInfo == null ? null : tableFieldInfo.getColumn();
    }

    @Override
    public Expression getTenantId() {
        init();
        // 根据空值填充测策略处理
//        if (String.class.equals(TABLE_LOGIC_DELETE_MAP.get(tableName).getPropertyType())) {
//            return new StringValue(TABLE_LOGIC_DELETE_MAP.get(tableName).getLogicNotDeleteValue());
//        } else {
//            return new LongValue(TABLE_LOGIC_DELETE_MAP.get(tableName).getLogicNotDeleteValue());
//        }
        return new NullValue();
    }

    //    @Override
//    public boolean doTableFilter(String tableName) {
//        init();
//        return !TABLE_LOGIC_DELETE_MAP.containsKey(tableName);
//    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String getSchemaName() {
        return null;
    }

    @Override
    public Expression getDefaultTenantId() {
        return null;
    }

    @Override
    public void setDefaultTenantId(Expression expression) {

    }

    @Override
    public String getDefaultTableName() {
        return null;
    }

    @Override
    public void setDefaultTableName(String tableName) {

    }

    @Override
    public String getDefaultSchemaName() {
        return null;
    }

    @Override
    public void setDefaultSchemaName(String schemaName) {

    }
}
