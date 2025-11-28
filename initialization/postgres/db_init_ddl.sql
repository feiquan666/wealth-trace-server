-- ========================================
-- 创建 schema
-- ========================================
CREATE SCHEMA IF NOT EXISTS tb_core;

DROP TABLE IF EXISTS tb_core.wt_user_infos;
DROP TABLE IF EXISTS tb_core.wt_ai_call_records;
DROP TABLE IF EXISTS tb_core.wt_ai_limit_configs;
DROP TABLE IF EXISTS tb_core.wt_ai_provider_configs;
DROP TABLE IF EXISTS tb_core.wt_ai_provider_prompt_configs;
DROP TABLE IF EXISTS tb_core.wt_user_customize_prompt;
DROP TABLE IF EXISTS tb_core.wt_api_channel_records;

-- 启用 pg_trgm 扩展（只需执行一次）
CREATE
    EXTENSION IF NOT EXISTS pg_trgm;

-- ========================================
-- 1.1 用户表: tb_core.wt_user_infos
-- ========================================
CREATE TABLE tb_core.wt_user_infos
(
    id                BIGSERIAL PRIMARY KEY,
    created_at        TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by        VARCHAR(64) NOT NULL DEFAULT '',
    updated_at        TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by        VARCHAR(64) NOT NULL DEFAULT '',
    created_trace_id  VARCHAR(64) NOT NULL DEFAULT '',
    updated_trace_id  VARCHAR(64) NOT NULL DEFAULT '',
    del_flag          CHAR(1)     NOT NULL DEFAULT 'N',
    user_id           VARCHAR(64) NOT NULL DEFAULT '',
    user_customize_id VARCHAR(64) NOT NULL DEFAULT '',
    nick_name         VARCHAR(64) NOT NULL DEFAULT '',
    avatar            VARCHAR(64) NOT NULL DEFAULT '',
    phone_prefix      VARCHAR(64) NOT NULL DEFAULT '',
    phone             VARCHAR(64) NOT NULL DEFAULT '',
    email             VARCHAR(64) NOT NULL DEFAULT '',
    register_time     TIMESTAMPTZ NOT NULL
);


-- 索引
CREATE UNIQUE INDEX idx_wt_user_infos_user_id ON tb_core.wt_user_infos (user_id);
CREATE UNIQUE INDEX idx_wt_user_infos_user_customize_id ON tb_core.wt_user_infos (user_customize_id);
CREATE INDEX idx_wt_user_infos_nick_name ON tb_core.wt_user_infos (nick_name);
CREATE INDEX idx_wt_user_infos_phone ON tb_core.wt_user_infos (phone);
CREATE INDEX idx_wt_user_infos_email ON tb_core.wt_user_infos (email);
CREATE INDEX idx_wt_user_infos_register_time ON tb_core.wt_user_infos (register_time);
CREATE INDEX idx_wt_user_infos_created_trace_id ON tb_core.wt_user_infos (created_trace_id);
CREATE INDEX idx_wt_user_infos_updated_trace_id ON tb_core.wt_user_infos (updated_trace_id);

-- trigram 模糊查询索引
CREATE INDEX idx_wt_user_infos_nick_name_trgm ON tb_core.wt_user_infos USING gin (nick_name gin_trgm_ops);
CREATE INDEX idx_wt_user_infos_phone_trgm ON tb_core.wt_user_infos USING gin (phone gin_trgm_ops);
CREATE INDEX idx_wt_user_infos_email_trgm ON tb_core.wt_user_infos USING gin (email gin_trgm_ops);

