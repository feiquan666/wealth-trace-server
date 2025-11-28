package com.feiquan.tools.repo.service;

import com.feiquan.tools.repo.entity.AiProviderConfigPO;

public interface IAiProviderConfigService extends IBaseService<AiProviderConfigPO> {
    AiProviderConfigPO findByCode(String code);
}
