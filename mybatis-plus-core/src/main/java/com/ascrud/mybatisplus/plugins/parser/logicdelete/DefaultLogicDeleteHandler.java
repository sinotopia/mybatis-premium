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
package com.ascrud.mybatisplus.plugins.parser.logicdelete;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ascrud.mybatisplus.entity.TableFieldInfo;
import com.ascrud.mybatisplus.entity.TableInfo;
import com.ascrud.mybatisplus.toolkit.TableInfoHelper;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;

/**
 * <p>
 * 逻辑删除处理器
 * </p>
 *
 * @author willenfoo
 * @since 2018-03-09
 */
public class DefaultLogicDeleteHandler implements LogicDeleteHandler {

    /**
     * 缓存表名与逻辑删除字段的映射
     */
    private static final Map<String, TableFieldInfo> TABLE_LOGIC_DELETE_MAP = new ConcurrentHashMap<String, TableFieldInfo>();

    public DefaultLogicDeleteHandler() {
        init();
    }

    private void init() {
        if (TABLE_LOGIC_DELETE_MAP.isEmpty()) {
            List<TableInfo> tableInfos = TableInfoHelper.getTableInfos();
            for (TableInfo tableInfo : tableInfos) {
                List<TableFieldInfo> tableFieldInfos = tableInfo.getFieldList();
                for (TableFieldInfo tableFieldInfo : tableFieldInfos) {
                    if (tableFieldInfo.isLogicDelete()) {
                        TABLE_LOGIC_DELETE_MAP.put(tableInfo.getTableName(), tableFieldInfo);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public Expression getValue(String tableName) {
        init();
        if (String.class.equals(TABLE_LOGIC_DELETE_MAP.get(tableName).getPropertyType())) {
            return new StringValue(TABLE_LOGIC_DELETE_MAP.get(tableName).getLogicNotDeleteValue());
        } else {
            return new LongValue(TABLE_LOGIC_DELETE_MAP.get(tableName).getLogicNotDeleteValue());
        }
    }

    @Override
    public String getColumn(String tableName) {
        init();
        return TABLE_LOGIC_DELETE_MAP.get(tableName).getColumn();
    }

    @Override
    public boolean doTableFilter(String tableName) {
        init();
        return !TABLE_LOGIC_DELETE_MAP.containsKey(tableName);
    }
}