-- ========================================
-- 1.2 AI供应商配置表: tb_core.wt_ai_provider_configs
-- ========================================
CREATE TABLE tb_core.wt_ai_provider_configs
(
    id               BIGSERIAL PRIMARY KEY,
    created_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by       VARCHAR(64) NOT NULL DEFAULT '',
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by       VARCHAR(64) NOT NULL DEFAULT '',
    created_trace_id VARCHAR(64) NOT NULL DEFAULT '',
    updated_trace_id VARCHAR(64) NOT NULL DEFAULT '',
    del_flag         CHAR(1)     NOT NULL DEFAULT 'N',
    provider_code    VARCHAR(64) NOT NULL DEFAULT '',
    provider_name    VARCHAR(64) NOT NULL DEFAULT '',
    secret_key       VARCHAR(64) NOT NULL DEFAULT '',
    provider_desc    VARCHAR(64) NOT NULL DEFAULT ''
);


CREATE INDEX idx_wt_ai_provider_configs_code ON tb_core.wt_ai_provider_configs (provider_code);
CREATE INDEX idx_wt_ai_provider_configs_name ON tb_core.wt_ai_provider_configs (provider_name);
CREATE INDEX idx_wt_ai_provider_configs_desc ON tb_core.wt_ai_provider_configs (provider_desc);
CREATE INDEX idx_wt_ai_provider_configs_created_trace_id ON tb_core.wt_ai_provider_configs (created_trace_id);
CREATE INDEX idx_wt_ai_provider_configs_updated_trace_id ON tb_core.wt_ai_provider_configs (updated_trace_id);

-- trigram 模糊查询索引
CREATE INDEX idx_wt_ai_provider_configs_name_trgm ON tb_core.wt_ai_provider_configs USING gin (provider_name gin_trgm_ops);
CREATE INDEX idx_wt_ai_provider_configs_desc_trgm ON tb_core.wt_ai_provider_configs USING gin (provider_desc gin_trgm_ops);
-- ========================================
-- 1.3 AI 调用记录表: tb_core.wt_ai_call_records
-- ========================================
CREATE TABLE tb_core.wt_ai_call_records
(
    id               BIGSERIAL PRIMARY KEY,
    created_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by       VARCHAR(64) NOT NULL DEFAULT '',
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by       VARCHAR(64) NOT NULL DEFAULT '',
    created_trace_id VARCHAR(64) NOT NULL DEFAULT '',
    updated_trace_id VARCHAR(64) NOT NULL DEFAULT '',
    del_flag         CHAR(1)     NOT NULL DEFAULT 'N',
    user_id          VARCHAR(64) NOT NULL DEFAULT '',
    provider_code    VARCHAR(64) NOT NULL DEFAULT '',
    request_id       VARCHAR(64) NOT NULL DEFAULT '',
    request_tokens   INT         NOT NULL DEFAULT 0,
    response_tokens  INT         NOT NULL DEFAULT 0,
    total_tokens     INT         NOT NULL DEFAULT 0,
    ai_model         VARCHAR(64) NOT NULL DEFAULT '',
    user_input       TEXT        NOT NULL DEFAULT '',
    ai_output        TEXT        NOT NULL DEFAULT '',
    call_type        VARCHAR(64) NOT NULL DEFAULT 'user'
);

CREATE INDEX idx_wt_ai_call_records_user_id ON tb_core.wt_ai_call_records (user_id);
CREATE INDEX idx_wt_ai_call_records_provider_code ON tb_core.wt_ai_call_records (provider_code);
CREATE INDEX idx_wt_ai_call_records_request_id ON tb_core.wt_ai_call_records (request_id);
CREATE INDEX idx_wt_ai_call_records_created_trace_id ON tb_core.wt_ai_call_records (created_trace_id);
CREATE INDEX idx_wt_ai_call_records_updated_trace_id ON tb_core.wt_ai_call_records (updated_trace_id);

