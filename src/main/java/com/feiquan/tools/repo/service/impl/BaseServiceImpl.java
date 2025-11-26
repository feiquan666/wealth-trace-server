package com.feiquan.tools.repo.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiquan.tools.repo.entity.BaseEntity;
import com.feiquan.tools.repo.service.IBaseService;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements IBaseService<T> {
}
