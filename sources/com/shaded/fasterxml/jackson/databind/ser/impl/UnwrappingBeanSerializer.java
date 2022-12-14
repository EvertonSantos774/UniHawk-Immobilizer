package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

public class UnwrappingBeanSerializer extends BeanSerializerBase {
    protected final NameTransformer _nameTransformer;

    public UnwrappingBeanSerializer(BeanSerializerBase beanSerializerBase, NameTransformer nameTransformer) {
        super(beanSerializerBase, nameTransformer);
        this._nameTransformer = nameTransformer;
    }

    public UnwrappingBeanSerializer(UnwrappingBeanSerializer unwrappingBeanSerializer, ObjectIdWriter objectIdWriter) {
        super((BeanSerializerBase) unwrappingBeanSerializer, objectIdWriter);
        this._nameTransformer = unwrappingBeanSerializer._nameTransformer;
    }

    protected UnwrappingBeanSerializer(UnwrappingBeanSerializer unwrappingBeanSerializer, String[] strArr) {
        super((BeanSerializerBase) unwrappingBeanSerializer, strArr);
        this._nameTransformer = unwrappingBeanSerializer._nameTransformer;
    }

    public JsonSerializer<Object> unwrappingSerializer(NameTransformer nameTransformer) {
        return new UnwrappingBeanSerializer((BeanSerializerBase) this, nameTransformer);
    }

    public boolean isUnwrappingSerializer() {
        return true;
    }

    public UnwrappingBeanSerializer withObjectIdWriter(ObjectIdWriter objectIdWriter) {
        return new UnwrappingBeanSerializer(this, objectIdWriter);
    }

    /* access modifiers changed from: protected */
    public UnwrappingBeanSerializer withIgnorals(String[] strArr) {
        return new UnwrappingBeanSerializer(this, strArr);
    }

    /* access modifiers changed from: protected */
    public BeanSerializerBase asArraySerializer() {
        return this;
    }

    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (this._objectIdWriter != null) {
            _serializeWithObjectId(obj, jsonGenerator, serializerProvider, false);
        } else if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
    }

    public String toString() {
        return "UnwrappingBeanSerializer for " + handledType().getName();
    }
}
