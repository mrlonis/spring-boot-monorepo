package com.mrlonis.xml.shared.adapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mrlonis.xml.shared.time.TimeAdapterUtil;
import java.io.IOException;

public class JacksonSerializer<T> extends JsonSerializer<T> {
    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        TimeAdapterUtil.serialize(value, gen, arg2);
    }
}
