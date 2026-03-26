package com.mrlonis.xml.shared.model.pure.jakarta.java;

import static com.mrlonis.xml.shared.util.Constants.AUTHOR;
import static com.mrlonis.xml.shared.util.Constants.ID;
import static com.mrlonis.xml.shared.util.Constants.JAVA_TIME_LOCAL_DATE_TIME;
import static com.mrlonis.xml.shared.util.Constants.JAVA_TIME_ZONED_DATE_TIME;
import static com.mrlonis.xml.shared.util.Constants.NAME;
import static com.mrlonis.xml.shared.util.Constants.TAGS;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PureJakartaJavaTimeConstants {
    private static final PureJakartaJavaTimeNoZoneAccessTypeField PURE_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_FIELD =
            PureJakartaJavaTimeNoZoneAccessTypeField.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJakartaJavaTimeNoZoneAccessTypeNone PURE_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_NONE =
            PureJakartaJavaTimeNoZoneAccessTypeNone.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJakartaJavaTimeNoZoneAccessTypeProperty
            PURE_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY = PureJakartaJavaTimeNoZoneAccessTypeProperty.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJakartaJavaTimeNoZoneAccessTypePublicMember
            PURE_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER =
                    PureJakartaJavaTimeNoZoneAccessTypePublicMember.builder()
                            .id(ID)
                            .name(NAME)
                            .author(AUTHOR)
                            .date(JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> PURE_JAKARTA_JAVA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD,
            PURE_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            PURE_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            PURE_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            PURE_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER);

    private static final PureJakartaJavaTimeZonedAccessTypeField PURE_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_FIELD =
            PureJakartaJavaTimeZonedAccessTypeField.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_ZONED_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJakartaJavaTimeZonedAccessTypeNone PURE_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_NONE =
            PureJakartaJavaTimeZonedAccessTypeNone.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_ZONED_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJakartaJavaTimeZonedAccessTypeProperty PURE_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_PROPERTY =
            PureJakartaJavaTimeZonedAccessTypeProperty.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_ZONED_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJakartaJavaTimeZonedAccessTypePublicMember
            PURE_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER =
                    PureJakartaJavaTimeZonedAccessTypePublicMember.builder()
                            .id(ID)
                            .name(NAME)
                            .author(AUTHOR)
                            .date(JAVA_TIME_ZONED_DATE_TIME)
                            .tag(TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> PURE_JAKARTA_JAVA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD,
            PURE_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            PURE_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            PURE_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            PURE_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> PURE_JAKARTA_JAVA_TIME_MAP = Map.of(
            TimeZoneIndicator.ZONED,
            PURE_JAKARTA_JAVA_TIME_ZONED_MAP,
            TimeZoneIndicator.NO_ZONE,
            PURE_JAKARTA_JAVA_TIME_NO_ZONE_MAP);
}
