package com.mrlonis.xml.shared.model.jakarta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.mrlonis.xml.shared.model.BaseModel;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.List;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class BaseJakartaModel<T> implements BaseModel<T> {
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
