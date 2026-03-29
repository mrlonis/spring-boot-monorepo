package com.mrlonis.xml.shared.model.jackson.jaxb;

import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.util.Constants;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonJaxbJavaTimeConstants {
    private static final JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeNoZoneAccessTypeField
            JACKSON_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_FIELD =
                    JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeNoZoneAccessTypeField.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeNoZoneAccessTypeNone
            JACKSON_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_NONE =
                    JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeNoZoneAccessTypeNone.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeNoZoneAccessTypeProperty
            JACKSON_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY =
                    JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeNoZoneAccessTypeProperty.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeNoZoneAccessTypePublicMember
            JACKSON_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER =
                    JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeNoZoneAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_LOCAL_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JAXB_JAVA_TIME_NO_ZONE_MAP = Map.of(
            XmlAccessorType.FIELD, JACKSON_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE, JACKSON_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY, JACKSON_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER, JACKSON_JAXB_JAVA_TIME_NO_ZONE_ACCESS_TYPE_PUBLIC_MEMBER);

    private static final JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeZonedAccessTypeField
            JACKSON_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_FIELD =
                    JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeZonedAccessTypeField.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_ZONED_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeZonedAccessTypeNone
            JACKSON_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_NONE =
                    JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeZonedAccessTypeNone.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_ZONED_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeZonedAccessTypeProperty
            JACKSON_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PROPERTY =
                    JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeZonedAccessTypeProperty.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_ZONED_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();
    private static final JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeZonedAccessTypePublicMember
            JACKSON_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER =
                    JacksonJaxbJavaTimeModels.JacksonJaxbJavaTimeZonedAccessTypePublicMember.builder()
                            .id(Constants.ID)
                            .name(Constants.NAME)
                            .author(Constants.AUTHOR)
                            .date(Constants.JAVA_TIME_ZONED_DATE_TIME)
                            .tag(Constants.TAGS)
                            .build();

    private static final Map<XmlAccessorType, BaseModel<?>> JACKSON_JAXB_JAVA_TIME_ZONED_MAP = Map.of(
            XmlAccessorType.FIELD, JACKSON_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_FIELD,
            XmlAccessorType.NONE, JACKSON_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_NONE,
            XmlAccessorType.PROPERTY, JACKSON_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PROPERTY,
            XmlAccessorType.PUBLIC_MEMBER, JACKSON_JAXB_JAVA_TIME_ZONED_ACCESS_TYPE_PUBLIC_MEMBER);

    public static final Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>> JACKSON_JAXB_JAVA_TIME_MAP = Map.of(
            TimeZoneIndicator.ZONED,
            JACKSON_JAXB_JAVA_TIME_ZONED_MAP,
            TimeZoneIndicator.NO_ZONE,
            JACKSON_JAXB_JAVA_TIME_NO_ZONE_MAP);
}
