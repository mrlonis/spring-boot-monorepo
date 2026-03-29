package com.mrlonis.xml.shared.model.jackson.jakarta;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.util.Constants;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonJakartaJavaTimeConstants {
    private static final JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeNoZoneAccessTypeField
            JACKSON_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_FIELD =
                    JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeNoZoneAccessTypeField.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeNoZoneAccessTypeNone
            JACKSON_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_NONE =
                    JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeNoZoneAccessTypeNone.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeNoZoneAccessTypeProperty
            JACKSON_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY =
                    JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeNoZoneAccessTypeProperty.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeNoZoneAccessTypePublicMember
            JACKSON_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER =
                    JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeNoZoneAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JAKARTA_JAVA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD, JACKSON_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE, JACKSON_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY, JACKSON_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER, JACKSON_JAKARTA_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER);

    private static final JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeZonedAccessTypeField
            JACKSON_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_FIELD =
                    JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeZonedAccessTypeField.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_ZONED_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeZonedAccessTypeNone
            JACKSON_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_NONE =
                    JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeZonedAccessTypeNone.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_ZONED_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeZonedAccessTypeProperty
            JACKSON_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_PROPERTY =
                    JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeZonedAccessTypeProperty.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_ZONED_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeZonedAccessTypePublicMember
            JACKSON_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER =
                    JacksonJakartaJavaTimeModels.JacksonJakartaJavaTimeZonedAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_ZONED_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JAKARTA_JAVA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD, JACKSON_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE, JACKSON_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY, JACKSON_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER, JACKSON_JAKARTA_JAVA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> JACKSON_JAKARTA_JAVA_TIME_MAP =
            Map.of(
                    TimeZoneIndicator.ZONED,
                    JACKSON_JAKARTA_JAVA_TIME_ZONED_MAP,
                    TimeZoneIndicator.NO_ZONE,
                    JACKSON_JAKARTA_JAVA_TIME_NO_ZONE_MAP);
}
