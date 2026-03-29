package com.mrlonis.xml.shared.model.jakarta;

import com.mrlonis.xml.shared.adapter.JakartaJodaTimeAdapters;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
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
public class JakartaJodaTimeModels {
    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJodaTimeNoZoneAccessTypeField extends BaseJakartaModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJodaTimeNoZoneAccessTypeNone extends BaseJakartaModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJodaTimeNoZoneAccessTypeProperty extends BaseJakartaModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJodaTimeNoZoneAccessTypePublicMember extends BaseJakartaModel<LocalDateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaLocalDateTimeAdapter.class)
        private LocalDateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJodaTimeZonedAccessTypeField extends BaseJakartaModel<DateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaDateTimeAdapter.class)
        private DateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJodaTimeZonedAccessTypeNone extends BaseJakartaModel<DateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaDateTimeAdapter.class)
        private DateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJodaTimeZonedAccessTypeProperty extends BaseJakartaModel<DateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaDateTimeAdapter.class)
        private DateTime date;
    }

    @XmlRootElement(name = "book")
    @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JakartaJodaTimeZonedAccessTypePublicMember extends BaseJakartaModel<DateTime> {
        @XmlElement
        @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaDateTimeAdapter.class)
        private DateTime date;
    }
}
