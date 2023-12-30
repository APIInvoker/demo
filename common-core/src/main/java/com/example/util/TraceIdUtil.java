package com.example.util;

import java.util.UUID;

/**
 * @author zx
 * @since 2022/11/16 016
 */
public class TraceIdUtil
{
    public static String getTraceId()
    {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }
}
