package com.mrlonis.xml.shared.model.jackson.jaxb;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.mrlonis.xml.shared.adapter.JaxbJodaTimeAdapters;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.jackson.Jacksonized;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

@UtilityClass
public class JacksonJaxbJodaTimeModels {
    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJodaTimeNoZoneAccessTypeField extends BaseJacksonJaxbModel<LocalDateTime> {
        @JacksonXmlProperty
        @XmlJavaTypeAdapter(JaxbJodaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        @Getter(onMethod_ = {@JsonGetter})
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJodaTimeNoZoneAccessTypeNone extends BaseJacksonJaxbModel<LocalDateTime> {
        @JacksonXmlProperty
        @XmlJavaTypeAdapter(JaxbJodaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        @Getter(onMethod_ = {@JsonGetter})
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJodaTimeNoZoneAccessTypeProperty extends BaseJacksonJaxbModel<LocalDateTime> {
        @JacksonXmlProperty
        @XmlJavaTypeAdapter(JaxbJodaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        @Getter(onMethod_ = {@JsonGetter})
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJodaTimeNoZoneAccessTypePublicMember extends BaseJacksonJaxbModel<LocalDateTime> {
        @JacksonXmlProperty
        @XmlJavaTypeAdapter(JaxbJodaTimeAdapters.JaxbLocalDateTimeAdapter.class)
        @Getter(onMethod_ = {@JsonGetter})
        private LocalDateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJodaTimeZonedAccessTypeField extends BaseJacksonJaxbModel<DateTime> {
        @JacksonXmlProperty
        @XmlJavaTypeAdapter(JaxbJodaTimeAdapters.JaxbDateTimeAdapter.class)
        @Getter(onMethod_ = {@JsonGetter})
        private DateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJodaTimeZonedAccessTypeNone extends BaseJacksonJaxbModel<DateTime> {
        @JacksonXmlProperty
        @XmlJavaTypeAdapter(JaxbJodaTimeAdapters.JaxbDateTimeAdapter.class)
        @Getter(onMethod_ = {@JsonGetter})
        private DateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJodaTimeZonedAccessTypeProperty extends BaseJacksonJaxbModel<DateTime> {
        @JacksonXmlProperty
        @XmlJavaTypeAdapter(JaxbJodaTimeAdapters.JaxbDateTimeAdapter.class)
        @Getter(onMethod_ = {@JsonGetter})
        private DateTime date;
    }

    @JacksonXmlRootElement(localName = "book")
    @XmlAccessorType
    @XmlType(propOrder = {"id", "name", "date", "tags"})
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Jacksonized
    @SuperBuilder
    public static class JacksonJaxbJodaTimeZonedAccessTypePublicMember extends BaseJacksonJaxbModel<DateTime> {
        @JacksonXmlProperty
        @XmlJavaTypeAdapter(JaxbJodaTimeAdapters.JaxbDateTimeAdapter.class)
        @Getter(onMethod_ = {@JsonGetter})
        private DateTime date;
    }
}
