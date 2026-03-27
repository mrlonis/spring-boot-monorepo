package com.mrlonis.xml.shared.adapter;

import com.mrlonis.xml.shared.time.TimeAdapterUtil;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import lombok.Getter;

@Getter
public abstract class BaseJaxbAdapter<T> extends XmlAdapter<String, T> {
    private final Class<T> type;

    protected BaseJaxbAdapter(Class<T> type) {
        this.type = type;
    }

    @Override
    public T unmarshal(String v) {
        return TimeAdapterUtil.unmarshal(v, this.getType());
    }

    @Override
    public String marshal(T v) {
        return TimeAdapterUtil.marshal(v);
    }
}
