package com.kasakad.fileio.kasakaidfileio.utility;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class BooleanDeserializer extends StdDeserializer<Boolean> {

    public BooleanDeserializer() {
        super(Boolean.class);
    }
    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if ("true".equals(p.getValueAsString().toLowerCase()) ||"false".equals(p.getValueAsString().toLowerCase())) return Boolean.valueOf(p.getValueAsString());
        return "1".equals(p.getValueAsString()) ? true : false;
    }
}
