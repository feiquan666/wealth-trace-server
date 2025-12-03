package com.feiquan.tools.channel.deepseek.dto.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/// 查询余额响应
@Data
public class DsQueryBalanceResp {

    @JsonProperty("is_available")
    private Boolean isAvailable;
    @JsonProperty("balance_infos")
    private List<DsBalanceInfoResp> balanceInfos;
}
