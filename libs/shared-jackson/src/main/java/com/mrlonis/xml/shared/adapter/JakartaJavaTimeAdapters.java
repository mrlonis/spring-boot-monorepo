package com.mrlonis.xml.shared.adapter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JakartaJavaTimeAdapters {
    public class JakartaLocalDateTimeAdapter extends BaseJakartaAdapter<LocalDateTime> {
        public JakartaLocalDateTimeAdapter() {
            super(LocalDateTime.class);
        }
    }

    public class JakartaZonedDateTimeAdapter extends BaseJakartaAdapter<ZonedDateTime> {
        public JakartaZonedDateTimeAdapter() {
            super(ZonedDateTime.class);
        }
    }
}
