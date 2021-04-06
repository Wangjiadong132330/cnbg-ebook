package com.cnbg.zs.ebook.common.lang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/6 12:47
* @Description
*/
public class JsonUtils {
private final static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return objectMapper;
    }
    /**
    * 转换为 JSON 字符串
    *
    * @param obj
    * @return
    * @throws Exception
    */
    public static String toJsonString(Object obj)  {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
    * 转换为 JavaBean
    *
    * @param jsonString
    * @param clazz
    * @return
    * @throws Exception
    */
    public static <T> T toJsonBean(String jsonString, Class<T> clazz) {
        try {
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            return objectMapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Map<String,Object> toJsonMap(String jsonString) {
        try {
            Map<String,Object> result = objectMapper.readValue(jsonString, Map.class);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String,Object> toJsonMap(Object jsonString) {
        try {
        Map<String,Object> result = objectMapper.convertValue(jsonString,Map.class);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static <T> List<T> toJsonList(String jsonString, Class<T> clazz){
        try {
                CollectionLikeType type  = objectMapper.getTypeFactory().constructCollectionLikeType(List.class,clazz);
                return objectMapper.readValue(jsonString,type);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
    }
}
