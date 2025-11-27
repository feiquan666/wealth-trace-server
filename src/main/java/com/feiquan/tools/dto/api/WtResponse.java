package com.feiquan.tools.dto.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.feiquan.tools.constant.WtRespCode;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WtResponse<T> {

    /// 响应码
    /// @see com.feiquan.tools.constant.WtRespCode
    private String code;
    private String msg;
    private T data;

    /// 分页查询时返回总记录数
    private Long total;
    /// 是否有更多记录
    private Boolean hasMore;
    /// 错误详情
    private List<ErrorDetail> errorDetails;

    public static <T> WtResponse<T> success(T data) {
        WtRespCode succeed = WtRespCode.Succeed;
        return WtResponse.<T>builder()
                .code(succeed.code())
                .msg(succeed.msg())
                .data(data)
                .build();
    }

    public static <T> WtResponse<List<T>> successPage(IPage<T> page) {
        if (Objects.isNull(page)) {
            return success(null);
        }
        WtResponse<List<T>> pageResp = success(page.getRecords());
        long total = page.getTotal();
        long pages = page.getPages();
        long current = page.getCurrent();
        pageResp.setTotal(total);
        pageResp.setHasMore(current < pages);
        return pageResp;
    }

    public static <T> WtResponse<T> failed(@NonNull WtRespCode wtRespCode) {
        return WtResponse.<T>builder()
                .code(wtRespCode.code())
                .msg(wtRespCode.msg())
                .build();
    }

    public static <T> WtResponse<T> failed(@NonNull WtRespCode wtRespCode, List<ErrorDetail> errorDetails) {
        return WtResponse.<T>builder()
                .code(wtRespCode.code())
                .msg(wtRespCode.msg())
                .errorDetails(errorDetails)
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorDetail {
        private String field;
        private String msg;
    }

}
