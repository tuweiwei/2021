package com.demotuwei.demotuwei.uitl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

@Component
public class JsonUtil {

    public static ObjectMapper objectMapper;

    @Autowired
    public JsonUtil(ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }

    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static ArrayNode parseArray(String json) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            return (ArrayNode) jsonNode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObjectNode parseObject(String json) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            return (ObjectNode) jsonNode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> T parseObjct(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> parseList(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, tClass));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <K, V> Map<K, V> parseMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        CompletionStage completionStage = new CompletableFuture();
        completionStage.thenApply(Function.identity());
        return null;
    }
}

