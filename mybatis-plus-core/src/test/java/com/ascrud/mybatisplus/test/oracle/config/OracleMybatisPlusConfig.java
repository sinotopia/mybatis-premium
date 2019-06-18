package com.ascrud.mybatisplus.test.oracle.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import com.ascrud.mybatisplus.MybatisConfiguration;
import com.ascrud.mybatisplus.MybatisXMLLanguageDriver;
import com.ascrud.mybatisplus.entity.GlobalConfiguration;
import com.ascrud.mybatisplus.incrementer.OracleKeyGenerator;
import com.ascrud.mybatisplus.plugins.PaginationInterceptor;
import com.ascrud.mybatisplus.plugins.PerformanceInterceptor;
import com.ascrud.mybatisplus.spring.MybatisSqlSessionFactoryBean;

/**
 * <p>
 * Mybatis Plus Config
 * </p>
 *
 * @author Caratacus
 * @date 2017/4/1
 */
@Configuration
@MapperScan("com.ascrud.mybatisplus.test.oracle.mapper")
public class OracleMybatisPlusConfig {

    @Bean("mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ResourceLoader resourceLoader, GlobalConfiguration globalConfiguration) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.ascrud.mybatisplus.test.oracle.entity");
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.setConfiguration(configuration);
        PaginationInterceptor pagination = new PaginationInterceptor();
        sqlSessionFactory.setPlugins(new Interceptor[]{
            pagination,
            new PerformanceInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(globalConfiguration);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration();
        conf.setIdType(1);
//        conf.setDbType("oracle");
        conf.setKeyGenerator(new OracleKeyGenerator());
//        conf.setDbColumnUnderline(true);
        return conf;
    }
}
