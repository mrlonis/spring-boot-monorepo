package com.mrlonis.xml.shared.model.jackson.jakarta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.mrlonis.xml.shared.adapter.JakartaJodaTimeAdapters;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.jackson.Jacksonized;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

@UtilityClass
public class JacksonJakartaJodaTimeModels {
    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJodaTimeNoZoneAccessTypeField extends BaseJacksonJakartaModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJodaTimeNoZoneAccessTypeNone extends BaseJacksonJakartaModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJodaTimeNoZoneAccessTypeProperty extends BaseJacksonJakartaModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJodaTimeNoZoneAccessTypePublicMember
            extends BaseJacksonJakartaModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJodaTimeZonedAccessTypeField extends BaseJacksonJakartaModel<DateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaDateTimeAdapter.class)
        private DateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJodaTimeZonedAccessTypeNone extends BaseJacksonJakartaModel<DateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaDateTimeAdapter.class)
        private DateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJodaTimeZonedAccessTypeProperty extends BaseJacksonJakartaModel<DateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaDateTimeAdapter.class)
        private DateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJakartaJodaTimeZonedAccessTypePublicMember extends BaseJacksonJakartaModel<DateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaDateTimeAdapter.class)
        private DateTime date;
    }
}
