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
@TableName("tb_core.wt_ai_provider_prompt_configs")
@EqualsAndHashCode(callSuper = true)
public class AiProviderPromptConfigPO extends BaseEntity {
    /// 供应商code
    @TableField("provider_code")
    private String provider_code;
    /// 提示词场景
    @TableField("prompt_scene")
    private String prompt_scene;
    /// 提示词内容
    @TableField("prompt_content")
    private String prompt_content;
    /// 备注
    @TableField("remark")
    private String remark;
}
