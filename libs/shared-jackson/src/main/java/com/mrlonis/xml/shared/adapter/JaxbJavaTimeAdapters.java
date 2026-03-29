package com.mrlonis.xml.shared.adapter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JaxbJavaTimeAdapters {
    public class JaxbLocalDateTimeAdapter extends BaseJaxbAdapter<LocalDateTime> {
        public JaxbLocalDateTimeAdapter() {
            super(LocalDateTime.class);
        }
    }

    public class JaxbZonedDateTimeAdapter extends BaseJaxbAdapter<ZonedDateTime> {
        public JaxbZonedDateTimeAdapter() {
            super(ZonedDateTime.class);
        }
    }
}
