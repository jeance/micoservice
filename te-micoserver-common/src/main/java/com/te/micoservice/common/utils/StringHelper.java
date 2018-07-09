package com.te.micoservice.common.utils;

public class StringHelper {
    /**
     * 判断字符串是否为空或是空字符串
     *
     * @param value
     * @return boolean
     * @author hcw
     */
    public static boolean isNullOrEmpty(String value) {
        if (value == null || value.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}