package com.mrlonis.xml.shared.adapter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonJavaTimeDeserializers {
    public class JacksonLocalDateTimeDeserializer extends BaseJacksonDeserializer<LocalDateTime> {
        public JacksonLocalDateTimeDeserializer() {
            super(LocalDateTime.class);
        }
    }

    public class JacksonZonedDateTimeDeserializer extends BaseJacksonDeserializer<ZonedDateTime> {
        public JacksonZonedDateTimeDeserializer() {
            super(ZonedDateTime.class);
        }
    }
}
