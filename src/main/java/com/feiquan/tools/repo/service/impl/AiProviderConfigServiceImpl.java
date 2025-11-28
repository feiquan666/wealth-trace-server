package com.feiquan.tools.repo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feiquan.tools.repo.entity.AiProviderConfigPO;
import com.feiquan.tools.repo.mapper.IAiProviderConfigMapper;
import com.feiquan.tools.repo.service.IAiProviderConfigService;
import org.springframework.stereotype.Repository;

@Repository
public class AiProviderConfigServiceImpl extends BaseServiceImpl<IAiProviderConfigMapper, AiProviderConfigPO> implements IAiProviderConfigService {
    @Override
    public AiProviderConfigPO findByCode(String code) {
        return getOne(new QueryWrapper<AiProviderConfigPO>().lambda().eq(AiProviderConfigPO::getProviderCode, code).last("LIMIT 1"));
    }
}
