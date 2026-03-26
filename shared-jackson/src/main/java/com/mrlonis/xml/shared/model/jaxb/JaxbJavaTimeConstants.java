package com.mrlonis.xml.shared.model.jaxb;

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
public class JaxbJavaTimeConstants {
    private static final JaxbJavaTimeModels.JaxbJavaTimeNoZoneAccessTypeField JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_FIELD =
            JaxbJavaTimeModels.JaxbJavaTimeNoZoneAccessTypeField.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final JaxbJavaTimeModels.JaxbJavaTimeNoZoneAccessTypeNone JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_NONE =
            JaxbJavaTimeModels.JaxbJavaTimeNoZoneAccessTypeNone.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_LOCAL_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final JaxbJavaTimeModels.JaxbJavaTimeNoZoneAccessTypeProperty
            JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY =
                    JaxbJavaTimeModels.JaxbJavaTimeNoZoneAccessTypeProperty.builder()
                            .id(ID)
                            .name(NAME)
                            .author(AUTHOR)
                            .date(JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(TAGS)
                            .build();
    private static final JaxbJavaTimeModels.JaxbJavaTimeNoZoneAccessTypePublicMember
            JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER =
                    JaxbJavaTimeModels.JaxbJavaTimeNoZoneAccessTypePublicMember.builder()
                            .id(ID)
                            .name(NAME)
                            .author(AUTHOR)
                            .date(JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JAXB_JAVA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD,
            JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER);

    private static final JaxbJavaTimeModels.JaxbJavaTimeZonedAccessTypeField JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_FIELD =
            JaxbJavaTimeModels.JaxbJavaTimeZonedAccessTypeField.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_ZONED_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final JaxbJavaTimeModels.JaxbJavaTimeZonedAccessTypeNone JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_NONE =
            JaxbJavaTimeModels.JaxbJavaTimeZonedAccessTypeNone.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_ZONED_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final JaxbJavaTimeModels.JaxbJavaTimeZonedAccessTypeProperty
            JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PROPERTY = JaxbJavaTimeModels.JaxbJavaTimeZonedAccessTypeProperty.builder()
                    .id(ID)
                    .name(NAME)
                    .author(AUTHOR)
                    .date(JAVA_TIME_ZONED_DATE_TIME)
                    .tag(TAGS)
                    .build();
    private static final JaxbJavaTimeModels.JaxbJavaTimeZonedAccessTypePublicMember
            JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER =
                    JaxbJavaTimeModels.JaxbJavaTimeZonedAccessTypePublicMember.builder()
                            .id(ID)
                            .name(NAME)
                            .author(AUTHOR)
                            .date(JAVA_TIME_ZONED_DATE_TIME)
                            .tag(TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JAXB_JAVA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD,
            JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE,
            JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY,
            JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER,
            JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> JAXB_JAVA_TIME_MAP = Map.of(
            TimeZoneIndicator.ZONED, JAXB_JAVA_TIME_ZONED_MAP, TimeZoneIndicator.NO_ZONE, JAXB_JAVA_TIME_NO_ZONE_MAP);
}
