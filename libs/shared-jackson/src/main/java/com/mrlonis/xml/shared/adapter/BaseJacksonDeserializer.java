package com.mrlonis.xml.shared.adapter;

import com.mrlonis.xml.shared.time.TimeAdapterUtil;
import java.io.IOException;
import lombok.Getter;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

@Getter
public abstract class BaseJacksonDeserializer<T> extends ValueDeserializer<T> {
    private final Class<T> type;

    protected BaseJacksonDeserializer(Class<T> type) {
        this.type = type;
    }

    @Override
    public T deserialize(JsonParser jsonparser, DeserializationContext context) {
        try {
            return TimeAdapterUtil.deserialize(jsonparser, context, this.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
