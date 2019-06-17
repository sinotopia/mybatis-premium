package com.baomidou.mybatisplus.test.plugins.multentancy.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.test.plugins.multentancy.entity.UserRoleEntity;
import com.baomidou.mybatisplus.test.plugins.multentancy.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.test.plugins.multentancy.service.UserRoleCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @date 2018-08-20
 */
@Service("userRoleCrudService")
public class UserRoleCrudServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity>
    implements UserRoleCrudService {

}
