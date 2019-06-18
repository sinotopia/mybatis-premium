package com.ascrud.mybatisplus.test.h2.service.impl;

import org.springframework.stereotype.Service;

import com.ascrud.mybatisplus.service.impl.ServiceImpl;
import com.ascrud.mybatisplus.test.h2.entity.mapper.H2UserExtendsMapper;
import com.ascrud.mybatisplus.test.h2.entity.persistent.H2UserIntVersionExtendTO;
import com.ascrud.mybatisplus.test.h2.service.IH2UserExtendsService;

/**
 * <p>
 * </p>
 *
 * @author yuxiaobin
 * @date 2017/6/26
 */
@Service
public class H2UserExtendsServiceImpl extends ServiceImpl<H2UserExtendsMapper, H2UserIntVersionExtendTO> implements IH2UserExtendsService {

}
