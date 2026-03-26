package com.mrlonis.xml.shared.model.jakarta;

import com.mrlonis.xml.shared.adapter.JakartaJavaTimeAdapters;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
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
public class JakartaJavaTimeModels {
    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJavaTimeNoZoneAccessTypeField extends BaseJakartaModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJavaTimeNoZoneAccessTypeNone extends BaseJakartaModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJavaTimeNoZoneAccessTypeProperty extends BaseJakartaModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJavaTimeNoZoneAccessTypePublicMember extends BaseJakartaModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJavaTimeZonedAccessTypeField extends BaseJakartaModel<ZonedDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJavaTimeZonedAccessTypeNone extends BaseJakartaModel<ZonedDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJavaTimeZonedAccessTypeProperty extends BaseJakartaModel<ZonedDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJavaTimeZonedAccessTypePublicMember extends BaseJakartaModel<ZonedDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJavaTimeAdapters.JakartaZonedDateTimeAdapter.class)
        private ZonedDateTime date;
    }
}