-- ========================================
-- 1.4 API 调用记录表: tb_core.wt_api_channel_records
-- ========================================
CREATE TABLE tb_core.wt_api_channel_records
(
    id                 BIGSERIAL PRIMARY KEY,
    created_at         TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by         VARCHAR(64) NOT NULL DEFAULT '',
    updated_at         TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by         VARCHAR(64) NOT NULL DEFAULT '',
    created_trace_id   VARCHAR(64) NOT NULL DEFAULT '',
    updated_trace_id   VARCHAR(64) NOT NULL DEFAULT '',
    del_flag           CHAR(1)     NOT NULL DEFAULT 'N',
    api_req_id         VARCHAR(64) NOT NULL DEFAULT '',
    api_req_parent_id  VARCHAR(64)          DEFAULT '',
    api_req_url        TEXT        NOT NULL DEFAULT '',
    api_req_parameters TEXT        NOT NULL DEFAULT '',
    api_req_headers    TEXT        NOT NULL DEFAULT '',
    api_req_body       TEXT        NOT NULL DEFAULT '',
    api_resp_headers   TEXT        NOT NULL DEFAULT '',
    api_resp_body      TEXT        NOT NULL DEFAULT '',
    api_resp_http_code INT         NOT NULL DEFAULT 200,
    api_resp_code      VARCHAR(64) NOT NULL DEFAULT '',
    api_resp_msg       TEXT        NOT NULL DEFAULT '',
    api_req_body_size  BIGINT      NOT NULL DEFAULT 0,
    api_resp_body_size BIGINT      NOT NULL DEFAULT 0
);


CREATE INDEX idx_wt_api_channel_records_req_id ON tb_core.wt_api_channel_records (api_req_id);
CREATE INDEX idx_wt_api_channel_records_parent_id ON tb_core.wt_api_channel_records (api_req_parent_id);
CREATE INDEX idx_wt_api_channel_records_created_trace_id ON tb_core.wt_api_channel_records (created_trace_id);
CREATE INDEX idx_wt_api_channel_records_updated_trace_id ON tb_core.wt_api_channel_records (updated_trace_id);

-- ========================================
-- 1.5 AI供应商 Prompt 配置表: tb_core.wt_ai_provider_prompt_configs
-- ========================================
CREATE TABLE tb_core.wt_ai_provider_prompt_configs
(
    id               BIGSERIAL PRIMARY KEY,
    created_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by       VARCHAR(64) NOT NULL DEFAULT '',
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by       VARCHAR(64) NOT NULL DEFAULT '',
    created_trace_id VARCHAR(64) NOT NULL DEFAULT '',
    updated_trace_id VARCHAR(64) NOT NULL DEFAULT '',
    del_flag         CHAR(1)     NOT NULL DEFAULT 'N',
    provider_code    VARCHAR(64) NOT NULL DEFAULT '',
    prompt_scene     VARCHAR(64) NOT NULL DEFAULT '',
    prompt_content   TEXT        NOT NULL DEFAULT '',
    remark           VARCHAR(64) NOT NULL DEFAULT ''
);


CREATE INDEX idx_wt_ai_provider_prompt_configs_code ON tb_core.wt_ai_provider_prompt_configs (provider_code);
CREATE INDEX idx_wt_ai_provider_prompt_configs_scene ON tb_core.wt_ai_provider_prompt_configs (prompt_scene);
CREATE INDEX idx_wt_ai_provider_prompt_configs_remark ON tb_core.wt_ai_provider_prompt_configs (remark);
CREATE INDEX idx_wt_ai_provider_prompt_configs_created_trace_id ON tb_core.wt_ai_provider_prompt_configs (created_trace_id);
CREATE INDEX idx_wt_ai_provider_prompt_configs_updated_trace_id ON tb_core.wt_ai_provider_prompt_configs (updated_trace_id);

-- trigram 模糊查询索引
CREATE INDEX idx_wt_ai_provider_prompt_configs_remark_trgm ON tb_core.wt_ai_provider_prompt_configs USING gin (remark gin_trgm_ops);

