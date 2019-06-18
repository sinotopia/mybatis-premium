package com.baomidou.mybatisplus.test.plugins.multitenancy.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.test.plugins.multitenancy.entity.RoleEntity;
import com.baomidou.mybatisplus.test.plugins.multitenancy.mapper.RoleMapper;
import com.baomidou.mybatisplus.test.plugins.multitenancy.service.RoleCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @date 2018-08-20
 */
@Service("roleCrudService")
public class RoleCrudServiceImpl extends ServiceImpl<RoleMapper, RoleEntity>
    implements RoleCrudService {

}
