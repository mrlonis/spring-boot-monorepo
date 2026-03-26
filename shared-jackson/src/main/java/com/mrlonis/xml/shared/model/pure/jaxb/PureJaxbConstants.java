package com.mrlonis.xml.shared.model.pure.jaxb;

import com.mrlonis.xml.shared.enums.TimeLibrary;
import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PureJaxbConstants {
    public static final Map<TimeLibrary, Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>>> PURE_JAXB_MAP =
            Map.of(
                    TimeLibrary.JODA,
                    PureJaxbJodaTimeConstants.PURE_JAXB_JODA_TIME_MAP,
                    TimeLibrary.JAVA,
                    PureJaxbJavaTimeConstants.PURE_JAXB_JAVA_TIME_MAP);
}
