package com.baomidou.mybatisplus.test.plugins.multentancy;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.MultiTenancyInterceptor;
import com.baomidou.mybatisplus.plugins.parser.ISqlParser;
import com.baomidou.mybatisplus.plugins.parser.multitenancy.DefaultTenantHandler;
import com.baomidou.mybatisplus.plugins.parser.multitenancy.MultitenancySqlParser;
import com.baomidou.mybatisplus.test.plugins.multentancy.entity.UserEntity;
import com.baomidou.mybatisplus.test.plugins.multentancy.service.UserCrudService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Reader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/plugins/multentancy/multentancyInterceptor.xml"})
public class MultentancyInterceptorTest {

    private static final Logger log = LoggerFactory.getLogger(MultentancyInterceptorTest.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private MultiTenancyInterceptor multiTenancyInterceptor;

    @Before
    public void setUp() throws Exception {

        List<ISqlParser> sqlParserList = new ArrayList<>();
        MultitenancySqlParser tenantSqlParser = new MultitenancySqlParser();
        tenantSqlParser.setTenantHandler(new DefaultTenantHandler());
        sqlParserList.add(tenantSqlParser);
        multiTenancyInterceptor.setSqlParserList(sqlParserList);

        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession();
        Connection conn = session.getConnection();
        Reader reader = Resources.getResourceAsReader("plugins/multentancy/multentancy.sql");
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setLogWriter(null);
        runner.runScript(reader);
        reader.close();
        session.close();
    }

    // select Test
    @Test
    public void selectTest() {
        UserEntity userEntity = userCrudService.selectById(1);
        Assert.assertNotNull(userEntity);
//        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
//        wrapper.eq("id", 1);
//        UserEntity userEntity1 = userCrudService.selectOne(wrapper);
//        Assert.assertNotNull(userEntity1);
//        List<Integer> ids = new ArrayList<>();
//        List<UserEntity> userEntityies = userCrudService.selectBatchIds(ids);
//        Assert.assertFalse(userEntityies.isEmpty());
//        List<UserEntity> userEntityies = userCrudService.se(ids);
//        userCrudService.selectByMap(ids);
//        userCrudService.selectCount(ids);
//        userCrudService.selectList()
//        userCrudService.selectMaps()
//        userCrudService.selectObj()
//        userCrudService.selectObjs()
    }

    @Test
    public void selectPageTest() {
        // 带OrderBy
        // userCrudService.selectPage()
        //  userCrudService.selectPage()
        //  userCrudService.selectMapsPage()
    }

    @Test
    public void updateTest() {
//        userCrudService.update()
//        userCrudService.updateAllColumnBatchById()
//        userCrudService.updateAllColumnBatchById()
//        userCrudService.updateAllColumnById()
//        userCrudService.updateBatchById(
//        userCrudService.updateById()
//        userCrudService.updateForSet()
    }

    @Test
    public void insertTest() {
//        userCrudService.insert()
//        userCrudService.insertAllColumn()
//        userCrudService.insertBatch()
//        userCrudService.insertBatch()
//        userCrudService.insertOrUpdate()
//        userCrudService.insertOrUpdateAllColumn()
//        userCrudService.insertOrUpdateAllColumnBatch()
//        userCrudService.insertOrUpdateAllColumnBatch()
//        userCrudService.insertOrUpdateBatch()
//        userCrudService.insertOrUpdateBatch()
    }
}
