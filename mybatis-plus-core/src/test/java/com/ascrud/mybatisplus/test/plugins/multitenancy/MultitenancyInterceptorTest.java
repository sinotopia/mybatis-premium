package com.ascrud.mybatisplus.test.plugins.multitenancy;

import com.ascrud.mybatisplus.mapper.EntityWrapper;
import com.ascrud.mybatisplus.plugins.MultiTenancyInterceptor;
import com.ascrud.mybatisplus.plugins.parser.ISqlParser;
import com.ascrud.mybatisplus.plugins.parser.multitenancy.DefaultTenantHandler;
import com.ascrud.mybatisplus.plugins.parser.multitenancy.MultitenancySqlParser;
import com.ascrud.mybatisplus.test.plugins.multitenancy.entity.UserEntity;
import com.ascrud.mybatisplus.test.plugins.multitenancy.service.UserCrudService;
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
@ContextConfiguration(locations = {"/plugins/multitenancy/multitenancyInterceptor.xml"})
public class MultitenancyInterceptorTest {

    private static final Logger log = LoggerFactory.getLogger(MultitenancyInterceptorTest.class);

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
        Reader reader = Resources.getResourceAsReader("plugins/multitenancy/multitenancy.sql");
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setLogWriter(null);
        runner.runScript(reader);
        reader.close();
        session.close();
    }

    // select Test
    @Test
    public void selectTest() {
//        UserEntity userEntity = userCrudService.selectById(1);
//        Assert.assertNotNull(userEntity);
//        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
//        wrapper.eq("id", 1);
//        wrapper.eq("userName", "root");
//        UserEntity userEntity1 = userCrudService.selectOne(wrapper);
//        Assert.assertNotNull(userEntity1);
//        List<Integer> ids = new ArrayList<>();
//        ids.add(1);
//        List<UserEntity> userEntityies = userCrudService.selectBatchIds(ids);
//        Assert.assertFalse(userEntityies.isEmpty());

//        userCrudService.selectByMap(ids);
//        userCrudService.selectCount(ids);
//        userCrudService.selectList()
        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("id", 1);
        wrapper.eq("userName", "root");
        List<UserEntity> userEntityies = userCrudService.selectList(wrapper);
        Assert.assertFalse(userEntityies.isEmpty());
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
    public void insertTest() {
        boolean retValue = false;
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(1L);
//        userEntity1.setTenantId(10);
        userEntity1.setAccount("Account");
        userEntity1.setUserName("UserName");
        userEntity1.setPassword("password");
        userEntity1.setSalt("salt");
        userEntity1.setAccessAttemptCount(0);
        userEntity1.setEmailAddress("email.com");
//        boolean retValue = userCrudService.insertAllColumn(userEntity1);
////        boolean retValue = userCrudService.insert(userEntity);
//        Assert.assertNotNull(userEntity1.getId());
//        List<UserEntity> userEntities = new ArrayList<>();
//        UserEntity userEntity2 = new UserEntity();
//        userEntity2.setTenantId(10);
//        userEntity2.setAccount("Account");
//        userEntity2.setUserName("UserName");
//        userEntity2.setPassword("password");
//        userEntity2.setSalt("salt");
//        userEntity2.setAccessAttemptCount(0);
//        userEntity2.setEmailAddress("email.com");
//        userEntities.add(userEntity1);
//        userEntities.add(userEntity2);
//        retValue = userCrudService.insertBatch(userEntities);
//        userCrudService.insertBatch()
//        Assert.assertTrue(retValue);
        retValue = userCrudService.insertOrUpdate(userEntity1);
        Assert.assertTrue(retValue);
//        userCrudService.insertOrUpdateAllColumn()
//        userCrudService.insertOrUpdateAllColumnBatch()
//        userCrudService.insertOrUpdateAllColumnBatch()
//        userCrudService.insertOrUpdateBatch()
//        userCrudService.insertOrUpdateBatch()
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


}
