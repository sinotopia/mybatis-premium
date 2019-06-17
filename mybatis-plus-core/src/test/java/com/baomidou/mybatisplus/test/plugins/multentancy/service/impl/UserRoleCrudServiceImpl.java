package com.scapegoat.scaffolder.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.scapegoat.scaffolder.ucc.domain.UserRoleEntity;
import com.scapegoat.scaffolder.ucc.mapper.UserRoleMapper;
import com.scapegoat.scaffolder.ucc.service.UserRoleCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @date 2018-08-20
 */
@Service("userRoleCrudService")
public class UserRoleCrudServiceImpl extends CrudServiceImpl<UserRoleMapper, UserRoleEntity>
        implements UserRoleCrudService {

}