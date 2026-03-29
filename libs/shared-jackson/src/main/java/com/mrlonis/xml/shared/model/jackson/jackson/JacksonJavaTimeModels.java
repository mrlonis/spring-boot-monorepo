package com.mrlonis.xml.shared.model.jackson.jackson;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrlonis.xml.shared.adapter.JacksonJavaTimeDeserializers;
import com.mrlonis.xml.shared.adapter.JacksonSerializer;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.jackson.Jacksonized;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@UtilityClass
public class JacksonJavaTimeModels {
    @JacksonXmlRootElement(localName = "book")
    @JsonPropertyOrder({"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJavaTimeNoZone extends BaseJacksonModel<LocalDateTime> {
        @JsonSerialize(using = JacksonSerializer.class)
        @JsonDeserialize(using = JacksonJavaTimeDeserializers.JacksonLocalDateTimeDeserializer.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @JsonPropertyOrder({"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJavaTimeZoned extends BaseJacksonModel<ZonedDateTime> {
        @JsonSerialize(using = JacksonSerializer.class)
        @JsonDeserialize(using = JacksonJavaTimeDeserializers.JacksonZonedDateTimeDeserializer.class)
        private ZonedDateTime date;
    }
}
