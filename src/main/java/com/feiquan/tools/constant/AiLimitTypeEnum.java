package com.feiquan.tools.constant;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum AiLimitTypeEnum {

    Undefined("undefined", "undefined"),
    Hour("hour", "基于小时"),
    Day("day", "基于天"),
    Month("month", "基于月"),

    ;

    private final String code;

    private final String desc;

    AiLimitTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AiLimitTypeEnum findByCode(String code) {
        for (AiLimitTypeEnum value : AiLimitTypeEnum.values()) {
            if (StringUtils.equals(value.getCode(), code)) {
                return value;
            }
        }
        return Undefined;
    }
}
