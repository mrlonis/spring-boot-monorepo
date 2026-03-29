package com.mrlonis.xml.shared.model.jaxb;

import static com.mrlonis.xml.shared.util.Constants.AUTHOR;
import static com.mrlonis.xml.shared.util.Constants.ID;
import static com.mrlonis.xml.shared.util.Constants.JODA_DATE_TIME;
import static com.mrlonis.xml.shared.util.Constants.JODA_LOCAL_DATE_TIME;
import static com.mrlonis.xml.shared.util.Constants.NAME;
import static com.mrlonis.xml.shared.util.Constants.TAGS;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JaxbJodaTimeConstants {
    private static final JaxbJodaTimeModels.JaxbJodaTimeNoZoneAccessTypeField JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD =
            JaxbJodaTimeModels.JaxbJodaTimeNoZoneAccessTypeField.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final JaxbJodaTimeModels.JaxbJodaTimeNoZoneAccessTypeNone JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE =
            JaxbJodaTimeModels.JaxbJodaTimeNoZoneAccessTypeNone.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final JaxbJodaTimeModels.JaxbJodaTimeNoZoneAccessTypeProperty
            JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY =
                    JaxbJodaTimeModels.JaxbJodaTimeNoZoneAccessTypeProperty.builder()
                            .id(ID)
                            .name(NAME)
                            .author(AUTHOR)
                            .date(JODA_LOCAL_DATE_TIME)
                            .tag(TAGS)
                            .build();
    private static final JaxbJodaTimeModels.JaxbJodaTimeNoZoneAccessTypePublicMember
            JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER =
                    JaxbJodaTimeModels.JaxbJodaTimeNoZoneAccessTypePublicMember.builder()
                            .id(ID)
                            .name(NAME)
                            .author(AUTHOR)
                            .date(JODA_LOCAL_DATE_TIME)
                            .tag(TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JAXB_JODA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD,
            JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER);

    private static final JaxbJodaTimeModels.JaxbJodaTimeZonedAccessTypeField JAXB_JODA_TIME_ZONED_ACCESS_TYPE_FIELD =
            JaxbJodaTimeModels.JaxbJodaTimeZonedAccessTypeField.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final JaxbJodaTimeModels.JaxbJodaTimeZonedAccessTypeNone JAXB_JODA_TIME_ZONED_ACCESS_TYPE_NONE =
            JaxbJodaTimeModels.JaxbJodaTimeZonedAccessTypeNone.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final JaxbJodaTimeModels.JaxbJodaTimeZonedAccessTypeProperty
            JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY = JaxbJodaTimeModels.JaxbJodaTimeZonedAccessTypeProperty.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final JaxbJodaTimeModels.JaxbJodaTimeZonedAccessTypePublicMember
            JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER =
                    JaxbJodaTimeModels.JaxbJodaTimeZonedAccessTypePublicMember.builder()
                            .id(ID)
                            .name(NAME)
                            .author(AUTHOR)
                            .date(JODA_DATE_TIME)
                            .tag(TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JAXB_JODA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD,
            JAXB_JODA_TIME_ZONED_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            JAXB_JODA_TIME_ZONED_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> JAXB_JODA_TIME_MAP = Map.of(
            TimeZoneIndicator.ZONED, JAXB_JODA_TIME_ZONED_MAP, TimeZoneIndicator.NO_ZONE, JAXB_JODA_TIME_NO_ZONE_MAP);
}
