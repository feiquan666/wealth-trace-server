package com.feiquan.tools.constant;

import com.feiquan.tools.dto.api.WtResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WtException extends RuntimeException {

    private String code;

    private List<WtResponse.ErrorDetail> errorDetails;

    public WtException(WtRespCode exceptionCode) {
        super(exceptionCode.msg());
        this.code = exceptionCode.code();
    }
}
