package com.mrlonis.xml.shared.model.pure.jaxb.java;

import com.mrlonis.xml.shared.adapter.JaxbJavaTimeAdapters;
import com.mrlonis.xml.shared.model.BaseModel;
import java.time.ZonedDateTime;
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
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "date", "tags"})
@Data
@Jacksonized
@Builder
public class PureJaxbJavaTimeZonedAccessTypeField implements BaseModel<ZonedDateTime> {
    @XmlAttribute
    private long id;

    @XmlElement(name = "title")
    @Getter(onMethod_ = {@XmlElement(name = "title")})
    @Setter(onMethod_ = {@XmlElement(name = "title")})
    private String name;

    @XmlTransient
    private String author;

    @XmlElement
    @XmlJavaTypeAdapter(JaxbJavaTimeAdapters.JaxbZonedDateTimeAdapter.class)
    private ZonedDateTime date;

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    @Getter(onMethod_ = {@XmlElementWrapper(name = "tags"), @XmlElement(name = "tag")})
    @Setter(onMethod_ = {@XmlElementWrapper(name = "tags"), @XmlElement(name = "tag")})
    private List<String> tag;
}
