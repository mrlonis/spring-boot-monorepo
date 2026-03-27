package com.mrlonis.xml.shared.model.jaxb;

import com.mrlonis.xml.shared.adapter.JaxbJavaTimeAdapters;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.jackson.Jacksonized;

@UtilityClass
public class JaxbJavaTimeModels {
    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JaxbJavaTimeNoZoneAccessTypeField extends BaseJaxbModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JaxbJavaTimeNoZoneAccessTypeNone extends BaseJaxbModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JaxbJavaTimeNoZoneAccessTypeProperty extends BaseJaxbModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JaxbJavaTimeNoZoneAccessTypePublicMember extends BaseJaxbModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JaxbJavaTimeZonedAccessTypeField extends BaseJaxbModel<ZonedDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JaxbJavaTimeZonedAccessTypeNone extends BaseJaxbModel<ZonedDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JaxbJavaTimeZonedAccessTypeProperty extends BaseJaxbModel<ZonedDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JaxbJavaTimeZonedAccessTypePublicMember extends BaseJaxbModel<ZonedDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }
}
