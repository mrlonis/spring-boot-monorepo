package com.mrlonis.xml.shared.model.jackson.jaxb;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.mrlonis.xml.shared.model.BaseModel;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class BaseJacksonJaxbModel<T> implements BaseModel<T> {
    @JacksonXmlProperty(isAttribute = true)
    @Getter(onMethod_ = {@JsonGetter})
    private long id;

    @JsonProperty("title")
    private String name;

    @XmlTransient
    private String author;

    @JacksonXmlElementWrapper(localName = "tags")
    @JsonProperty("tag")
    private List<String> tag;
}
