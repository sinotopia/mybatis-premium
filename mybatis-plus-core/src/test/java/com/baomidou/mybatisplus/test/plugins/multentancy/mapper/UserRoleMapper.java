package com.baomidou.mybatisplus.test.plugins.multentancy;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.test.plugins.multentancy.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;


/**
 * @author walkman
 * @date 2017-11-21
 */
@Repository("userRoleMapper")
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

}
