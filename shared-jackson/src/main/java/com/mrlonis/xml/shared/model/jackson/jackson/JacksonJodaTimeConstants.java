package com.mrlonis.xml.shared.model.jackson.jackson;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.util.Constants;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonJodaTimeConstants {
    private static final JacksonJodaTimeModels.JacksonJodaTimeNoZone JACKSON_JODA_TIME_NO_ZONE =
            JacksonJodaTimeModels.JacksonJodaTimeNoZone.builder()
                    .id(Constants.ID)
                    .name(Constants.NAME)
                    .author(Constants.AUTHOR)
                    .date(Constants.JODA_LOCAL_DATE_TIME)
                    .tag(Constants.TAGS)
                    .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JODA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD, JACKSON_JODA_TIME_NO_ZONE,
            XmlAccessorType.NONE, JACKSON_JODA_TIME_NO_ZONE,
            XmlAccessorType.PROPERTY, JACKSON_JODA_TIME_NO_ZONE,
            XmlAccessorType.PUBLIC_MEMBER, JACKSON_JODA_TIME_NO_ZONE);

    private static final JacksonJodaTimeModels.JacksonJodaTimeZoned JACKSON_JODA_TIME_ZONED =
            JacksonJodaTimeModels.JacksonJodaTimeZoned.builder()
                    .id(Constants.ID)
                    .name(Constants.NAME)
                    .author(Constants.AUTHOR)
                    .date(Constants.JODA_DATE_TIME)
                    .tag(Constants.TAGS)
                    .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JODA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD, JACKSON_JODA_TIME_ZONED,
            XmlAccessorType.NONE, JACKSON_JODA_TIME_ZONED,
            XmlAccessorType.PROPERTY, JACKSON_JODA_TIME_ZONED,
            XmlAccessorType.PUBLIC_MEMBER, JACKSON_JODA_TIME_ZONED);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> JACKSON_JODA_TIME_MAP = Map.of(
            TimeZoneIndicator.ZONED,
            JACKSON_JODA_TIME_ZONED_MAP,
            TimeZoneIndicator.NO_ZONE,
            JACKSON_JODA_TIME_NO_ZONE_MAP);
}
