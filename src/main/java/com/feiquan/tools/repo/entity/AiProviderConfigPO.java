package com.feiquan.tools.repo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/// AI供应商配置表
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_core.wt_ai_provider_configs")
@EqualsAndHashCode(callSuper = true)
public class AiProviderConfigPO extends BaseEntity {
    /// 供应商code
    @TableField("provider_code")
    private String providerCode;
    /// 供应商名称
    @TableField("provider_name")
    private String providerName;
    /// 供应商秘钥
    @TableField("secret_key")
    private String secretKey;
    /// 供应商描述
    @TableField("provider_desc")
    private String providerDesc;
}
