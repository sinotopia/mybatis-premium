package com.baomidou.mybatisplus.test.plugins.multentancy.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.test.plugins.multentancy.entity.TenantEntity;
import com.baomidou.mybatisplus.test.plugins.multentancy.mapper.TenantMapper;
import com.baomidou.mybatisplus.test.plugins.multentancy.service.TenantCrudService;
import org.springframework.stereotype.Service;


/**
 * tenantCrudService
 */
@Service("tenantCrudService")
public class TenantCrudServiceImpl extends ServiceImpl<TenantMapper, TenantEntity> implements TenantCrudService {

}
