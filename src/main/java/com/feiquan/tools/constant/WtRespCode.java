package com.feiquan.tools.constant;

public record WtRespCode(String code, String msg) {

    public static WtRespCode Succeed = new WtRespCode("0000", "succeed");
    public static WtRespCode Failed = new WtRespCode("9999", "failed");

    public static WtRespCode failedWithMsg(String msg) {
        return new WtRespCode(Failed.code(), msg);
    }

    public static WtRespCode failedWithException(Exception e) {
        return new WtRespCode(Failed.code(), e.getMessage());
    }

    public WtRespCode createWithMsg(String msg) {
        return new WtRespCode(this.code(), msg);
    }

    public WtRespCode createWithException(Exception e) {
        return new WtRespCode(this.code(), e.getMessage());
    }

}
