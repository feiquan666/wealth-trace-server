package com.feiquan.tools.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.feiquan.tools.biz.IBizAiProviderConfigService;
import com.feiquan.tools.dto.api.req.AiProviderCreateReq;
import com.feiquan.tools.dto.api.resp.AiProviderResp;
import com.feiquan.tools.repo.entity.AiProviderConfigPO;
import com.feiquan.tools.repo.service.IAiProviderConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BizAiProviderConfigServiceImpl implements IBizAiProviderConfigService {
    private final IAiProviderConfigService iAiProviderConfigService;

    @Override
    public List<AiProviderResp> queryAllAiProvider() {
        return AiProviderResp.fromPOList(iAiProviderConfigService.list());
    }

    @Override
    public AiProviderResp createAiProvider(AiProviderCreateReq req) {
        String code = req.getCode();
        AiProviderConfigPO providerConfig = iAiProviderConfigService.findByCode(code);
        if (Objects.isNull(providerConfig)) {
            providerConfig = req.toPO();
        }
        return AiProviderResp.fromPO(providerConfig);
    }

    @Override
    public AiProviderResp updateAiProvider(AiProviderCreateReq req) {
        String code = req.getCode();
        AiProviderConfigPO providerConfig = iAiProviderConfigService.findByCode(code);
        // todo 跑异常表
        if (Objects.isNull(providerConfig)) {
            return null;
        }
        return null;
    }

    @Override
    public Boolean deleteAiProvider(String code) {
        return iAiProviderConfigService.remove(new UpdateWrapper<AiProviderConfigPO>()
                .lambda().eq(AiProviderConfigPO::getProviderCode, code));
    }
}
