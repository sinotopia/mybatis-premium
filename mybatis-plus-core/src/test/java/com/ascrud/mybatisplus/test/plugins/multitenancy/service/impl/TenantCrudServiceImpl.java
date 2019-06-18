package com.ascrud.mybatisplus.test.plugins.multitenancy.service.impl;

import com.ascrud.mybatisplus.service.impl.ServiceImpl;
import com.ascrud.mybatisplus.test.plugins.multitenancy.entity.TenantEntity;
import com.ascrud.mybatisplus.test.plugins.multitenancy.mapper.TenantMapper;
import com.ascrud.mybatisplus.test.plugins.multitenancy.service.TenantCrudService;
import org.springframework.stereotype.Service;


/**
 * tenantCrudService
 */
@Service("tenantCrudService")
public class TenantCrudServiceImpl extends ServiceImpl<TenantMapper, TenantEntity> implements TenantCrudService {

}
