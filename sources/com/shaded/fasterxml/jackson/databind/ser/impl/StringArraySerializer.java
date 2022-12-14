package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class StringArraySerializer extends ArraySerializerBase<String[]> implements ContextualSerializer {
    private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(String.class);
    public static final StringArraySerializer instance = new StringArraySerializer();
    protected final JsonSerializer<Object> _elementSerializer;

    protected StringArraySerializer() {
        super(String[].class, (BeanProperty) null);
        this._elementSerializer = null;
    }

    public StringArraySerializer(StringArraySerializer stringArraySerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        super((ArraySerializerBase<?>) stringArraySerializer, beanProperty);
        this._elementSerializer = jsonSerializer;
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return this;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer jsonSerializer;
        AnnotatedMember member;
        Object findContentSerializer;
        if (beanProperty == null || (member = beanProperty.getMember()) == null || (findContentSerializer = serializerProvider.getAnnotationIntrospector().findContentSerializer(member)) == null) {
            jsonSerializer = null;
        } else {
            jsonSerializer = serializerProvider.serializerInstance(member, findContentSerializer);
        }
        if (jsonSerializer == null) {
            jsonSerializer = this._elementSerializer;
        }
        JsonSerializer findConvertingContentSerializer = findConvertingContentSerializer(serializerProvider, beanProperty, jsonSerializer);
        if (findConvertingContentSerializer == null) {
            findConvertingContentSerializer = serializerProvider.findValueSerializer((Class<?>) String.class, beanProperty);
        } else if (findConvertingContentSerializer instanceof ContextualSerializer) {
            findConvertingContentSerializer = ((ContextualSerializer) findConvertingContentSerializer).createContextual(serializerProvider, beanProperty);
        }
        if (isDefaultSerializer(findConvertingContentSerializer)) {
            findConvertingContentSerializer = null;
        }
        return findConvertingContentSerializer == this._elementSerializer ? this : new StringArraySerializer(this, beanProperty, findConvertingContentSerializer);
    }

    public JavaType getContentType() {
        return VALUE_TYPE;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._elementSerializer;
    }

    public boolean isEmpty(String[] strArr) {
        return strArr == null || strArr.length == 0;
    }

    public boolean hasSingleElement(String[] strArr) {
        return strArr.length == 1;
    }

    public void serializeContents(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        int length = strArr.length;
        if (length != 0) {
            if (this._elementSerializer != null) {
                serializeContentsSlow(strArr, jsonGenerator, serializerProvider, this._elementSerializer);
                return;
            }
            for (int i = 0; i < length; i++) {
                if (strArr[i] == null) {
                    jsonGenerator.writeNull();
                } else {
                    jsonGenerator.writeString(strArr[i]);
                }
            }
        }
    }

    private void serializeContentsSlow(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (strArr[i] == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                jsonSerializer.serialize(strArr[i], jsonGenerator, serializerProvider);
            }
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        createSchemaNode.put("items", (JsonNode) createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_STRING));
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonArrayFormatVisitor expectArrayFormat;
        if (jsonFormatVisitorWrapper != null && (expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType)) != null) {
            expectArrayFormat.itemsFormat(JsonFormatTypes.STRING);
        }
    }
}
