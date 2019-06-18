/**
 * Copyright (c) 2011-2020, cacotopia (cacotopia@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.ascrud.mybatisplus.plugins.parser.multitenancy;

import java.util.List;

import com.ascrud.mybatisplus.entity.TableFieldInfo;
import com.ascrud.mybatisplus.entity.TableInfo;
import com.ascrud.mybatisplus.enums.MultitenancyFillStrategy;
import com.ascrud.mybatisplus.enums.MultitenancyStrategy;
import com.ascrud.mybatisplus.exceptions.MybatisPlusException;
import com.ascrud.mybatisplus.plugins.parser.AbstractJsqlParser;

import com.ascrud.mybatisplus.toolkit.CollectionUtils;
import com.ascrud.mybatisplus.toolkit.StringUtils;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.update.Update;

/**
 * <p>
 * 租户 SQL 解析（ TenantId 行级 ）
 * </p>
 *
 * @author hubin
 * @since 2017-09-01
 */
public class MultitenancySqlParser extends AbstractJsqlParser {

    private TenantHandler tenantHandler;

    public TenantHandler getTenantHandler() {
        return tenantHandler;
    }

    public void setTenantHandler(TenantHandler tenantHandler) {
        this.tenantHandler = tenantHandler;
    }

    /**
     * select 语句处理
     */
    @Override
    public void processSelectBody(SelectBody selectBody) {
        if (selectBody instanceof PlainSelect) {
            processPlainSelect((PlainSelect) selectBody);
        } else if (selectBody instanceof WithItem) {
            WithItem withItem = (WithItem) selectBody;
            if (withItem.getSelectBody() != null) {
                processSelectBody(withItem.getSelectBody());
            }
        } else {
            SetOperationList operationList = (SetOperationList) selectBody;
            if (operationList.getSelects() != null && operationList.getSelects().size() > 0) {
                List<SelectBody> plainSelects = operationList.getSelects();
                for (SelectBody plainSelect : plainSelects) {
                    processSelectBody(plainSelect);
                }
            }
        }
    }

    /**
     * <p>
     * insert 语句处理
     * </p>
     */
    @Override
    public void processInsert(Insert insert) {
        String tableName = insert.getTable().getName();
        TableInfo tableInfo = this.tenantHandler.getTenantTable(tableName);
        if (tableInfo == null) {
            // 非多租户表退出执行
            return;
        }
        if (MultitenancyStrategy.TABLE.equals(tableInfo.getMultitenancyStrategy())) {
            // 如果是表多租户模式
        } else if (MultitenancyStrategy.COLUMN.equals(tableInfo.getMultitenancyStrategy())) {
            // 如果是列多租户模式
            Expression tenantValue;
            try {
                tenantValue = getTenantValue();
                if (tenantValue == null && MultitenancyFillStrategy.IGNORED.equals(this.tenantHandler.getFillStrategy())) {
                    return;
                }
            } catch (MybatisPlusException e) {
                logger.error(e.getMessage());
                throw e;
            }
            TableFieldInfo tenantColumn = tenantHandler.getTenantColumn(tableName);
            boolean fillTenant = false;
            if (tenantColumn != null && !checkExistTenantColumn(insert.getColumns(), tenantColumn.getColumn())) {
                insert.getColumns().add(new Column(this.tenantHandler.getColumn(tableName)));
                fillTenant = true;
            }
            if (insert.getSelect() != null) {
                processPlainSelect((PlainSelect) insert.getSelect().getSelectBody(), true);
            } else if (insert.getItemsList() != null) {
                // fixed github pull/295
                ItemsList itemsList = insert.getItemsList();
                if (itemsList instanceof MultiExpressionList) {
                    List<ExpressionList> exprList = ((MultiExpressionList) itemsList).getExprList();
                    for (ExpressionList el : exprList) {
                        if (fillTenant) {
                            el.getExpressions().add(tenantValue);
                        }
                    }
                } else {
                    if (fillTenant) {
                        ((ExpressionList) insert.getItemsList()).getExpressions().add(tenantValue);
                    }
                }
            } else {
                throw new MybatisPlusException("Failed to process multiple-table update, please exclude the tableName or statementId");
            }
        }
    }

