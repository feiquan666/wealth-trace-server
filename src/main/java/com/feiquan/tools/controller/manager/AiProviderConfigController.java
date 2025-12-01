package com.feiquan.tools.controller.manager;


import com.feiquan.tools.biz.IBizAiProviderConfigService;
import com.feiquan.tools.dto.api.WtResponse;
import com.feiquan.tools.dto.api.req.AiProviderCreateReq;
import com.feiquan.tools.dto.api.resp.AiProviderResp;
import com.feiquan.tools.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ai/provider")
@RequiredArgsConstructor
public class AiProviderConfigController {

    private final IBizAiProviderConfigService iBizAiProviderConfigService;

    @PostMapping("/create")
    public WtResponse<AiProviderResp> createAiProvider(@RequestBody @Validated AiProviderCreateReq createReq) {
        log.info("创建AI提供服务商请求参数：{}", createReq);
        AiProviderResp resp = iBizAiProviderConfigService.createAiProvider(createReq);
        log.info("创建AI提供服务商响应参数：{}", resp);
        return WtResponse.success(resp);
    }

    @PutMapping("/update")
    public WtResponse<AiProviderResp> updateAiProvider(@RequestBody @Validated AiProviderCreateReq updateReq) {
        log.info("更新AI提供服务商请求参数：{}", updateReq);
        AiProviderResp resp = iBizAiProviderConfigService.updateAiProvider(updateReq);
        log.info("更新AI提供服务商响应参数：{}", resp);
        return WtResponse.success(resp);
    }

    @PostMapping("/queryAll")
    public WtResponse<List<AiProviderResp>> queryAll() {
        log.info("查询全部AI提供服务商请求");
        List<AiProviderResp> resp = iBizAiProviderConfigService.queryAllAiProvider();
        log.info("查询全部AI提供服务商请求响应结果：{}", JsonUtil.toJson(resp));
        return WtResponse.success(resp);
    }

    @DeleteMapping("/delete")
    public WtResponse<Boolean> deleteAiProvider(@RequestParam("code") String code) {
        log.info("删除AI提供服务商请求参数：{}", code);
        Boolean resp = iBizAiProviderConfigService.deleteAiProvider(code);
        log.info("删除AI提供服务商请求结果：{}", resp);
        return WtResponse.success(resp);
    }
}
