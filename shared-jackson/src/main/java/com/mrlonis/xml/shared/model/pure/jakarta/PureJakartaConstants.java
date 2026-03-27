package com.mrlonis.xml.shared.model.pure.jakarta;

import com.mrlonis.xml.shared.enums.TimeLibrary;
import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.model.pure.jakarta.java.PureJakartaJavaTimeConstants;
import com.mrlonis.xml.shared.model.pure.jakarta.joda.PureJakartaJodaTimeConstants;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PureJakartaConstants {
    public static final Map<TimeLibrary, Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>>> PURE_JAKARTA_MAP =
            Map.of(
                    TimeLibrary.JODA,
                    PureJakartaJodaTimeConstants.PURE_JAKARTA_JODA_TIME_MAP,
                    TimeLibrary.JAVA,
                    PureJakartaJavaTimeConstants.PURE_JAKARTA_JAVA_TIME_MAP);
}
