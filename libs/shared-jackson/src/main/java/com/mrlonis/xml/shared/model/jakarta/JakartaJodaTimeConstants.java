package com.mrlonis.xml.shared.model.jakarta;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.util.Constants;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JakartaJodaTimeConstants {
    private static final JakartaJodaTimeModels.JakartaJodaTimeNoZoneAccessTypeField
            JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD =
                    JakartaJodaTimeModels.JakartaJodaTimeNoZoneAccessTypeField.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JakartaJodaTimeModels.JakartaJodaTimeNoZoneAccessTypeNone
            JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE =
                    JakartaJodaTimeModels.JakartaJodaTimeNoZoneAccessTypeNone.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JakartaJodaTimeModels.JakartaJodaTimeNoZoneAccessTypeProperty
            JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY =
                    JakartaJodaTimeModels.JakartaJodaTimeNoZoneAccessTypeProperty.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JakartaJodaTimeModels.JakartaJodaTimeNoZoneAccessTypePublicMember
            JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER =
                    JakartaJodaTimeModels.JakartaJodaTimeNoZoneAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JAKARTA_JODA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD,
            JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER);

    private static final JakartaJodaTimeModels.JakartaJodaTimeZonedAccessTypeField
            JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_FIELD =
                    JakartaJodaTimeModels.JakartaJodaTimeZonedAccessTypeField.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JakartaJodaTimeModels.JakartaJodaTimeZonedAccessTypeNone
            JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_NONE =
                    JakartaJodaTimeModels.JakartaJodaTimeZonedAccessTypeNone.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JakartaJodaTimeModels.JakartaJodaTimeZonedAccessTypeProperty
            JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY =
                    JakartaJodaTimeModels.JakartaJodaTimeZonedAccessTypeProperty.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JakartaJodaTimeModels.JakartaJodaTimeZonedAccessTypePublicMember
            JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER =
                    JakartaJodaTimeModels.JakartaJodaTimeZonedAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JAKARTA_JODA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD,
            JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> JAKARTA_JODA_TIME_MAP = Map.of(
            TimeZoneIndicator.ZONED,
            JAKARTA_JODA_TIME_ZONED_MAP,
            TimeZoneIndicator.NO_ZONE,
            JAKARTA_JODA_TIME_NO_ZONE_MAP);
}
