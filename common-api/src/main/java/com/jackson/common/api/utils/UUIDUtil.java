package com.jackson.common.api.utils;

import java.util.UUID;

/**
 * Create by: Jackson
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
