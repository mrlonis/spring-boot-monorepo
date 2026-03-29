package com.mrlonis.xml.shared.adapter;

import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

@UtilityClass
public class JaxbJodaTimeAdapters {
    public class JaxbLocalDateTimeAdapter extends BaseJaxbAdapter<LocalDateTime> {
        public JaxbLocalDateTimeAdapter() {
            super(LocalDateTime.class);
        }
    }

    public class JaxbDateTimeAdapter extends BaseJaxbAdapter<DateTime> {
        public JaxbDateTimeAdapter() {
            super(DateTime.class);
        }
    }
}
