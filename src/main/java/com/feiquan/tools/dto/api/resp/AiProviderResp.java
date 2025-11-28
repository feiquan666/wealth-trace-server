package com.feiquan.tools.dto.api.resp;

import com.feiquan.tools.repo.entity.AiProviderConfigPO;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiProviderResp {

    private String code;

    private String name;

    private String description;

    public static AiProviderResp fromPO(AiProviderConfigPO po) {
        if (Objects.isNull(po)) {
            return null;
        }
        return AiProviderResp.builder()
                .code(po.getProviderCode())
                .name(po.getProviderName())
                .description(po.getProviderDesc())
                .build();
    }

    public static List<AiProviderResp> fromPOList(List<AiProviderConfigPO> poList) {
        List<AiProviderResp> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(poList)) {
            return result;
        }
        for (AiProviderConfigPO po : poList) {
            if (Objects.isNull(po)) {
                continue;
            }
            result.add(AiProviderResp.fromPO(po));
        }
        return result;
    }
}
