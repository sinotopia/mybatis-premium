package com.scapegoat.scaffolder.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.scapegoat.scaffolder.ucc.domain.TenantEntity;
import com.scapegoat.scaffolder.ucc.mapper.TenantMapper;
import com.scapegoat.scaffolder.ucc.service.TenantCrudService;
import org.springframework.stereotype.Service;


/**
 * tenantCrudService
 */
@Service("tenantCrudService")
public class TenantCrudServiceImpl extends CrudServiceImpl<TenantMapper, TenantEntity> implements TenantCrudService {

    @Override
    public TenantEntity findByTenantCode(String code) {
        TenantEntity tenantEntity = new TenantEntity();
        tenantEntity.setCode(code);
        return this.baseMapper.selectOne(tenantEntity);
    }

    @Override
    public TenantEntity findByTenantName(String name) {
        TenantEntity tenantEntity = new TenantEntity();
        tenantEntity.setName(name);
        return this.baseMapper.selectOne(tenantEntity);
    }
}
