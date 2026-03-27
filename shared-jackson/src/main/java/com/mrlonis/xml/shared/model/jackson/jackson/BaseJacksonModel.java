package com.mrlonis.xml.shared.model.jackson.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrlonis.xml.shared.model.BaseModel;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@NoArgsConstructor
@Data
@SuperBuilder
public abstract class BaseJacksonModel<T> implements BaseModel<T> {
    @JacksonXmlProperty(isAttribute = true)
    private long id;

    @JsonProperty("title")
    private String name;

    @JsonIgnore
    private String author;

    @JacksonXmlElementWrapper(localName = "tags")
    @JsonProperty("tag")
    private List<String> tag;
}