    /**
     * 是否以存在多租户列
     *
     * @param columnList columnList
     * @param columnName columnName
     * @return boolean
     */
    private boolean checkExistTenantColumn(List<Column> columnList, String columnName) {
        if (CollectionUtils.isEmpty(columnList) || StringUtils.isEmpty(columnName)) {
            return false;
        }
        for (Column column : columnList) {
            if (null != column) {
                //如果插入SQL中已经包含租户列则注入租户
                if (columnName.equals(column.getColumnName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <p>
     * update 语句处理
     * </p>
     */
    @Override
    public void processUpdate(Update update) {
        List<Table> tableList = update.getTables();
        if (null == tableList || tableList.size() >= 2) {
            throw new MybatisPlusException("Failed to process multiple-table update, please exclude the statementId");
        }
        Table table = tableList.get(0);
        String tableName = table.getName();
        TableInfo tableInfo = this.tenantHandler.getTenantTable(tableName);
        // 非多租户表退出执行
        if (tableInfo == null) {
            return;
        }
        if (MultitenancyStrategy.TABLE.equals(tableInfo.getMultitenancyStrategy())) {
            // 如果是表多租户模式
        } else if (MultitenancyStrategy.COLUMN.equals(tableInfo.getMultitenancyStrategy())) {
            Expression tenantValue = this.tenantHandler.getTenantId();
            // 如果是列多租户模式
            if (tenantValue == null) {
                // 空值处理
                if (MultitenancyFillStrategy.IGNORED.equals(this.tenantHandler.getFillStrategy())) {
                    return;
                } else {
                    tenantValue = this.getTenantValue();
                }
            }
            if (MultitenancyFillStrategy.NOT_NULL.equals(this.tenantHandler.getFillStrategy())) {
                throw new MybatisPlusException("Failed to process Multitenancy-table insert, tenantValue cannot be null.");
            }
            TableFieldInfo tenantColumn = this.tenantHandler.getTenantColumn(tableName);
            if (tenantColumn != null && !checkExistTenantColumn(update.getColumns(), tenantColumn.getColumn())) {
                update.setWhere(this.andExpression(table, update.getWhere(), tenantValue));
            }
        }
    }

    /**
     * <p>
     * delete 语句处理
     * </p>
     */
    @Override
    public void processDelete(Delete delete) {
        String tableName = delete.getTable().getName();
        TableInfo tableInfo = this.tenantHandler.getTenantTable(tableName);
        // 非多租户表退出执行
        if (tableInfo == null) {
            return;
        }
        if (MultitenancyStrategy.TABLE.equals(tableInfo.getMultitenancyStrategy())) {
            // 如果是表多租户模式
        } else if (MultitenancyStrategy.COLUMN.equals(tableInfo.getMultitenancyStrategy())) {
            Expression tenantValue = this.tenantHandler.getTenantId();
            // 如果是列多租户模式
            if (tenantValue == null) {
                // 空值处理
                if (MultitenancyFillStrategy.IGNORED.equals(this.tenantHandler.getFillStrategy())) {
                    return;
                } else {
                    tenantValue = this.getTenantValue();
                }
            }
            if (MultitenancyFillStrategy.NOT_NULL.equals(this.tenantHandler.getFillStrategy())) {
                throw new MybatisPlusException("Failed to process Multitenancy-table insert, tenantValue cannot be null.");
            }
            TableFieldInfo tenantColumn = this.tenantHandler.getTenantColumn(tableName);
            if (tenantColumn != null) {
                delete.setWhere(this.andExpression(delete.getTable(), delete.getWhere(), tenantValue));
            }
        }
    }

    /**
     * <p>
     * delete update 语句 where 处理
     * </p>
     */
    protected Expression andExpression(Table table, Expression where, Expression tenantValue) {
        //获得where条件表达式
        if (tenantValue != null) {
            if (tenantValue instanceof NullValue) {
                //支持TenantId为空
                IsNullExpression isNullExpression = new IsNullExpression();
                isNullExpression.setLeftExpression(this.getAliasColumn(table));
                if (null != where) {
                    return new AndExpression(isNullExpression, where);
                }
                return isNullExpression;
            } else {
                EqualsTo equalsTo = new EqualsTo();
                equalsTo.setLeftExpression(this.getAliasColumn(table));
                equalsTo.setRightExpression(tenantValue);
                if (null != where) {
                    return new AndExpression(equalsTo, where);
                }
                return equalsTo;
            }
        }
        return where;
    }

    /**
     * <p>
     * 处理 PlainSelect
     * </p>
     */
    protected void processPlainSelect(PlainSelect plainSelect) {
        processPlainSelect(plainSelect, false);
    }

    /**
     * <p>
     * 处理 PlainSelect
     * </p>
     *
     * @param plainSelect plainSelect
     * @param addColumn   是否添加租户列,insert into select语句中需要
     */
    protected void processPlainSelect(PlainSelect plainSelect, boolean addColumn) {
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table fromTable = (Table) fromItem;
            if (!this.tenantHandler.isMultitenancy(fromTable.getName())) {
                // 过滤退出执行
                return;
            }
            TableInfo tableInfo = this.tenantHandler.getTenantTable(fromTable.getName());
            // 非多租户表退出执行
            if (tableInfo == null) {
                return;
            }
            if (MultitenancyStrategy.TABLE.equals(tableInfo.getMultitenancyStrategy())) {
                // 如果是表多租户模式
            } else if (MultitenancyStrategy.COLUMN.equals(tableInfo.getMultitenancyStrategy())) {
                Expression tenantValue = this.tenantHandler.getTenantId();
                // 如果是列多租户模式
                if (tenantValue == null) {
                    // 空值处理
                    if (MultitenancyFillStrategy.IGNORED.equals(this.tenantHandler.getFillStrategy())) {
                        return;
                    } else {
                        tenantValue = this.getTenantValue();
                    }
                }
                if (MultitenancyFillStrategy.NOT_NULL.equals(this.tenantHandler.getFillStrategy())) {
                    throw new MybatisPlusException("Failed to process Multitenancy-table insert, tenantValue cannot be null.");
                }
                plainSelect.setWhere(builderExpression(plainSelect.getWhere(), fromTable, tenantValue));
                if (addColumn) {
                    plainSelect.getSelectItems().add(new SelectExpressionItem(new Column(this.tenantHandler.getColumn(fromTable.getName()))));
                }
            }
        } else {
            processFromItem(fromItem);
        }
        List<Join> joins = plainSelect.getJoins();
        if (joins != null && joins.size() > 0) {
            for (Join join : joins) {
                processJoin(join);
                processFromItem(join.getRightItem());
            }
        }
    }

    /**
     * 处理子查询等
     *
     * @param fromItem fromItem
     */
    protected void processFromItem(FromItem fromItem) {
        if (fromItem instanceof SubJoin) {
            SubJoin subJoin = (SubJoin) fromItem;
            if (subJoin.getJoin() != null) {
                processJoin(subJoin.getJoin());
            }
            if (subJoin.getLeft() != null) {
                processFromItem(subJoin.getLeft());
            }
        } else if (fromItem instanceof SubSelect) {
            SubSelect subSelect = (SubSelect) fromItem;
            if (subSelect.getSelectBody() != null) {
                processSelectBody(subSelect.getSelectBody());
            }
        } else if (fromItem instanceof ValuesList) {
            logger.debug("Perform a subquery, if you do not give us feedback");
        } else if (fromItem instanceof LateralSubSelect) {
            LateralSubSelect lateralSubSelect = (LateralSubSelect) fromItem;
            if (lateralSubSelect.getSubSelect() != null) {
                SubSelect subSelect = lateralSubSelect.getSubSelect();
                if (subSelect.getSelectBody() != null) {
                    processSelectBody(subSelect.getSelectBody());
                }
            }
        }
    }

    /**
     * 处理联接语句
     */
    protected void processJoin(Join join) {
        if (join.getRightItem() instanceof Table) {
            Table fromTable = (Table) join.getRightItem();
            if (!tenantHandler.isMultitenancy(this.tenantHandler.getTableName())) {
                // 过滤退出执行
                return;
            }
            TableInfo tableInfo = this.tenantHandler.getTenantTable(fromTable.getName());
            // 非多租户表退出执行
            if (tableInfo == null) {
                return;
            }
            if (MultitenancyStrategy.TABLE.equals(tableInfo.getMultitenancyStrategy())) {
                // 如果是表多租户模式
            } else if (MultitenancyStrategy.COLUMN.equals(tableInfo.getMultitenancyStrategy())) {
                Expression tenantValue = this.tenantHandler.getTenantId();
                // 如果是列多租户模式
                if (tenantValue == null) {
                    // 空值处理
                    if (MultitenancyFillStrategy.IGNORED.equals(this.tenantHandler.getFillStrategy())) {
                        return;
                    } else {
                        tenantValue = this.getTenantValue();
                    }
                }
                if (MultitenancyFillStrategy.NOT_NULL.equals(this.tenantHandler.getFillStrategy())) {
                    throw new MybatisPlusException("Failed to process Multitenancy-table insert, tenantValue cannot be null.");
                }
                join.setOnExpression(builderExpression(join.getOnExpression(), fromTable, tenantValue));
            }
        }
    }

    /**
     * 处理条件
     */
    protected Expression builderExpression(Expression expression, Table table, Expression tenantValue) {

        if (tenantValue instanceof NullValue) {
            //支持TenantId为空
            IsNullExpression isNullExpression = new IsNullExpression();
            isNullExpression.setLeftExpression(this.getAliasColumn(table));
            if (null != expression) {
                return new AndExpression(isNullExpression, expression);
            }
            return isNullExpression;
        } else {
            //生成字段名
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(this.getAliasColumn(table));
            equalsTo.setRightExpression(tenantValue);
            //加入判断防止条件为空时生成 "and null" 导致查询结果为空
            if (null != expression) {
                return new AndExpression(equalsTo, expression);
            }
            return equalsTo;
        }
    }

    /**
     * <p>
     * 租户字段别名设置<br>
     * tableName.tenantId 或 tableAlias.tenantId
     * </p>
     *
     * @param table 表对象
     * @return 字段
     */
    protected Column getAliasColumn(Table table) {
        StringBuilder column = new StringBuilder();
//        if (null == table.getAlias()) {
//            column.append(table.getName());
//        }
        if (table.getAlias() != null) {
            column.append(table.getAlias().getName());
            column.append(".");
        }
        column.append(this.tenantHandler.getColumn(table.getName()));
        return new Column(column.toString());
    }

    protected Expression getTenantValue() throws MybatisPlusException {
        Expression tenantValue = this.tenantHandler.getTenantId();
        // 如果是列多租户模式
        if (tenantValue == null) {
            if (MultitenancyFillStrategy.DEFAULT.equals(this.tenantHandler.getFillStrategy())) {
                // 空值处理
                tenantValue = this.tenantHandler.getDefaultTenantId();
                if (tenantValue == null) {
                    tenantValue = new NullValue();
                }
            } else if (MultitenancyFillStrategy.NULL.equals(this.tenantHandler.getFillStrategy())) {
                tenantValue = new NullValue();
            } else if (MultitenancyFillStrategy.NOT_NULL.equals(this.tenantHandler.getFillStrategy())) {
                throw new MybatisPlusException("Failed to process Multitenancy-table insert, tenantValue cannot be null.");
            }
        }
        return tenantValue;
    }

}