-- ========================================
-- 1.6 用户自定义 Prompt 配置表: tb_core.wt_user_customize_prompt
-- ========================================
CREATE TABLE tb_core.wt_user_customize_prompt
(
    id                     BIGSERIAL PRIMARY KEY,
    created_at             TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by             VARCHAR(64) NOT NULL DEFAULT '',
    updated_at             TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by             VARCHAR(64) NOT NULL DEFAULT '',
    created_trace_id       VARCHAR(64) NOT NULL DEFAULT '',
    updated_trace_id       VARCHAR(64) NOT NULL DEFAULT '',
    del_flag               CHAR(1)     NOT NULL DEFAULT 'N',
    user_id                VARCHAR(64) NOT NULL,
    prompt_content         TEXT        NOT NULL,
    prompt_content_history JSONB                DEFAULT '{}'::jsonb
);


CREATE INDEX idx_wt_user_customize_prompt_user_id ON tb_core.wt_user_customize_prompt (user_id);
CREATE INDEX idx_wt_user_customize_prompt_created_trace_id ON tb_core.wt_user_customize_prompt (created_trace_id);
CREATE INDEX idx_wt_user_customize_prompt_updated_trace_id ON tb_core.wt_user_customize_prompt (updated_trace_id);

-- ========================================
-- 1.7 用户 token 限制配置表: tb_core.wt_ai_limit_configs
-- ========================================
CREATE TABLE tb_core.wt_ai_limit_configs
(
    id               BIGSERIAL PRIMARY KEY,
    created_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by       VARCHAR(64) NOT NULL DEFAULT '',
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by       VARCHAR(64) NOT NULL DEFAULT '',
    created_trace_id VARCHAR(64) NOT NULL DEFAULT '',
    updated_trace_id VARCHAR(64) NOT NULL DEFAULT '',
    del_flag         CHAR(1)     NOT NULL DEFAULT 'N',
    user_id          VARCHAR(64) NOT NULL DEFAULT '',
    limit_type       VARCHAR(64) NOT NULL DEFAULT 'hour',
    max_tokens       INT         NOT NULL DEFAULT 0
);


CREATE INDEX idx_wt_ai_limit_configs_user_id ON tb_core.wt_ai_limit_configs (user_id);
CREATE INDEX idx_wt_ai_limit_configs_created_trace_id ON tb_core.wt_ai_limit_configs (created_trace_id);
CREATE INDEX idx_wt_ai_limit_configs_updated_trace_id ON tb_core.wt_ai_limit_configs (updated_trace_id);

COMMENT
    ON TABLE tb_core.wt_user_infos IS '用户核心信息表';
COMMENT
    ON COLUMN tb_core.wt_user_infos.created_at IS '创建时间';
COMMENT
    ON COLUMN tb_core.wt_user_infos.created_by IS '创建者';
COMMENT
    ON COLUMN tb_core.wt_user_infos.updated_at IS '更新时间';
COMMENT
    ON COLUMN tb_core.wt_user_infos.updated_by IS '更新者';
COMMENT
    ON COLUMN tb_core.wt_user_infos.created_trace_id IS '创建时分布式追踪id';
COMMENT
    ON COLUMN tb_core.wt_user_infos.updated_trace_id IS '更新时分布式追踪id';
COMMENT
    ON COLUMN tb_core.wt_user_infos.del_flag IS '删除标志';
COMMENT
    ON COLUMN tb_core.wt_user_infos.user_id IS '内部用户ID';
COMMENT
    ON COLUMN tb_core.wt_user_infos.user_customize_id IS '用户自定义ID';
COMMENT
    ON COLUMN tb_core.wt_user_infos.nick_name IS '用户昵称';
COMMENT
    ON COLUMN tb_core.wt_user_infos.avatar IS '用户头像文件key';
COMMENT
    ON COLUMN tb_core.wt_user_infos.phone_prefix IS '手机号前缀';
COMMENT
    ON COLUMN tb_core.wt_user_infos.phone IS '手机号';
COMMENT
    ON COLUMN tb_core.wt_user_infos.email IS '邮箱';
COMMENT
    ON COLUMN tb_core.wt_user_infos.register_time IS '注册时间';

COMMENT
    ON TABLE tb_core.wt_ai_provider_configs IS 'AI供应商配置表';
