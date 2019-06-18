package com.ascrud.mybatisplus.test.plugins.paginationInterceptor.service.impl;

import org.springframework.stereotype.Service;

import com.ascrud.mybatisplus.service.impl.ServiceImpl;
import com.ascrud.mybatisplus.test.plugins.paginationInterceptor.entity.PageUser;
import com.ascrud.mybatisplus.test.plugins.paginationInterceptor.mapper.PageUserMapper;
import com.ascrud.mybatisplus.test.plugins.paginationInterceptor.service.PageUserService;

@Service
public class PageUserServiceImpl extends ServiceImpl<PageUserMapper, PageUser> implements PageUserService {

}
