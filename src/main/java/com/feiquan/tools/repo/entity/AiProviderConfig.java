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
public class AiProviderConfig extends BaseEntity {
    /// 供应商code
    @TableField("provider_code")
    private String provider_code;
    /// 供应商名称
    @TableField("provider_name")
    private String provider_name;
    /// 供应商秘钥
    @TableField("secret_key")
    private String secret_key;
    /// 供应商描述
    @TableField("provider_desc")
    private String provider_desc;
}
