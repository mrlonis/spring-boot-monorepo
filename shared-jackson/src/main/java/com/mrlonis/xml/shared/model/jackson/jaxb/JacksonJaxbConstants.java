package com.mrlonis.xml.shared.model.jackson.jaxb;

import static com.mrlonis.xml.shared.model.jackson.jaxb.JacksonJaxbJodaTimeConstants.JACKSON_JAXB_JODA_TIME_MAP;

import com.mrlonis.xml.shared.enums.TimeLibrary;
import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonJaxbConstants {
    public static final Map<TimeLibrary, Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>>> JACKSON_JAXB_MAP =
            Map.of(
                    TimeLibrary.JODA,
                    JACKSON_JAXB_JODA_TIME_MAP,
                    TimeLibrary.JAVA,
                    JacksonJaxbJavaTimeConstants.JACKSON_JAXB_JAVA_TIME_MAP);
}
