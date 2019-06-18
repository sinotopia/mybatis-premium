package com.baomidou.mybatisplus.test.plugins.multitenancy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.test.plugins.multitenancy.entity.UserEntity;
import org.springframework.stereotype.Repository;


/**
 * @author walkman
 * @date 2017-11-21
 */
@Repository("userMapper")
public interface UserMapper extends BaseMapper<UserEntity> {

}
