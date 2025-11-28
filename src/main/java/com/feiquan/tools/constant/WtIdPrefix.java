package com.feiquan.tools.constant;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class WtIdPrefix {

    /// 为了避免重复，建议 value 和常量名一致
    /// 长度不能超过5
    public static final WtIdPrefix U =  new WtIdPrefix("U");
    public static final WtIdPrefix CU =  new WtIdPrefix("CU");

    private final String value;

    private WtIdPrefix(@NonNull String value) {
        value = value.trim();
        if (value.length() > 5) {
            value = value.substring(0, 5);
        }
        this.value = value;
    }

}
