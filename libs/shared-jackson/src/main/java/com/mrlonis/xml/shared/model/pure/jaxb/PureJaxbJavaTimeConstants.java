package com.mrlonis.xml.shared.model.pure.jaxb;

import static com.mrlonis.xml.shared.util.Constants.AUTHOR;
import static com.mrlonis.xml.shared.util.Constants.ID;
import static com.mrlonis.xml.shared.util.Constants.JAVA_TIME_LOCAL_DATE_TIME;
import static com.mrlonis.xml.shared.util.Constants.JAVA_TIME_ZONED_DATE_TIME;
import static com.mrlonis.xml.shared.util.Constants.NAME;
import static com.mrlonis.xml.shared.util.Constants.TAGS;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.model.pure.jaxb.java.PureJaxbJavaTimeNoZoneAccessTypeField;
import com.mrlonis.xml.shared.model.pure.jaxb.java.PureJaxbJavaTimeNoZoneAccessTypeNone;
import com.mrlonis.xml.shared.model.pure.jaxb.java.PureJaxbJavaTimeNoZoneAccessTypeProperty;
import com.mrlonis.xml.shared.model.pure.jaxb.java.PureJaxbJavaTimeNoZoneAccessTypePublicMember;
import com.mrlonis.xml.shared.model.pure.jaxb.java.PureJaxbJavaTimeZonedAccessTypeField;
import com.mrlonis.xml.shared.model.pure.jaxb.java.PureJaxbJavaTimeZonedAccessTypeNone;
import com.mrlonis.xml.shared.model.pure.jaxb.java.PureJaxbJavaTimeZonedAccessTypeProperty;
import com.mrlonis.xml.shared.model.pure.jaxb.java.PureJaxbJavaTimeZonedAccessTypePublicMember;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PureJaxbJavaTimeConstants {
    private static final PureJaxbJavaTimeNoZoneAccessTypeField PURE_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_FIELD =
            PureJaxbJavaTimeNoZoneAccessTypeField.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJavaTimeNoZoneAccessTypeNone PURE_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_NONE =
            PureJaxbJavaTimeNoZoneAccessTypeNone.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJavaTimeNoZoneAccessTypeProperty PURE_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY =
            PureJaxbJavaTimeNoZoneAccessTypeProperty.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJavaTimeNoZoneAccessTypePublicMember
            PURE_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER =
                    PureJaxbJavaTimeNoZoneAccessTypePublicMember.builder()
                            .id(ID)
                            .name(NAME)
                            .author(AUTHOR)
                            .date(JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> PURE_JAXB_JAVA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD,
            PURE_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            PURE_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            PURE_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            PURE_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER);

    private static final PureJaxbJavaTimeZonedAccessTypeField PURE_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_FIELD =
            PureJaxbJavaTimeZonedAccessTypeField.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_ZONED_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJavaTimeZonedAccessTypeNone PURE_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_NONE =
            PureJaxbJavaTimeZonedAccessTypeNone.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_ZONED_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJavaTimeZonedAccessTypeProperty PURE_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PROPERTY =
            PureJaxbJavaTimeZonedAccessTypeProperty.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_ZONED_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final PureJaxbJavaTimeZonedAccessTypePublicMember
            PURE_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER = PureJaxbJavaTimeZonedAccessTypePublicMember.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_ZONED_DATE_TIME)
                    .tag(TAGS)
                    .build();

    private static final Map<XmlAccessorType, BaseModel<?>> PURE_JAXB_JAVA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD,
            PURE_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            PURE_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            PURE_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            PURE_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> PURE_JAXB_JAVA_TIME_MAP = Map.of(
            TimeZoneIndicator.ZONED,
            PURE_JAXB_JAVA_TIME_ZONED_MAP,
            TimeZoneIndicator.NO_ZONE,
            PURE_JAXB_JAVA_TIME_NO_ZONE_MAP);
}
