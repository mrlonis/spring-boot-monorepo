package com.mrlonis.xml.shared.test;

import com.mrlonis.xml.shared.enums.AnnotationLibrary;
import com.mrlonis.xml.shared.enums.TimeLibrary;
import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.params.provider.Arguments;

@UtilityClass
public class TestUtils {
    public static Stream<Arguments> generateTestArguments(
            boolean includeJaxb,
            boolean includeJakarta,
            boolean includeJaxbJackson,
            boolean includeJakartaJackson,
            boolean includeJackson,
            boolean includePureJaxb,
            boolean includePureJakarta) {
        List<Arguments> arguments = new ArrayList<>();
        if (includeJaxb) {
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.FIELD, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.NONE, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.PROPERTY, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.PUBLIC_MEMBER, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.FIELD, TimeLibrary.JODA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.NONE, TimeLibrary.JODA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.PROPERTY, TimeLibrary.JODA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.FIELD, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.NONE, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.PROPERTY, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.PUBLIC_MEMBER, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.FIELD, TimeLibrary.JAVA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.NONE, TimeLibrary.JAVA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB, XmlAccessorType.PROPERTY, TimeLibrary.JAVA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAXB,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
        }
        if (includeJakarta) {
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.FIELD, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.NONE, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.PROPERTY, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.FIELD, TimeLibrary.JODA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.NONE, TimeLibrary.JODA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.PROPERTY, TimeLibrary.JODA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.FIELD, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.NONE, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.PROPERTY, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.FIELD, TimeLibrary.JAVA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.NONE, TimeLibrary.JAVA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA, XmlAccessorType.PROPERTY, TimeLibrary.JAVA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
        }
        if (includeJaxbJackson) {
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB, XmlAccessorType.FIELD, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB, XmlAccessorType.NONE, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB, XmlAccessorType.NONE, TimeLibrary.JODA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB, XmlAccessorType.FIELD, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB, XmlAccessorType.NONE, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB, XmlAccessorType.NONE, TimeLibrary.JAVA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAXB,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
        }
        if (includeJakartaJackson) {
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.NONE,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.NONE,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.NONE,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.NONE,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
        }
        if (includeJackson) {
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.NONE,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.NONE,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.NONE,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.NONE,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.JACKSON_JACKSON,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
        }
        if (includePureJaxb) {
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB, XmlAccessorType.FIELD, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB, XmlAccessorType.NONE, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB, XmlAccessorType.PROPERTY, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB, XmlAccessorType.FIELD, TimeLibrary.JODA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB, XmlAccessorType.NONE, TimeLibrary.JODA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB, XmlAccessorType.FIELD, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB, XmlAccessorType.NONE, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB, XmlAccessorType.PROPERTY, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB, XmlAccessorType.FIELD, TimeLibrary.JAVA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB, XmlAccessorType.NONE, TimeLibrary.JAVA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAXB,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
        }
        if (includePureJakarta) {
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA, XmlAccessorType.FIELD, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA, XmlAccessorType.NONE, TimeLibrary.JODA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA, XmlAccessorType.NONE, TimeLibrary.JODA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JODA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA, XmlAccessorType.FIELD, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA, XmlAccessorType.NONE, TimeLibrary.JAVA, TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.ZONED));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA,
                    XmlAccessorType.FIELD,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA, XmlAccessorType.NONE, TimeLibrary.JAVA, TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA,
                    XmlAccessorType.PROPERTY,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
            arguments.add(Arguments.arguments(
                    AnnotationLibrary.PURE_JAKARTA,
                    XmlAccessorType.PUBLIC_MEMBER,
                    TimeLibrary.JAVA,
                    TimeZoneIndicator.NO_ZONE));
        }
        return arguments.stream();
    }
}
