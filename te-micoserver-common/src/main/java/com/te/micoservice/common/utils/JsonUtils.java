package com.te.micoservice.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hcw07605 on 2017/7/26.
 */
public class JsonUtils {

    /**
     * FastJson
     * model转json
     *@param model 类class
     *@return List<T>
     *@author hcw
     */
    public static <T> String modelToJson(T model) {
        return JSON.toJSONString(model, SerializerFeature.WriteDateUseDateFormat);
    }
    /**
     * FastJson
     * model转json
     *@param model 类class
     *@return List<T>
     *@author hcw
     */
    public static <T> String modelToJson(List<T> model) {
        return JSON.toJSONString(model, SerializerFeature.WriteDateUseDateFormat);
    }


    /**
     * FastJson
     * json解析
     *@param json
     *@return List<T>
     *@author hcw
     */
    public static com.alibaba.fastjson.JSONObject jsonToJSONObject(String json){
        return JSON.parseObject(json);
    }
    /**
     * FastJson
     * json解析
     *@param json
     *@return List<T>
     *@author hcw
     */
    public static com.alibaba.fastjson.JSONArray jsonToJSONArray(String json) {return JSON.parseArray(json);}
    /**
     * FastJson
     * json字符串转换成model<T>
     * 只能解析数组json
     *@param json
     *@param model 类class
     *@return List<T>
     *@author hcw
     */
    public static <T> T jsonToModel(String json,Class<T> model){
        return JSON.parseObject(json,model);
    }

    /**
     * FastJson
     * json字符串转换成List<T>model
     * 只能解析数组json
     *@param json
     *@param model 类class
     *@return List<T>
     *@author hcw
     */
    public static <T> List<T> jsonToModelList(String json, Class<T> model){
        return JSON.parseArray(json,model);
    }

    /**
     * FastJson
     * json字符串转换成List<T>model
     * 只能解析数组json
     *@param json
     *@param model 类class
     *@return List<T>
     *@author hcw
     */
    public static  List<Object> jsonToModelList(String json, Type[] model){
        return JSON.parseArray(json,model);
    }
}
