package com.baomidou.mybatisplus.test.plugins.multitenancy.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.test.plugins.multitenancy.entity.UserEntity;
import com.baomidou.mybatisplus.test.plugins.multitenancy.mapper.UserMapper;
import com.baomidou.mybatisplus.test.plugins.multitenancy.service.UserCrudService;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 * @date 2018-08-20
 */
@Service("userCrudService")
public class UserCrudServiceImpl extends ServiceImpl<UserMapper, UserEntity>
    implements UserCrudService {

}
