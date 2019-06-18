package com.baomidou.mybatisplus.test.plugins.multitenancy.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.test.plugins.multitenancy.entity.TenantEntity;
import com.baomidou.mybatisplus.test.plugins.multitenancy.mapper.TenantMapper;
import com.baomidou.mybatisplus.test.plugins.multitenancy.service.TenantCrudService;
import org.springframework.stereotype.Service;


/**
 * tenantCrudService
 */
@Service("tenantCrudService")
public class TenantCrudServiceImpl extends ServiceImpl<TenantMapper, TenantEntity> implements TenantCrudService {

}
