package com.mrlonis.xml.shared.model.jaxb;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrlonis.xml.shared.model.BaseModel;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@NoArgsConstructor
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
    @JacksonXmlProperty(localName = "tag")
    @XmlElement(name = "tag")
    @JsonAlias("tags")
    private List<String> tag;
}
