package com.baomidou.mybatisplus.test.plugins.multentancy;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.test.plugins.multentancy.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @author walkman
 * @date 2017-11-21
 */
@Repository("userMapper")
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 通过租户和账号查找租户用户
     *
     * @param tenantId tenantId
     * @param account  account
     * @return UserEntity
     */
    @SqlParser(filter = true)
    UserEntity findTenantUserByAccount(@Param("tenantId") Integer tenantId, @Param("account") String account);

    /**
     * 通过账号查找宿主用户
     *
     * @param account account
     * @return UserEntity
     */
    @SqlParser(filter = true)
    UserEntity findUserByAccount(@Param("account") String account);

}