COMMENT
    ON COLUMN tb_core.wt_ai_provider_configs.provider_code IS '供应商code';
COMMENT
    ON COLUMN tb_core.wt_ai_provider_configs.provider_name IS '供应商名称';
COMMENT
    ON COLUMN tb_core.wt_ai_provider_configs.secret_key IS '供应商秘钥';
COMMENT
    ON COLUMN tb_core.wt_ai_provider_configs.provider_desc IS '供应商描述';


COMMENT
    ON TABLE tb_core.wt_ai_call_records IS 'AI调用记录表';
COMMENT
    ON COLUMN tb_core.wt_ai_call_records.user_id IS '用户ID';
COMMENT
    ON COLUMN tb_core.wt_ai_call_records.provider_code IS '供应商code';
COMMENT
    ON COLUMN tb_core.wt_ai_call_records.request_id IS 'API请求ID';
COMMENT
    ON COLUMN tb_core.wt_ai_call_records.request_tokens IS '请求耗费token';
COMMENT
    ON COLUMN tb_core.wt_ai_call_records.response_tokens IS '响应耗费token';
COMMENT
    ON COLUMN tb_core.wt_ai_call_records.total_tokens IS '总耗费token';
COMMENT
    ON COLUMN tb_core.wt_ai_call_records.ai_model IS 'AI模型名称';
COMMENT
    ON COLUMN tb_core.wt_ai_call_records.user_input IS '用户输入内容';
COMMENT
    ON COLUMN tb_core.wt_ai_call_records.ai_output IS 'AI 输出内容';
COMMENT
    ON COLUMN tb_core.wt_ai_call_records.call_type IS '调用类型: user/system';


COMMENT
    ON TABLE tb_core.wt_api_channel_records IS 'API调用记录表';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_req_id IS 'API请求ID';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_req_parent_id IS '父请求ID';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_req_url IS '请求URL';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_req_parameters IS '请求参数';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_req_headers IS '请求头';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_req_body IS '请求体';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_resp_headers IS '响应头';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_resp_body IS '响应体';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_resp_http_code IS 'HTTP响应码';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_resp_code IS '业务响应码';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_resp_msg IS '业务响应信息';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_req_body_size IS '请求体大小(byte)';
COMMENT
    ON COLUMN tb_core.wt_api_channel_records.api_resp_body_size IS '响应体大小(byte)';

COMMENT
    ON TABLE tb_core.wt_ai_provider_prompt_configs IS 'AI供应商Prompt配置表';
COMMENT
    ON COLUMN tb_core.wt_ai_provider_prompt_configs.provider_code IS '供应商code';
COMMENT
    ON COLUMN tb_core.wt_ai_provider_prompt_configs.prompt_scene IS '提示词场景';
COMMENT
    ON COLUMN tb_core.wt_ai_provider_prompt_configs.prompt_content IS '提示词内容';
COMMENT
    ON COLUMN tb_core.wt_ai_provider_prompt_configs.remark IS '备注';

COMMENT
    ON TABLE tb_core.wt_user_customize_prompt IS '用户自定义Prompt配置表';
COMMENT
    ON COLUMN tb_core.wt_user_customize_prompt.user_id IS '用户ID';
COMMENT
    ON COLUMN tb_core.wt_user_customize_prompt.prompt_content IS '提示词内容';
COMMENT
    ON COLUMN tb_core.wt_user_customize_prompt.prompt_content_history IS '历史提示词内容';

COMMENT
    ON TABLE tb_core.wt_ai_limit_configs IS '用户Token限制配置表';
COMMENT
    ON COLUMN tb_core.wt_ai_limit_configs.user_id IS '用户ID';
COMMENT
    ON COLUMN tb_core.wt_ai_limit_configs.limit_type IS '限制类型: hour/day/month';
COMMENT
    ON COLUMN tb_core.wt_ai_limit_configs.max_tokens IS '最大允许token数';