package com.mrlonis.xml.shared.model.jaxb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.mrlonis.xml.shared.model.BaseModel;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class BaseJaxbModel<T> implements BaseModel<T> {
    @XmlAttribute
    private long id;

    @JsonProperty("title")
    private String name;

    @XmlTransient
    private String author;

    @JacksonXmlElementWrapper(localName = "tags")
    @XmlElement(name = "tag")
    private List<String> tag;
}
