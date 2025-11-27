package com.feiquan.tools.constant;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum AiLimitCallTypeEnum {

    Undefined("undefined", "KYC Submission Time"),
    User("user", "用户"),
    System("system", "系统"),

    ;

    private final String code;

    private final String desc;

    AiLimitCallTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AiLimitCallTypeEnum findByCode(String code) {
        for (AiLimitCallTypeEnum value : AiLimitCallTypeEnum.values()) {
            if (StringUtils.equals(value.getCode(), code)) {
                return value;
            }
        }
        return Undefined;
    }
}
