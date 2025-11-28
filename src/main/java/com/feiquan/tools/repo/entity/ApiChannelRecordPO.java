package com.feiquan.tools.repo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_core.wt_api_channel_records")
@EqualsAndHashCode(callSuper = true)
public class ApiChannelRecordPO extends BaseEntity {
    /// API请求ID
    @TableField("api_req_id")
    private String apiReqId;
    /// 父请求ID
    @TableField("api_req_parent_id")
    private String apiReqParentId;
    /// 请求URL
    @TableField("api_req_url")
    private String apiReqUrl;
    /// 请求参数
    @TableField("api_req_parameters")
    private String apiReqParameters;
    /// 请求头
    @TableField("api_req_headers")
    private String apiReqHeaders;
    /// 请求体
    @TableField("api_req_body")
    private String apiReqBody;
    /// 响应头
    @TableField("api_resp_headers")
    private String apiRespHeaders;
    /// 响应体
    @TableField("api_resp_body")
    private String apiRespBody;
    /// HTTP响应码
    @TableField("api_resp_http_code")
    private String apiRespHttpCode;
    /// 业务响应码
    @TableField("api_resp_code")
    private String apiRespCode;
    /// 业务响应信息
    @TableField("api_resp_msg")
    private String apiRespMsg;
    /// 请求体大小(byte)
    @TableField("api_req_body_size")
    private String apiReqBodySize;
    /// 响应体大小(byte)
    @TableField("api_resp_body_size")
    private String apiRespBodySize;
}
