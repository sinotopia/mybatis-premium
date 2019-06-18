package com.ascrud.mybatisplus.test.h2.service.impl;

import org.springframework.stereotype.Service;

import com.ascrud.mybatisplus.service.impl.ServiceImpl;
import com.ascrud.mybatisplus.test.h2.entity.mapper.H2UserSequenceMapper;
import com.ascrud.mybatisplus.test.h2.entity.persistent.H2UserSequence;
import com.ascrud.mybatisplus.test.h2.service.IH2UserSequenceService;

/**
 * <p>
 * </p>
 *
 * @author yuxiaobin
 * @date 2017/6/26
 */
@Service
public class H2UserSequenceServiceImpl extends ServiceImpl<H2UserSequenceMapper, H2UserSequence> implements IH2UserSequenceService {

}
