package com.demotuwei.demotuwei.config;

import com.demotuwei.demotuwei.enums.HobbyEnum;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Convert extends JsonDeserializer<List<HobbyEnum>> {
    @Override
    public List<HobbyEnum> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String temp = jsonParser.getText();
        String[] strings = StringUtils.delimitedListToStringArray(temp, ",");


        List<HobbyEnum> hobbyEnumList = new ArrayList<>();
        for (String s : strings) {
            HobbyEnum byCode = HobbyEnum.getByCode(s);
            hobbyEnumList.add(byCode);
        }
        return hobbyEnumList;
    }

}
