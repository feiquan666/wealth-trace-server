package com.feiquan.tools.channel.deepseek.constant;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum DeepseekErrorCode {
    Undefined("undefined", "undefined"),
    E400("400", "原因：请求体格式错误。解决方法：请根据错误信息提示修改请求体"),
    E401("401", "原因：API key 错误，认证失败。解决方法：请检查您的 API key 是否正确，如没有 API key，请先 创建 API key"),
    E402("402", "原因：账号余额不足。解决方法：请确认账户余额，并前往 充值 页面进行充值"),
    E422("422", "原因：请求体参数错误。解决方法：请根据错误信息提示修改相关参数"),
    E429("429", "原因：请求速率（TPM 或 RPM）达到上限。解决方法：请合理规划您的请求速率。"),
    E500("500", "原因：服务器内部故障。解决方法：请等待后重试。若问题一直存在，请联系我们解决"),
    E503("503", "原因：服务器负载过高。解决方法：请稍后重试您的请求"),

    ;

    private final String code;

    private final String desc;

    DeepseekErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DeepseekErrorCode findByCode(String code) {
        for (DeepseekErrorCode value : DeepseekErrorCode.values()) {
            if (StringUtils.equals(value.getCode(), code)) {
                return value;
            }
        }
        return Undefined;
    }
}
