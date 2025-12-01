package com.feiquan.tools.util;

import com.feiquan.tools.constant.WtException;
import com.feiquan.tools.constant.WtRespCode;
import com.feiquan.tools.dto.api.WtResponse;
import com.google.common.collect.Lists;

import java.util.List;

public class ExceptionUtil {

    public static void throwException(WtRespCode wtRespCode) {
        throw new WtException(wtRespCode);
    }
    public static void throwException(boolean condition, WtRespCode wtRespCode) {
        if(condition){
            throwException(wtRespCode);
        }
    }

    public static void throwException(String msg, Object... args) {
        throwException(WtRespCode.failedWithMsg(msg, args));
    }

    public static void throwException(boolean condition, String msg, Object... args) {
        if (condition) {
            throwException(msg, args);
        }
    }

    public static void throwFieldException(String field, String msg, Object... args) {
        WtException wtException = new WtException(WtRespCode.Failed);
        List<WtResponse.ErrorDetail> errorDetails = Lists.newArrayList(
                WtResponse.ErrorDetail.of(field, msg, args)
        );
        wtException.setErrorDetails(errorDetails);
        throw wtException;
    }

    public static void throwFieldException(boolean condition, String field, String msg, Object... args) {
        if (condition) {
            throwFieldException(field, msg, args);
        }
    }

}
