package com.feiquan.tools.channel.deepseek.dto.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

/// 余额响应
public class DsBalanceInfoResp {
    /// 币种，可用：CNY、USD
    /// @see com.feiquan.tools.constant.WtCurrencyEnum
    private String currency;
    /// 总的可用余额：赠送余额+充值余额
    @JsonProperty("total_balance")
    private String totalBalance;
    /// 未过期的赠送余额
    @JsonProperty("granted_balance")
    private String grantedBalance;
    /// 充值余额
    @JsonProperty("topped_up_balance")
    private String toppedUpBalance;
}
