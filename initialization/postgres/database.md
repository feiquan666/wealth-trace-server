# 1、数据库初始化文档

**创建docker容器**

```shell

docker run -d \
  --name postgres16_11 \
  -e POSTGRES_USER=postgres_user \
  -e POSTGRES_PASSWORD='hfdD4Frjs#ds.dR3Sw' \
  -e POSTGRES_DB=postgres \
  -v /Users/{user}/homework/wealth_trace/data/postgres16.11/data:/var/lib/postgresql/data \
  -p 6000:5432 \
  postgres:16.11
```
**创建数据库**
```shell

CREATE DATABASE wealth_trace_dev
    OWNER postgres_user
    ENCODING 'UTF8'
    TEMPLATE template0
    CONNECTION LIMIT 500;

```
存放在 `tb_core` schema 中.
所有表都有以下字段，且非空(除非另有声明)，会有合适的默认值，对于字符串默认为空字符串，布尔类型默认为 false，时间类型都使用 utc带时区，且精确到6位，如果时间类型允许为空的话则允许为 null。需要根据我的说明给每个字段加入备注。字段如下：

1. `id` 自增主键 id，使用 bigint
2. `created_at` 创建时间，数据库需要自动生成
3. `created_by` 创建者，长度 64
4. `updated_at` 更新时间，数据库需要自动生成
5. `updated_by` 更新者，长度 64
6. `created_trace_id` 创建时的分布式追踪 id，建立索引
7. `updated_trace_id` 更新时的分布式追踪 id，建立索引
8. `del_flag` 是否删除标志
## 1.1、用户表：wt_user_infos
主要存储用户核心信息，字段如下，字段如下：

1. `user_id` 用户内部 id，长度64，对用户不可见，建立唯一索引
2. `user_customize_id` 用户自定义 id，长度64，用户看到的 id，相对来说比较容易记忆，支持自己修改，建立唯一索引
3. `nick_name` 用户昵称，长度 64，建立索引，支持模糊查询
4. `avatar` 用户头像，长度 64，存储的是文件 key
5. `phone_prefix` 手机号前缀，固定长度4，不足的前面会补0
6. `phone` 手机号，长度32，建立索引，支持模糊查询
7. `email` 邮箱，长度64，建立索引，支持模糊查询
8. `register_time` 注册时间，建立索引

## 1.2、AI 供应商配置表：wt_ai_provider_configs

1. `provider_code` 供应商 code，长度64，建立唯一索引
2. `provider_name` 供应商名称，长度64，建立索引，用于模糊查询
3. `secret_key` 供应商秘钥，长度128
4. `provider_desc` 供应商描述，长度255，建立索引，用于模糊查询

## 1.3、AI 调用记录表：wt_ai_call_records

1. `user_id` 用户 id，长度 64
2. `provider_code` 供应商 code，长度64
3. `request_id` 关联的API请求 id，长度64
4. `request_tokens` 请求耗费 token 数量
5. `response_tokens` 响应耗费 token 数量
6. `total_tokens` 总耗费 token 数量
7. `ai_model` AI 模型名称，长度64
8. `user_input` 用户输入内容，text 类型
9. `call_type` 调用类型：user、system

## 1.4、API 调用记录表：wt_api_channel_records

1. `api_req_id` api 请求 id，长度64
2. `api_req_parent_id` 父请求 id，用于关联一组 api 请求记录
3. `api_req_url` 请求 url，text 类型
4. `api_req_parameters` 请求参数，text 类型
5. `api_req_headers` 请求头，text 类型
6. `api_req_body` 请求体，text 类型
7. `api_resp_headers` 响应头，text 类型
8. `api_resp_body` 响应体，text 类型
9. `api_resp_http_code` 响应 code，http 层面
10. `api_resp_code` api 业务层面响应 code，长度64
11. `api_resp_msg` api 业务层面响应 msg，text 类型
12. `api_req_body_size` 请求体大小，单位：byte
13. `api_resp_body_size` 响应体大小，单位：byte

## 1.5、AI 供应商 prompt 配置表：wt_ai_provider_prompt_configs

1. `provider_code` 供应商 code，长度 64
2. `prompt_scene` 提示词场景，长度 64
3. `prompt_content` 提示词内容，text类型
4. `remark` 备注，长度255

## 1.6、用户自定义 prompt 配置表：wt_user_customize_prompt

1. `user_id` 用户 id，长度64
2. `prompt_content` 提示词内容，text 类型
3. `prompt_content_history` 历史提示词内容，jsonb类型

## 1.7、用户 token 限制配置表：wt_ai_limit_configs

1. `user_id` 用户 id，长度 64
2. `limit_type` 限制类型：hour、day、month
3. `max_tokens` 最大允许 token 数



