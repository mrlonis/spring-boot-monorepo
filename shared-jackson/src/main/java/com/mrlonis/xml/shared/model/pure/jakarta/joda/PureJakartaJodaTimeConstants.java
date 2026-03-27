package com.mrlonis.xml.shared.model.pure.jakarta.joda;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.util.Constants;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PureJakartaJodaTimeConstants {
    private static final PureJakartaJodaTimeNoZoneAccessTypeField PURE_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD =
            PureJakartaJodaTimeNoZoneAccessTypeField.builder()
                    .id(Constants.ID)
                    .name(Constants.NAME)
                    .author(Constants.AUTHOR)
                    .date(Constants.JODA_LOCAL_DATE_TIME)
                    .tag(Constants.TAGS)
                    .build();
    private static final PureJakartaJodaTimeNoZoneAccessTypeNone PURE_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE =
            PureJakartaJodaTimeNoZoneAccessTypeNone.builder()
                    .id(Constants.ID)
                    .name(Constants.NAME)
                    .author(Constants.AUTHOR)
                    .date(Constants.JODA_LOCAL_DATE_TIME)
                    .tag(Constants.TAGS)
                    .build();
    private static final PureJakartaJodaTimeNoZoneAccessTypeProperty
            PURE_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY = PureJakartaJodaTimeNoZoneAccessTypeProperty.builder()
                    .id(Constants.ID)
                    .name(Constants.NAME)
                    .author(Constants.AUTHOR)
                    .date(Constants.JODA_LOCAL_DATE_TIME)
                    .tag(Constants.TAGS)
                    .build();
    private static final PureJakartaJodaTimeNoZoneAccessTypePublicMember
            PURE_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER =
                    PureJakartaJodaTimeNoZoneAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> PURE_JAKARTA_JODA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD,
            PURE_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            PURE_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            PURE_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            PURE_JAKARTA_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER);

    private static final PureJakartaJodaTimeZonedAccessTypeField PURE_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_FIELD =
            PureJakartaJodaTimeZonedAccessTypeField.builder()
                    .id(Constants.ID)
                    .name(Constants.NAME)
                    .author(Constants.AUTHOR)
                    .date(Constants.JODA_DATE_TIME)
                    .tag(Constants.TAGS)
                    .build();
    private static final PureJakartaJodaTimeZonedAccessTypeNone PURE_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_NONE =
            PureJakartaJodaTimeZonedAccessTypeNone.builder()
                    .id(Constants.ID)
                    .name(Constants.NAME)
                    .author(Constants.AUTHOR)
                    .date(Constants.JODA_DATE_TIME)
                    .tag(Constants.TAGS)
                    .build();
    private static final PureJakartaJodaTimeZonedAccessTypeProperty PURE_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY =
            PureJakartaJodaTimeZonedAccessTypeProperty.builder()
                    .id(Constants.ID)
                    .name(Constants.NAME)
                    .author(Constants.AUTHOR)
                    .date(Constants.JODA_DATE_TIME)
                    .tag(Constants.TAGS)
                    .build();
    private static final PureJakartaJodaTimeZonedAccessTypePublicMember
            PURE_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER =
                    PureJakartaJodaTimeZonedAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JODA_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> PURE_JAKARTA_JODA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD,
            PURE_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            PURE_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            PURE_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            PURE_JAKARTA_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> PURE_JAKARTA_JODA_TIME_MAP = Map.of(
            TimeZoneIndicator.ZONED,
            PURE_JAKARTA_JODA_TIME_ZONED_MAP,
            TimeZoneIndicator.NO_ZONE,
            PURE_JAKARTA_JODA_TIME_NO_ZONE_MAP);
}
