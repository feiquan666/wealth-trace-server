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
@TableName("tb_core.wt_ai_limit_configs")
@EqualsAndHashCode(callSuper = true)
public class AiLimitConfig extends BaseEntity {
    /// 用户ID
    @TableField("user_id")
    private String userId;
    /// 限制类型
    /// @see com.feiquan.tools.constant.AiLimitTypeEnum
    @TableField("limit_type")
    private String limitType;
    /// 最大允许token数
    @TableField("max_tokens")
    private Integer maxTokens;
}
