package com.feiquan.tools.util;

import com.google.common.collect.Lists;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

@Slf4j
public class HttpServletUtil {

    /// 打印请求参数、请求头和请求体
    public static void printRequestDetails(HttpServletRequest request, String body) {
        // 打印请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> params = new HashMap<>();
        parameterMap.forEach((key, value) -> params.put(key, value.length == 1 ? value[0] : value));
        log.info("请求参数: {}", params);
        // 打印请求头
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        log.info("请求头: {}", headers);
        // 打印请求体
        log.info("请求体: {}", body);
    }

    /// 获取查询参数
    public static String getOneQueryParameter(HttpServletRequest request, String key) {
        List<String> reqIds = getAllQueryParameter(request, key);
        if (CollectionUtils.isEmpty(reqIds)) {
            return null;
        }
        return reqIds.get(0);
    }

    /// 获取全部查询参数
    public static List<String> getAllQueryParameter(HttpServletRequest request, String key) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap == null || !parameterMap.containsKey(key)) {
            return null;
        }
        String[] reqIds = parameterMap.get(key);
        if (Objects.isNull(reqIds) || reqIds.length == 0) {
            return null;
        }
        return Lists.newArrayList(reqIds);
    }

    /// 获取一个 header 参数
    public static String getHeaderOneValue(HttpServletRequest request, String key) {
        List<String> values = getHeaderAllValue(request, key);
        if (CollectionUtils.isEmpty(values)) {
            return null;
        }
        return values.get(0);
    }

    /// 获取全部 header 参数
    public static List<String> getHeaderAllValue(HttpServletRequest request, String key) {
        Enumeration<String> headers = request.getHeaders(key);
        List<String> values = new ArrayList<>();
        while (Objects.nonNull(headers) && headers.hasMoreElements()) {
            values.add(headers.nextElement());
        }
        return values;
    }
}
