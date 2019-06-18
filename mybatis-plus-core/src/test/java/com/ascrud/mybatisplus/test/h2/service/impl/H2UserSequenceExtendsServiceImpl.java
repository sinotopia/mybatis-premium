package com.ascrud.mybatisplus.test.h2.service.impl;

import org.springframework.stereotype.Service;

import com.ascrud.mybatisplus.service.impl.ServiceImpl;
import com.ascrud.mybatisplus.test.h2.entity.mapper.H2UserSequenceExtendsMapper;
import com.ascrud.mybatisplus.test.h2.entity.persistent.H2UserSequenceExtendTO;
import com.ascrud.mybatisplus.test.h2.service.IH2UserSequenceExtendsService;

/**
 * <p>
 * </p>
 *
 * @author yuxiaobin
 * @date 2017/6/26
 */
@Service
public class H2UserSequenceExtendsServiceImpl extends ServiceImpl<H2UserSequenceExtendsMapper, H2UserSequenceExtendTO> implements IH2UserSequenceExtendsService {

}
