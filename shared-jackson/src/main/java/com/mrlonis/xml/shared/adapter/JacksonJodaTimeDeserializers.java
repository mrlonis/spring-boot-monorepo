package com.mrlonis.xml.shared.adapter;

import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

@UtilityClass
public class JacksonJodaTimeDeserializers {
    public class JacksonLocalDateTimeDeserializer extends BaseJacksonDeserializer<LocalDateTime> {
        public JacksonLocalDateTimeDeserializer() {
            super(LocalDateTime.class);
        }
    }

    public class JacksonDateTimeDeserializer extends BaseJacksonDeserializer<DateTime> {
        public JacksonDateTimeDeserializer() {
            super(DateTime.class);
        }
    }
}
