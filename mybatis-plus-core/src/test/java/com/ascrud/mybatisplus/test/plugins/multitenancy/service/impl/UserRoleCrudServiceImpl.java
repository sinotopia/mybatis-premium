package com.ascrud.mybatisplus.test.plugins.multitenancy.service.impl;

import com.ascrud.mybatisplus.service.impl.ServiceImpl;
import com.ascrud.mybatisplus.test.plugins.multitenancy.entity.UserRoleEntity;
import com.ascrud.mybatisplus.test.plugins.multitenancy.mapper.UserRoleMapper;
import com.ascrud.mybatisplus.test.plugins.multitenancy.service.UserRoleCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @date 2018-08-20
 */
@Service("userRoleCrudService")
public class UserRoleCrudServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity>
    implements UserRoleCrudService {

}
