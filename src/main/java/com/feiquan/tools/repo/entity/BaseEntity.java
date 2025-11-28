package com.feiquan.tools.repo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("created_by")
    private String createdBy;
    @TableField("created_at")
    private OffsetDateTime createdAt;
    @TableField("updated_by")
    private String updatedBy;
    @TableField("updated_at")
    private OffsetDateTime updatedAt;
    @TableField("created_trace_id")
    private String createdTraceId;
    @TableField("updated_trace_id")
    private String updatedTraceId;
    @TableField("del_flag")
    private String delFlag;
}
