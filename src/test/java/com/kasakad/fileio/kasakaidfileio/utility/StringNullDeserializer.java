package com.kasakad.fileio.kasakaidfileio.utility;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.SneakyThrows;

public class StringNullDeserializer extends StdDeserializer<String> {

    public StringNullDeserializer() {
        super(String.class);
    }

    @SneakyThrows
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) {
        return "null".equals(p.getValueAsString()) ? null : p.getValueAsString();
    }
}
