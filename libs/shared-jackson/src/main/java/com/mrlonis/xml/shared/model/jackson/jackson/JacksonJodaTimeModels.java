package com.mrlonis.xml.shared.model.jackson.jackson;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrlonis.xml.shared.adapter.JacksonJodaTimeDeserializers;
import com.mrlonis.xml.shared.adapter.JacksonSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.jackson.Jacksonized;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@UtilityClass
public class JacksonJodaTimeModels {
    @JacksonXmlRootElement(localName = "book")
    @JsonPropertyOrder({"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJodaTimeNoZone extends BaseJacksonModel<LocalDateTime> {
        @JsonSerialize(using = JacksonSerializer.class)
        @JsonDeserialize(using = JacksonJodaTimeDeserializers.JacksonLocalDateTimeDeserializer.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @JsonPropertyOrder({"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJodaTimeZoned extends BaseJacksonModel<DateTime> {
        @JsonSerialize(using = JacksonSerializer.class)
        @JsonDeserialize(using = JacksonJodaTimeDeserializers.JacksonDateTimeDeserializer.class)
        private DateTime date;
    }
}
