/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
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

import com.ascrud.mybatisplus.entity.TableFieldInfo;
import com.ascrud.mybatisplus.entity.TableInfo;
import com.ascrud.mybatisplus.enums.MultitenancyFillStrategy;
import net.sf.jsqlparser.expression.Expression;

/**
 * <p>
 * 租户处理器（ TenantId 行级 ）
 * </p>
 *
 * @author hubin
 * @since 2017-08-31
 */
public interface TenantHandler {

    /**
     * 获取空值填充策略
     *
     * @return MultitenancyFillStrategy
     */
    MultitenancyFillStrategy getFillStrategy();

    /**
     * 设置空值填充策略
     *
     * @param multitenancyFillStrategy multitenancyFillStrategy
     */
    void setFillStrategy(MultitenancyFillStrategy multitenancyFillStrategy);

    /**
     * 是否多租户表
     *
     * @param tableName tableName
     * @return isMultitenancy
     */
    boolean isMultitenancy(String tableName);

    /**
     * 获取多租户表信息
     *
     * @param tableName tableName
     * @return TableInfo
     */
    TableInfo getTenantTable(String tableName);

    /**
     * 获取多租户列信息
     *
     * @param tableName tableName
     * @return TableFieldInfo
     */
    TableFieldInfo getTenantColumn(String tableName);

    /**
     * 列多租户模式下获取列名
     *
     * @param tableName tableName
     * @return columnName
     */
    String getColumn(String tableName);

    /**
     * 列多租户模式下获取租户ID
     *
     * @return Expression
     */
    Expression getTenantId();

    /**
     * 列多租户模式下获取表名
     *
     * @return tableName
     */
    String getTableName();

    /**
     * SCHEMA多租户模式下获取SCHEMA名
     *
     * @return schemaName
     */
    String getSchemaName();

    /**
     * 列多租户模式下获取默认租户ID
     * 仅在 @{code getTenantId==null && getFillStrategy == MultitenancyFillStrategy.DEFAULT}
     * 下有效
     *
     * @return expression
     */
    Expression getDefaultTenantId();

    /**
     * setDefaultTenantId
     *
     * @param expression expression
     */
    void setDefaultTenantId(Expression expression);

    /**
     * 列多租户模式下获取默认表名
     * 仅在 @{code getTableName==null && getFillStrategy == MultitenancyFillStrategy.DEFAULT}
     *
     * @return tableName
     */
    String getDefaultTableName();

    /**
     * setDefaultTableName
     *
     * @param tableName tableName
     */
    void setDefaultTableName(String tableName);

    /**
     * 列多租户模式下获取默认表名
     * 仅在 @{code getSchemaName==null && getFillStrategy == MultitenancyFillStrategy.DEFAULT}
     *
     * @param schemaName schemaName
     */
    void setDefaultSchemaName(String schemaName);

    /**
     * getDefaultSchemaName
     *
     * @return schemaName
     */
    String getDefaultSchemaName();

}
