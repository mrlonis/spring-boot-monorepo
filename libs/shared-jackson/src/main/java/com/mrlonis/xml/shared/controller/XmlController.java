package com.mrlonis.xml.shared.controller;

import com.mrlonis.xml.shared.enums.AnnotationLibrary;
import com.mrlonis.xml.shared.enums.TimeLibrary;
import com.mrlonis.xml.shared.enums.TimeZoneIndicator;
import com.mrlonis.xml.shared.enums.XmlAccessorType;
import com.mrlonis.xml.shared.model.BaseModel;
import com.mrlonis.xml.shared.util.Constants;
import com.mrlonis.xml.shared.util.FetchModelUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.XML_PATH)
@AllArgsConstructor
public class XmlController {
    private FetchModelUtil fetchModelUtil;

    @GetMapping(
            value = "/{formatLibrary}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BaseModel<?> getModel(
            @PathVariable AnnotationLibrary formatLibrary,
            @RequestParam XmlAccessorType accessType,
            @RequestParam TimeLibrary dateLibrary,
            @RequestParam TimeZoneIndicator zoned) {
        return fetchModelUtil.fetchModel(formatLibrary, accessType, dateLibrary, zoned);
    }

    @PostMapping(
            value = Constants.DESERIALIZE_PATH,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BaseModel<?> deserializeModel(@RequestBody BaseModel<?> model) {
        return model;
    }
}
