package com.feiquan.tools.dto.api.req;

import com.feiquan.tools.repo.entity.AiProviderConfigPO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiProviderCreateReq {

    @NotBlank(message = "AI 提供商 code 不允许为空")
    @Length(max = 64, message = "AI 提供商 code 不允许超过64 个字符")
    private String code;
    @NotBlank(message = "AI 提供商名称不允许为空")
    @Length(max = 64)
    private String name;
    @NotBlank(message = "AI 提供商描述不允许为空")
    @Length(max = 64)
    private String description;
    @NotBlank(message = "AI 提供商 secretKey 不允许为空")
    @Length(max = 64)
    private String secretKey;

    public AiProviderConfigPO toPO() {
        return AiProviderConfigPO.builder()
                .providerCode(code)
                .providerName(name)
                .secretKey(secretKey)
                .providerDesc(description)
                .build();
    }
}
