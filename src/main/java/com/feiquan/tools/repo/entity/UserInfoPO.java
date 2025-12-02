package com.feiquan.tools.repo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

/// 用户信息表
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_core.wt_user_infos")
@EqualsAndHashCode(callSuper = true)
public class UserInfoPO extends BaseEntity {
    @TableField("user_id")
    private String userId;
    @TableField("user_customize_id")
    private String userCustomizeId;
    @TableField("nick_name")
    private String nickName;
    @TableField("avatar")
    private String avatar;
    @TableField("phone_prefix")
    private String phonePrefix;
    @TableField("phone")
    private String phone;
    @TableField("email")
    private String email;
    @TableField("register_time")
    private OffsetDateTime registerTime;
}
