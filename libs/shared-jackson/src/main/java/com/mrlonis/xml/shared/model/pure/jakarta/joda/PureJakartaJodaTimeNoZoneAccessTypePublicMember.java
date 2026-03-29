package com.mrlonis.xml.shared.model.pure.jakarta.joda;

import com.mrlonis.xml.shared.adapter.JakartaJodaTimeAdapters;
import com.mrlonis.xml.shared.model.BaseModel;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.joda.time.LocalDateTime;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder = {"id", "name", "date", "tags"})
@Data
@Jacksonized
@Builder
public class PureJakartaJodaTimeNoZoneAccessTypePublicMember implements BaseModel<LocalDateTime> {
    @XmlAttribute
    private long id;

    @XmlElement(name = "title")
    private String name;

    @XmlTransient
    private String author;

    @XmlElement
    @XmlJavaTypeAdapter(JakartaJodaTimeAdapters.JakartaLocalDateTimeAdapter.class)
    private LocalDateTime date;

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    private List<String> tag;
}
