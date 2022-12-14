package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsPropertyTypeSerializer extends AsArrayTypeSerializer {
    protected final String _typePropertyName;

    public AsPropertyTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        super(typeIdResolver, beanProperty);
        this._typePropertyName = str;
    }

    public AsPropertyTypeSerializer forProperty(BeanProperty beanProperty) {
        return this._property == beanProperty ? this : new AsPropertyTypeSerializer(this._idResolver, beanProperty, this._typePropertyName);
    }

    public String getPropertyName() {
        return this._typePropertyName;
    }

    public JsonTypeInfo.C1447As getTypeInclusion() {
        return JsonTypeInfo.C1447As.PROPERTY;
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(this._typePropertyName, idFromValue(obj));
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(this._typePropertyName, idFromValueAndType(obj, cls));
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeEndObject();
    }

    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(this._typePropertyName, str);
    }

    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        jsonGenerator.writeEndObject();
    }
}
