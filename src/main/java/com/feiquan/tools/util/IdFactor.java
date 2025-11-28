package com.feiquan.tools.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.feiquan.tools.constant.WtIdPrefix;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class IdFactor {


    /// Snowflake，不同的前缀会生成不同的种子
    private static final ConcurrentHashMap<String, Snowflake> SNOWFLAKE_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    public static String nextId(@NonNull WtIdPrefix prefix) {
        String value = prefix.getValue();
        Snowflake snowflake = getSnowflake(value);
        long nextId = snowflake.nextId();
        return StringUtils.defaultString(value) + nextId;
    }

    public static String nextId() {
        return IdUtil.getSnowflakeNextIdStr();
    }

    public static Long nextIdLong() {
        return IdUtil.getSnowflakeNextId();
    }

    private static Snowflake getSnowflake(String shortCode) {
        Snowflake result;
        if (!SNOWFLAKE_CONCURRENT_HASH_MAP.containsKey(shortCode)) {
            synchronized (SNOWFLAKE_CONCURRENT_HASH_MAP) {
                result = new Snowflake(8, 16);
                SNOWFLAKE_CONCURRENT_HASH_MAP.putIfAbsent(shortCode, result);
            }
        }
        result = SNOWFLAKE_CONCURRENT_HASH_MAP.get(shortCode);
        return result;
    }
}