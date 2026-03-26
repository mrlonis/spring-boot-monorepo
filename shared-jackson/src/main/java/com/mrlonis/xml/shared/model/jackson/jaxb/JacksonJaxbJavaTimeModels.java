package com.mrlonis.xml.shared.model.jackson.jaxb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.mrlonis.xml.shared.adapter.JaxbJavaTimeAdapters;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.jackson.Jacksonized;

@UtilityClass
public class JacksonJaxbJavaTimeModels {
    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJavaTimeNoZoneAccessTypeField extends BaseJacksonJaxbModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJavaTimeNoZoneAccessTypeNone extends BaseJacksonJaxbModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJavaTimeNoZoneAccessTypeProperty extends BaseJacksonJaxbModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJavaTimeNoZoneAccessTypePublicMember extends BaseJacksonJaxbModel<LocalDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJavaTimeZonedAccessTypeField extends BaseJacksonJaxbModel<ZonedDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJavaTimeZonedAccessTypeNone extends BaseJacksonJaxbModel<ZonedDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJavaTimeZonedAccessTypeProperty extends BaseJacksonJaxbModel<ZonedDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJavaTimeZonedAccessTypePublicMember extends BaseJacksonJaxbModel<ZonedDateTime> {
        @JsonProperty
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }
}
