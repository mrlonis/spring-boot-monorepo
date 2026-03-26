package com.mrlonis.xml.shared.model.jackson.jackson;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.util.Constants;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonJavaTimeConstants {
    private static final JacksonJavaTimeModels.JacksonJavaTimeNoZone JACKSON_JAVA_TIME_NO_ZONE =
            JacksonJavaTimeModels.JacksonJavaTimeNoZone.builder()
                    .id(Constants.ID)
                    .name(Constants.NAME)
                    .author(Constants.AUTHOR)
                    .date(Constants.JAVA_TIME_LOCAL_DATE_TIME)
                    .tag(Constants.TAGS)
                    .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JAVA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD, JACKSON_JAVA_TIME_NO_ZONE,
            XmlAccessorType.NONE, JACKSON_JAVA_TIME_NO_ZONE,
            XmlAccessorType.PROPERTY, JACKSON_JAVA_TIME_NO_ZONE,
            XmlAccessorType.PUBLIC_MEMBER, JACKSON_JAVA_TIME_NO_ZONE);

    private static final JacksonJavaTimeModels.JacksonJavaTimeZoned JACKSON_JAVA_TIME_ZONED =
            JacksonJavaTimeModels.JacksonJavaTimeZoned.builder()
                    .id(Constants.ID)
                    .name(Constants.NAME)
                    .author(Constants.AUTHOR)
                    .date(Constants.JAVA_TIME_ZONED_DATE_TIME)
                    .tag(Constants.TAGS)
                    .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JAVA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD, JACKSON_JAVA_TIME_ZONED,
            XmlAccessorType.NONE, JACKSON_JAVA_TIME_ZONED,
            XmlAccessorType.PROPERTY, JACKSON_JAVA_TIME_ZONED,
            XmlAccessorType.PUBLIC_MEMBER, JACKSON_JAVA_TIME_ZONED);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> JACKSON_JAVA_TIME_MAP = Map.of(
            TimeZoneIndicator.ZONED,
            JACKSON_JAVA_TIME_ZONED_MAP,
            TimeZoneIndicator.NO_ZONE,
            JACKSON_JAVA_TIME_NO_ZONE_MAP);
}
