package com.mrlonis.xml.shared.model.jackson.jakarta;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.util.Constants;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonJakartaJodaTimeConstants {
    private static final JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeNoZoneAccessTypeField
            JACKSON_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD =
                    JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeNoZoneAccessTypeField.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeNoZoneAccessTypeNone
            JACKSON_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE =
                    JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeNoZoneAccessTypeNone.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeNoZoneAccessTypeProperty
            JACKSON_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY =
                    JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeNoZoneAccessTypeProperty.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeNoZoneAccessTypePublicMember
            JACKSON_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER =
                    JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeNoZoneAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JAKARTA_JODA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD, JACKSON_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE, JACKSON_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY, JACKSON_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER, JACKSON_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER);

    private static final JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeZonedAccessTypeField
            JACKSON_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_FIELD =
                    JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeZonedAccessTypeField.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeZonedAccessTypeNone
            JACKSON_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_NONE =
                    JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeZonedAccessTypeNone.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeZonedAccessTypeProperty
            JACKSON_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY =
                    JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeZonedAccessTypeProperty.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeZonedAccessTypePublicMember
            JACKSON_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER =
                    JacksonJakartaJodaTimeModels.JacksonJakartaJodaTimeZonedAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JAKARTA_JODA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD,
            JACKSON_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            JACKSON_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            JACKSON_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            JACKSON_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> JACKSON_JAKARTA_JODA_TIME_MAP =
            Map.of(
                    TimeZoneIndicator.ZONED,
                    JACKSON_JAKARTA_JODA_TIME_ZONED_MAP,
                    TimeZoneIndicator.NO_ZONE,
                    JACKSON_JAKARTA_JODA_TIME_NO_ZONE_MAP);
}
