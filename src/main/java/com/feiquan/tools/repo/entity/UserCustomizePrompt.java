package com.feiquan.tools.repo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/// 用户自定义Prompt配置表
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_core.wt_user_customize_prompt")
@EqualsAndHashCode(callSuper = true)
public class UserCustomizePrompt extends BaseEntity {
    /// 用户ID
    @TableField("user_id")
    private String userId;
    /// 提示词内容
    @TableField("prompt_content")
    private String promptContent;
    /// 历史提示词内容
    @TableField("prompt_content_history")
    private String promptContentHistory;
}
