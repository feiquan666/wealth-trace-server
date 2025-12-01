package com.feiquan.tools.controller.manager;


import com.feiquan.tools.biz.IBizAiProviderConfigService;
import com.feiquan.tools.dto.api.WtResponse;
import com.feiquan.tools.dto.api.req.AiProviderCreateReq;
import com.feiquan.tools.dto.api.resp.AiProviderResp;
import com.feiquan.tools.util.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ai/provider")
@RequiredArgsConstructor
public class AiProviderConfigController {

    private final IBizAiProviderConfigService iBizAiProviderConfigService;

    @PostMapping("/create")
    public WtResponse<AiProviderResp> createAiProvider(@RequestBody @Validated AiProviderCreateReq createReq){
        String code = createReq.getCode();
        ExceptionUtil.throwException(StringUtils.equals("deepseek", code), "code", "非法的AI提供商code：{}，出错时间：{}", code, OffsetDateTime.now());
        return WtResponse.success(iBizAiProviderConfigService.createAiProvider(createReq));
    }

    @PutMapping("/update")
    public WtResponse<AiProviderResp> updateAiProvider(@RequestBody @Validated AiProviderCreateReq updateReq){
        return WtResponse.success(iBizAiProviderConfigService.updateAiProvider(updateReq));
    }

    @PostMapping("/queryAll")
    public WtResponse<List<AiProviderResp>> queryAll(){
        return WtResponse.success(iBizAiProviderConfigService.queryAllAiProvider());
    }

    @DeleteMapping("/delete")
    public WtResponse<Boolean> deleteAiProvider(@RequestParam("code") String code){
        return WtResponse.success(iBizAiProviderConfigService.deleteAiProvider(code));
    }
}
