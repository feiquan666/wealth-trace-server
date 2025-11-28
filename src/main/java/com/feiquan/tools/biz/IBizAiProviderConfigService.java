package com.feiquan.tools.biz;

import com.feiquan.tools.dto.api.req.AiProviderCreateReq;
import com.feiquan.tools.dto.api.resp.AiProviderResp;

import java.util.List;

public interface IBizAiProviderConfigService {

    /// 查询所有
    List<AiProviderResp> queryAllAiProvider();

    /// 创建
    AiProviderResp createAiProvider(AiProviderCreateReq req);

    /// 更新
    AiProviderResp updateAiProvider(AiProviderCreateReq req);

    /// 删除
    Boolean deleteAiProvider(String code);
}
