package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;

public abstract class ContainerDeserializerBase<T> extends StdDeserializer<T> {
    public abstract JsonDeserializer<Object> getContentDeserializer();

    public abstract JavaType getContentType();

    protected ContainerDeserializerBase(Class<?> cls) {
        super(cls);
    }
}
