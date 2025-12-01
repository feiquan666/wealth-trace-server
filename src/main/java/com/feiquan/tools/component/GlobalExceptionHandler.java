package com.feiquan.tools.component;

import com.feiquan.tools.constant.WtException;
import com.feiquan.tools.constant.WtRespCode;
import com.feiquan.tools.dto.api.WtResponse;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WtResponse<?> handleValidationException(MethodArgumentNotValidException ex) {
        // 收集字段错误列表
        List<WtResponse.ErrorDetail> errorDetails = Lists.newArrayList();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err -> {
                    WtResponse.ErrorDetail detail = WtResponse.ErrorDetail.of(err.getField(), err.getDefaultMessage());
                    errorDetails.add(detail);
                });
        // 返回统一失败响应，包含错误明细
        return WtResponse.failed(WtRespCode.ParameterExp, errorDetails);
    }

    /**
     * 捕获自定义业务异常（可选）
     */
    @ExceptionHandler(WtException.class)
    public WtResponse<?> handleBusinessException(WtException ex) {
        return WtResponse.failed(WtRespCode.failedWithException(ex), ex.getErrorDetails());
    }

    /**
     * 捕获所有未处理的异常
     */
    @ExceptionHandler(Exception.class)
    public WtResponse<?> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return WtResponse.failed(WtRespCode.SystemExp);
    }
}
