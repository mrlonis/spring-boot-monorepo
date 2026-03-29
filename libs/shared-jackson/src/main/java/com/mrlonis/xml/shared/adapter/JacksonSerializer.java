package com.mrlonis.xml.shared.adapter;

import com.mrlonis.xml.shared.time.TimeAdapterUtil;
import java.io.IOException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

public class JacksonSerializer<T> extends ValueSerializer<T> {
    @Override
    public void serialize(T value, JsonGenerator gen, SerializationContext arg2) {
        try {
            TimeAdapterUtil.serialize(value, gen, arg2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
