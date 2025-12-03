package com.feiquan.tools.channel.deepseek.dto.resp;

import lombok.Data;

/// deepseek 发生错误时的返回
@Data
public class DsErrorResp {
    private String code;
    private String message;
    private String type;
    private Object param;
}
