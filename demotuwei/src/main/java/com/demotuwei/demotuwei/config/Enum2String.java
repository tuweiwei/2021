package com.demotuwei.demotuwei.config;

import com.demotuwei.demotuwei.enums.HobbyEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Enum2String<I extends Number> extends JsonSerializer<List<BaseEnumOfKeyValue>> {
    @Override
    public void serialize(List<BaseEnumOfKeyValue> enums, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(enums.stream().map(el -> String.valueOf(el.getCode())).collect(Collectors.joining(",")));
        //jsonGenerator.writeStartArray(Arrays.asList(enums.stream().map(el -> String.valueOf(el.getCode())).collect(Collectors.joining(","))));
    }
}
