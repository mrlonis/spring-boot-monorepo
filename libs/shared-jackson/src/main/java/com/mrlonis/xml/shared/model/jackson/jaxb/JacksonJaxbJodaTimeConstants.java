package com.mrlonis.xml.shared.model.jackson.jaxb;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.util.Constants;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonJaxbJodaTimeConstants {
    private static final JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeNoZoneAccessTypeField
            JACKSON_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD =
                    JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeNoZoneAccessTypeField.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeNoZoneAccessTypeNone
            JACKSON_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE =
                    JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeNoZoneAccessTypeNone.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeNoZoneAccessTypeProperty
            JACKSON_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY =
                    JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeNoZoneAccessTypeProperty.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeNoZoneAccessTypePublicMember
            JACKSON_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER =
                    JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeNoZoneAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JAXB_JODA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD, JACKSON_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE, JACKSON_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY, JACKSON_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER, JACKSON_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER);

    private static final JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeZonedAccessTypeField
            JACKSON_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_FIELD =
                    JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeZonedAccessTypeField.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeZonedAccessTypeNone
            JACKSON_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_NONE =
                    JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeZonedAccessTypeNone.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeZonedAccessTypeProperty
            JACKSON_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY =
                    JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeZonedAccessTypeProperty.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeZonedAccessTypePublicMember
            JACKSON_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER =
                    JacksonJaxbJodaTimeModels.JacksonJaxbJodaTimeZonedAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JAXB_JODA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD,
            JACKSON_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            JACKSON_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            JACKSON_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            JACKSON_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> JACKSON_JAXB_JODA_TIME_MAP = Map.of(
            TimeZoneIndicator.ZONED,
            JACKSON_JAXB_JODA_TIME_ZONED_MAP,
            TimeZoneIndicator.NO_ZONE,
            JACKSON_JAXB_JODA_TIME_NO_ZONE_MAP);
}
