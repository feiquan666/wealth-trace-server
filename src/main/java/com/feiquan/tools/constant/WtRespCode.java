package com.feiquan.tools.constant;

import com.feiquan.tools.util.WtStringUtil;

import java.util.Objects;

public record WtRespCode(String code, String msg) {

    public static WtRespCode Succeed = new WtRespCode("0000", "succeed");
    public static WtRespCode ParameterExp = new WtRespCode("0400", "参数校验未通过");
    public static WtRespCode SystemExp = new WtRespCode("0500", "内部系统异常");
    public static WtRespCode Failed = new WtRespCode("9999", "failed");

    public static WtRespCode failedWithMsg(String msg, Object... args) {
        if (Objects.nonNull(args) && args.length > 0) {
            // 实现 {} 占位符替换
            return new WtRespCode(Failed.code(), WtStringUtil.formatWithBraces(msg, args));
        }
        return new WtRespCode(Failed.code(), msg);
    }

    public static WtRespCode failedWithException(Exception e) {
        String exCode;
        if (e instanceof WtException wtE) {
            exCode = wtE.getCode();
        } else {
            exCode = Failed.code();
        }
        return new WtRespCode(exCode, e.getMessage());
    }

    public WtRespCode createWithMsg(String msg) {
        return new WtRespCode(this.code(), msg);
    }

    public WtRespCode createWithException(Exception e) {
        return new WtRespCode(this.code(), e.getMessage());
    }

}
