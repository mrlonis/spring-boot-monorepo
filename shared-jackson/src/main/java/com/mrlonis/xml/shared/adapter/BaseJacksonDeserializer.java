package com.mrlonis.xml.shared.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.mrlonis.xml.shared.time.TimeAdapterUtil;
import java.io.IOException;
import lombok.Getter;

@Getter
public abstract class BaseJacksonDeserializer<T> extends JsonDeserializer<T> {
    private final Class<T> type;

    protected BaseJacksonDeserializer(Class<T> type) {
        this.type = type;
    }

    @Override
    public T deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
        return TimeAdapterUtil.deserialize(jsonparser, context, this.getType());
    }
}
