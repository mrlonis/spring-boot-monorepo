package com.mrlonis.xml.shared.model.jackson.jakarta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.mrlonis.xml.shared.adapter.JakartaJavaTimeAdapters;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.jackson.Jacksonized;

@UtilityClass
public class JacksonJakartaJavaTimeModels {
    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJavaTimeNoZoneAccessTypeField extends BaseJacksonJakartaModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJavaTimeNoZoneAccessTypeNone extends BaseJacksonJakartaModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJavaTimeNoZoneAccessTypeProperty extends BaseJacksonJakartaModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJavaTimeNoZoneAccessTypePublicMember
            extends BaseJacksonJakartaModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJavaTimeZonedAccessTypeField extends BaseJacksonJakartaModel<ZonedDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJavaTimeZonedAccessTypeNone extends BaseJacksonJakartaModel<ZonedDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJavaTimeZonedAccessTypeProperty extends BaseJacksonJakartaModel<ZonedDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJavaTimeZonedAccessTypePublicMember
            extends BaseJacksonJakartaModel<ZonedDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }
}
