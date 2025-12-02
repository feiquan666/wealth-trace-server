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
