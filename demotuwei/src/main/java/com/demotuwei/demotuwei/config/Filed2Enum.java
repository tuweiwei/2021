package com.demotuwei.demotuwei.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Filed2Enum extends JsonDeserializer<Object> implements ContextualDeserializer {
    private JavaType targetClass;

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        if (jsonParser == null || jsonParser.getText() == null) {
            return null;
        }

        Class<?> rawClass = targetClass.getContentType().getRawClass();
        BaseEnumOfKeyValue[] enumConstants = (BaseEnumOfKeyValue[]) rawClass.getEnumConstants();

        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        if (treeNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) treeNode;
            List<BaseEnumOfKeyValue> ret = new ArrayList<>();
            for (int i = 0; i < arrayNode.size(); i++) {

                String nodeType = arrayNode.get(i).getNodeType().toString();
                Object val = "";
                switch (nodeType) {
                    case "STRING" : val = arrayNode.get(i).asText(); break;
                    case "NUMBER" : val = arrayNode.get(i).asInt(); break;
                    default:break;
                }

                for(BaseEnumOfKeyValue e : enumConstants) {
                    if(e.getCode().equals(val)) {
                        ret.add(e);
                    }
                }
            }
            return ret;

        } else {
            // 前端穿过来的值
//        String temp = jsonParser.getText();
//        String[] strings = StringUtils.delimitedListToStringArray(temp, ",");
//            List<HobbyEnum> hobbyEnumList = new ArrayList<>();
//            for (String s : strings) {
//                HobbyEnum byCode = HobbyEnum.getByCode(s);
//                hobbyEnumList.add(byCode);
//            }
//            return hobbyEnumList;
            return null;
        }

    }

    @Override
    public Class<?> handledType() {
        return List.class;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JavaType contextualType = deserializationContext.getContextualType();
        targetClass = contextualType;
        return this;
    }
}
