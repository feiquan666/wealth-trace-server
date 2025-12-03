package com.feiquan.tools.constant;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum WtCurrencyEnum {

    Undefined("undefined", "undefined"),
    CNY("CNY", "人民币"),
    USD("USD", "美元"),
    ;

    private final String code;

    private final String desc;

    WtCurrencyEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static WtCurrencyEnum findByCode(String code) {
        for (WtCurrencyEnum value : WtCurrencyEnum.values()) {
            if (StringUtils.equals(value.getCode(), code)) {
                return value;
            }
        }
        return Undefined;
    }
}
