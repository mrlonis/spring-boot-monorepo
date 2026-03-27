package com.mrlonis.xml.shared.model.pure.jaxb;

import com.mrlonis.xml.shared.model.BaseModel;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class BasePureJaxbModel<T> implements BaseModel<T> {
    @XmlAttribute
    private long id;

    @XmlElement(name = "title")
    private String name;

    @XmlTransient
    private String author;

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    private List<String> tag;
}
