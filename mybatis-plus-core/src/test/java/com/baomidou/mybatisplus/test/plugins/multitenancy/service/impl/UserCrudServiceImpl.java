package com.baomidou.mybatisplus.test.plugins.multentancy.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.test.plugins.multentancy.entity.UserEntity;
import com.baomidou.mybatisplus.test.plugins.multentancy.mapper.UserMapper;
import com.baomidou.mybatisplus.test.plugins.multentancy.service.UserCrudService;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 * @date 2018-08-20
 */
@Service("userCrudService")
public class UserCrudServiceImpl extends ServiceImpl<UserMapper, UserEntity>
    implements UserCrudService {

}
