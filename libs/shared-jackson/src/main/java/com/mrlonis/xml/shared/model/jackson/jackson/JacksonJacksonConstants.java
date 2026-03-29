package com.mrlonis.xml.shared.model.jackson.jackson;

import static com.mrlonis.xml.shared.model.jackson.jackson.JacksonJodaTimeConstants.JACKSON_JODA_TIME_MAP;

import com.mrlonis.xml.shared.enums.TimeLibrary;
import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonJacksonConstants {
    public static final Map<TimeLibrary, Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>>>
            JACKSON_JACKSON_MAP = Map.of(
                    TimeLibrary.JODA,
                    JACKSON_JODA_TIME_MAP,
                    TimeLibrary.JAVA,
                    JacksonJavaTimeConstants.JACKSON_JAVA_TIME_MAP);
}
