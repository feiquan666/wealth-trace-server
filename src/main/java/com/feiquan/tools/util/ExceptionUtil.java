package com.feiquan.tools.util;

import com.feiquan.tools.constant.WtException;
import com.feiquan.tools.constant.WtRespCode;
import com.feiquan.tools.dto.api.WtResponse;
import com.google.common.collect.Lists;

import java.util.List;

public class ExceptionUtil {

    public static void throwException(String field, String msg, Object... args) {
        WtException wtException = new WtException(WtRespCode.Failed);
        List<WtResponse.ErrorDetail> errorDetails = Lists.newArrayList(
                WtResponse.ErrorDetail.of(field, msg, args)
        );
        wtException.setErrorDetails(errorDetails);
        throw wtException;
    }

    public static void throwException(boolean condition, String field, String msg, Object... args) {
        if (condition) {
            throwException(field, msg, args);
        }
    }

}
