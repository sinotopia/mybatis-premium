package com.scapegoat.scaffolder.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.scapegoat.scaffolder.ucc.domain.UserEntity;
import com.scapegoat.scaffolder.ucc.mapper.UserMapper;
import com.scapegoat.scaffolder.ucc.service.UserCrudService;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 * @date 2018-08-20
 */
@Service("userCrudService")
public class UserCrudServiceImpl extends CrudServiceImpl<UserMapper, UserEntity>
        implements UserCrudService {

    @Override
    public UserEntity getTenantUserByAccount(Integer tenantId, String account) {
        return this.baseMapper.findTenantUserByAccount(tenantId, account);
    }

    @Override
    public UserEntity getHostUserByAccount(String account) {
        return this.baseMapper.findUserByAccount(account);
    }
}
