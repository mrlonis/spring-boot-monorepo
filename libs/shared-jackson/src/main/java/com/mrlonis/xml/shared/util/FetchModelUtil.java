package com.mrlonis.xml.shared.util;

import static com.mrlonis.xml.shared.model.jackson.jackson.JacksonJacksonConstants.JACKSON_JACKSON_MAP;
import static com.mrlonis.xml.shared.model.jackson.jakarta.JacksonJakartaConstants.JACKSON_JAKARTA_MAP;
import static com.mrlonis.xml.shared.model.jackson.jaxb.JacksonJaxbConstants.JACKSON_JAXB_MAP;
import static com.mrlonis.xml.shared.model.pure.jakarta.PureJakartaConstants.PURE_JAKARTA_MAP;
import static com.mrlonis.xml.shared.model.pure.jaxb.PureJaxbConstants.PURE_JAXB_MAP;

import com.mrlonis.xml.shared.enums.AnnotationLibrary;
import com.mrlonis.xml.shared.enums.TimeLibrary;
import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.model.jakarta.JakartaConstants;
import com.mrlonis.xml.shared.model.jaxb.JaxbConstants;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class FetchModelUtil {
    private static final Map<
                    AnnotationLibrary, Map<TimeLibrary, Map<TimeZoneIndicator, Map<XmlAccessorType, BaseModel<?>>>>>
            XML_MODELS_BY_TYPE = Map.of(
                    AnnotationLibrary.JAXB,
                    JaxbConstants.JAXB_MAP,
                    AnnotationLibrary.JAKARTA,
                    JakartaConstants.JAKARTA_MAP,
                    AnnotationLibrary.PURE_JAXB,
                    PURE_JAXB_MAP,
                    AnnotationLibrary.PURE_JAKARTA,
                    PURE_JAKARTA_MAP,
                    AnnotationLibrary.JACKSON_JAXB,
                    JACKSON_JAXB_MAP,
                    AnnotationLibrary.JACKSON_JAKARTA,
                    JACKSON_JAKARTA_MAP,
                    AnnotationLibrary.JACKSON_JACKSON,
                    JACKSON_JACKSON_MAP);

    @Cacheable("fetchModels")
    public BaseModel<?> fetchModel(
            AnnotationLibrary formatLibrary,
            XmlAccessorType accessType,
            TimeLibrary dateLibrary,
            TimeZoneIndicator zoned) {
        return XML_MODELS_BY_TYPE.get(formatLibrary).get(dateLibrary).get(zoned).get(accessType);
    }
}
