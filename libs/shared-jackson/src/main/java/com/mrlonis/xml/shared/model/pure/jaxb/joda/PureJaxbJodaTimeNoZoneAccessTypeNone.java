package com.mrlonis.xml.shared.model.pure.jaxb.joda;

import com.mrlonis.xml.shared.adapter.JaxbJodaTimeAdapters;
import com.mrlonis.xml.shared.model.BaseModel;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.joda.time.LocalDateTime;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"id", "name", "date", "tags"})
@Data
@Builder
@Jacksonized
public class PureJaxbJodaTimeNoZoneAccessTypeNone implements BaseModel<LocalDateTime> {
    @XmlAttribute
    private long id;

    @XmlElement(name = "title")
    private String name;

    @XmlTransient
    private String author;

    @XmlElement
    @XmlJavaTypeAdapter(JaxbJodaTimeAdapters.JaxbLocalDateTimeAdapter.class)
    private LocalDateTime date;

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    private List<String> tag;
}
