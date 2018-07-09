package com.te.micoservice.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * 公共类
 * Date  2018/3/28
 * Time  下午4:39
 *
 * @author bingbing.wang@corp.elong.com
 */
public class CommonUtil {


    private static ObjectMapper MAPPER;

    static {
        MAPPER = generateMapper(JsonInclude.Include.ALWAYS);
    }

    public static String requestToString(Object value) {
        String resp = "";
        if (value != null) {
            try {
                resp = MAPPER.writeValueAsString(value);
            } catch (JsonProcessingException e) {
            //    PlatformLogger.error("CommonUtil.requestToString json convert is error", e);
                return resp;
            }
        }
        return resp;
    }

    /**
     * 将json通过类型转换成对象
     *
     * @param json  json字符串
     * @param clazz 泛型类型
     * @return 返回对象
     * @throws IOException
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return clazz.equals(String.class) ? (T) json : MAPPER.readValue(json, clazz);
    }

    /**
     * 将json通过类型转换成对象
     *
     * @param json          json字符串
     * @param typeReference 引用类型
     * @return 返回对象
     * @throws IOException
     */
    public static <T> T fromJson(String json, TypeReference<?> typeReference) throws IOException {
        return (T) (typeReference.getType().equals(String.class) ? json : MAPPER.readValue(json, typeReference));
    }


    /**
     * 将对象转换成json
     *
     * @param src 对象
     * @return 返回json字符串
     * @throws IOException
     */
    public static <T> String toJson(T src) throws IOException {
        return src instanceof String ? (String) src : MAPPER.writeValueAsString(src);
    }

    /**
     * 此方法将会吞掉json异常(但是会打印异常日志），适合在打印日志的场景
     * 出现异常时，返回""
     *
     * @param src
     * @return
     */
    public static <T> String toJsonString(T src) {
        String result = "";
        try {
            if (src != null) {
                result = src instanceof String ? (String) src : MAPPER.writeValueAsString(src);
            } else {
                result = "null";
            }
        } catch (Exception e) {
            //PlatformLogger.error("CommonUtil.toJsonString Exception", e);
            result = "";
        }
        return result;
    }

    /**
     * 将对象转换成json, 可以设置输出属性
     *
     * @param src       对象
     * @param inclusion 传入一个枚举值, 设置输出属性
     * @return 返回json字符串
     * @throws IOException
     */
    public static <T> String toJson(T src, JsonInclude.Include inclusion) throws IOException {
        if (src instanceof String) {
            return (String) src;
        } else {
            ObjectMapper customMapper = generateMapper(inclusion);
            return customMapper.writeValueAsString(src);
        }
    }

    /**
     * 将对象转换成json, 传入配置对象
     *
     * @param src    对象
     * @param mapper 配置对象
     * @return 返回json字符串
     * @throws IOException
     */
    public static <T> String toJson(T src, ObjectMapper mapper) throws IOException {
        if (null != mapper) {
            if (src instanceof String) {
                return (String) src;
            } else {
                return mapper.writeValueAsString(src);
            }
        } else {
            return null;
        }
    }

    public static ObjectMapper mapper() {
        return MAPPER;
    }


    private static ObjectMapper generateMapper(JsonInclude.Include inclusion) {

        ObjectMapper customMapper = new ObjectMapper();

        // 设置输出时包含属性的风格
        customMapper.setSerializationInclusion(inclusion);

        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        customMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 禁止使用int代表Enum的order()來反序列化Enum,非常危險
        customMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);

//        customMapper.configure(DeserializationFeature.FAIL_ON_EMPTY_BEANS,false);

        // 所有日期格式都统一为以下样式
        customMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        return customMapper;
    }
}
