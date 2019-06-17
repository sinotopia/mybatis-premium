package com.baomidou.mybatisplus.plugins;

import com.baomidou.mybatisplus.toolkit.PluginUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;


/**
 * 共享数据库的多租户系统实现
 *
 * @author walkman
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MultiTenancyInterceptor extends SqlParserHandler implements Interceptor {

    /**
     * 日志
     */
    private static final Log logger = LogFactory.getLog(PaginationInterceptor.class);

    /**
     * 属性参数信息
     */
    private Properties properties;

    public MultiTenancyInterceptor() {
        if (logger.isDebugEnabled()) {
            logger.debug("初始化MultiTenancyInterceptor插件");
        }
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        return invocation.proceed();
    }

    /**
     * @param target target
     * @return Object
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 获取配置信息
     * {dialect=mysql, tenantInfo=org.xue.test.TenantInfoImpl, tenantIdColumn=tenant_id}
     *
     * @param properties properties
     */
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
