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
@TableName("tb_core.wt_ai_call_records")
@EqualsAndHashCode(callSuper = true)
public class AiCallRecord extends BaseEntity {
    @TableField("user_id")
    private String userId;
    /// 供应商code
    /// 参考 tb_core.wt_ai_provider_configs.provider_code
    @TableField("provider_code")
    private String providerCode;
    /// API请求ID
    @TableField("request_id")
    private String requestId;
    /// 请求耗费token
    @TableField("request_tokens")
    private Integer requestTokens;
    /// 响应耗费token
    @TableField("response_tokens")
    private Integer responseTokens;
    /// 总耗费token
    @TableField("total_tokens")
    private Integer totalTokens;
    /// AI模型名称
    @TableField("ai_model")
    private String aiModel;
    /// 用户输入内容
    @TableField("user_input")
    private String userInput;
    /// AI 输出内容
    @TableField("ai_output")
    private String aiOutput;
    /// 调用类型
    /// @see com.feiquan.tools.constant.AiLimitCallTypeEnum
    @TableField("call_type")
    private String callType;
}
