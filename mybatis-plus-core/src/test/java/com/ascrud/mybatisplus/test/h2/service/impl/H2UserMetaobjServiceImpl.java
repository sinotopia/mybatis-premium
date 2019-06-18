package com.ascrud.mybatisplus.test.h2.service.impl;

import org.springframework.stereotype.Service;

import com.ascrud.mybatisplus.service.impl.ServiceImpl;
import com.ascrud.mybatisplus.test.h2.entity.mapper.H2UserMetaobjMapper;
import com.ascrud.mybatisplus.test.h2.entity.persistent.H2UserMetaObj;
import com.ascrud.mybatisplus.test.h2.service.IH2UserMetaobjService;

/**
 * <p>
 * </p>
 *
 * @author yuxiaobin
 * @date 2017/6/27
 */
@Service
public class H2UserMetaobjServiceImpl extends ServiceImpl<H2UserMetaobjMapper, H2UserMetaObj> implements IH2UserMetaobjService {

}
