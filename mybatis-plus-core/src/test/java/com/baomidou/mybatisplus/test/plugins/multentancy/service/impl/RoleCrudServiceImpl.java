package com.scapegoat.scaffolder.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.scapegoat.scaffolder.ucc.domain.RoleEntity;
import com.scapegoat.scaffolder.ucc.mapper.RoleMapper;
import com.scapegoat.scaffolder.ucc.service.RoleCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @date 2018-08-20
 */
@Service("roleCrudService")
public class RoleCrudServiceImpl extends CrudServiceImpl<RoleMapper, RoleEntity>
        implements RoleCrudService {

}