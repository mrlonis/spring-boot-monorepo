package com.mrlonis.xml.shared.model.pure.jaxb;

import static com.mrlonis.xml.shared.util.Constants.AUTHOR;
import static com.mrlonis.xml.shared.util.Constants.ID;
import static com.mrlonis.xml.shared.util.Constants.JODA_DATE_TIME;
import static com.mrlonis.xml.shared.util.Constants.JODA_LOCAL_DATE_TIME;
import static com.mrlonis.xml.shared.util.Constants.NAME;
import static com.mrlonis.xml.shared.util.Constants.TAGS;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.model.pure.jaxb.joda.PureJaxbJodaTimeNoZoneAccessTypeField;
import com.mrlonis.xml.shared.model.pure.jaxb.joda.PureJaxbJodaTimeNoZoneAccessTypeNone;
import com.mrlonis.xml.shared.model.pure.jaxb.joda.PureJaxbJodaTimeNoZoneAccessTypeProperty;
import com.mrlonis.xml.shared.model.pure.jaxb.joda.PureJaxbJodaTimeNoZoneAccessTypePublicMember;
import com.mrlonis.xml.shared.model.pure.jaxb.joda.PureJaxbJodaTimeZonedAccessTypeField;
import com.mrlonis.xml.shared.model.pure.jaxb.joda.PureJaxbJodaTimeZonedAccessTypeNone;
import com.mrlonis.xml.shared.model.pure.jaxb.joda.PureJaxbJodaTimeZonedAccessTypeProperty;
import com.mrlonis.xml.shared.model.pure.jaxb.joda.PureJaxbJodaTimeZonedAccessTypePublicMember;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PureJaxbJodaTimeConstants {
    private static final PureJaxbJodaTimeNoZoneAccessTypeField PURE_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD =
            PureJaxbJodaTimeNoZoneAccessTypeField.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJodaTimeNoZoneAccessTypeNone PURE_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE =
            PureJaxbJodaTimeNoZoneAccessTypeNone.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJodaTimeNoZoneAccessTypeProperty PURE_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY =
            PureJaxbJodaTimeNoZoneAccessTypeProperty.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJodaTimeNoZoneAccessTypePublicMember
            PURE_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER =
                    PureJaxbJodaTimeNoZoneAccessTypePublicMember.builder()
                            .id(ID)
                            .name(NAME)
                            .author(AUTHOR)
                            .date(JODA_LOCAL_DATE_TIME)
                            .tag(TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> PURE_JAXB_JODA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD,
            PURE_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            PURE_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            PURE_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            PURE_JAXB_JODA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER);

    private static final PureJaxbJodaTimeZonedAccessTypeField PURE_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_FIELD =
            PureJaxbJodaTimeZonedAccessTypeField.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJodaTimeZonedAccessTypeNone PURE_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_NONE =
            PureJaxbJodaTimeZonedAccessTypeNone.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJodaTimeZonedAccessTypeProperty PURE_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY =
            PureJaxbJodaTimeZonedAccessTypeProperty.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJodaTimeZonedAccessTypePublicMember
            PURE_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER = PureJaxbJodaTimeZonedAccessTypePublicMember.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JODA_DATE_TIME)
                    .tag(TAGS)
                    .build();

    private static final Map<XmlAccessorType, BaseModel<?>> PURE_JAXB_JODA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD,
            PURE_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            PURE_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            PURE_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            PURE_JAXB_JODA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> PURE_JAXB_JODA_TIME_MAP = Map.of(
            TimeZoneIndicator.ZONED,
            PURE_JAXB_JODA_TIME_ZONED_MAP,
            TimeZoneIndicator.NO_ZONE,
            PURE_JAXB_JODA_TIME_NO_ZONE_MAP);
}
