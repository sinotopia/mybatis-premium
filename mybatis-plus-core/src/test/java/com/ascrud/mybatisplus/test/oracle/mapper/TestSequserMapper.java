package com.ascrud.mybatisplus.test.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ascrud.mybatisplus.mapper.BaseMapper;
import com.ascrud.mybatisplus.test.oracle.entity.TestSequser;

/**
 * TestUser 表数据库控制层接口
 */
public interface TestSequserMapper extends BaseMapper<TestSequser> {

    @Select("select * from TEST_SEQUSER")
    List<TestSequser> getList();
}
