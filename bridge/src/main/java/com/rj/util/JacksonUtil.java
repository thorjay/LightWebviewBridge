package com.rj.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JacksonUtil {
    private static volatile JacksonUtil instance;

    private static ObjectMapper om;

    public static JacksonUtil getInstance(){
        if(instance == null){
            synchronized (JacksonUtil.class){
                if (instance == null){
                    om = new ObjectMapper();
                    instance = new JacksonUtil();
                }
            }
        }
        return instance;
    }

    public <T>T strToBean(String json, Class<T> clazzType) throws JsonProcessingException {
        return om.readValue(json,clazzType);
    }

    public <T> T stream2Bean(InputStream json, Class<T> clazzType) throws IOException {
        return om.readValue(json,clazzType);
    }

    public String beanToStr(Object object){
        try {
            return om.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> strToList(String json, Class<T> clazzType) throws JsonProcessingException {
        JavaType javaType = om.getTypeFactory().constructCollectionType(List.class,clazzType);
        return om.readValue(json,javaType);
    }

    public <E> Set<E> strToSet(String json, Class<E> clazzType) throws JsonProcessingException {
        JavaType javaType = om.getTypeFactory().constructCollectionType(Set.class,clazzType);
        return om.readValue(json,javaType);
    }

    public <K,V> Map<K,V> strToMap(String json,Class<K> keyType,Class<V> valueType) throws JsonProcessingException {
        JavaType javaType = om.getTypeFactory().constructMapType(Map.class,keyType,valueType);
        return om.readValue(json,javaType);
    }

    public <K,V> Map<K,V> streamToMap(InputStream json,Class<K> keyType,Class<V> valueType) throws IOException {
        JavaType javaType = om.getTypeFactory().constructMapType(Map.class,keyType,valueType);
        return om.readValue(json,javaType);
    }



}
