package com.mrlonis.xml.shared.model.jaxb;

import static com.mrlonis.xml.shared.model.jaxb.JaxbJodaTimeConstants.JAXB_JODA_TIME_MAP;

import com.mrlonis.xml.shared.enums.TimeLibrary;
import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JaxbConstants {
    public static final Map<TimeLibrary, Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>>> JAXB_MAP =
            Map.of(TimeLibrary.JODA, JAXB_JODA_TIME_MAP, TimeLibrary.JAVA, JaxbJavaTimeConstants.JAXB_JAVA_TIME_MAP);
}
