package com.mrlonis.xml.shared.adapter;

import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

@UtilityClass
public class JakartaJodaTimeAdapters {
    public class JakartaLocalDateTimeAdapter extends BaseJakartaAdapter<LocalDateTime> {
        public JakartaLocalDateTimeAdapter() {
            super(LocalDateTime.class);
        }
    }

    public class JakartaDateTimeAdapter extends BaseJakartaAdapter<DateTime> {
        public JakartaDateTimeAdapter() {
            super(DateTime.class);
        }
    }
}
