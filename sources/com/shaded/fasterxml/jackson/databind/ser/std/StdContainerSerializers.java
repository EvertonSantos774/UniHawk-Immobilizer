package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.IteratorSerializer;

public class StdContainerSerializers {
    protected StdContainerSerializers() {
    }

    public static ContainerSerializer<?> indexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return new IndexedListSerializer(javaType, z, typeSerializer, (BeanProperty) null, jsonSerializer);
    }

    public static ContainerSerializer<?> collectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return new CollectionSerializer(javaType, z, typeSerializer, (BeanProperty) null, jsonSerializer);
    }

    public static ContainerSerializer<?> iteratorSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer) {
        return new IteratorSerializer(javaType, z, typeSerializer, (BeanProperty) null);
    }

    public static ContainerSerializer<?> iterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer) {
        return new IterableSerializer(javaType, z, typeSerializer, (BeanProperty) null);
    }

    public static JsonSerializer<?> enumSetSerializer(JavaType javaType) {
        return new EnumSetSerializer(javaType, (BeanProperty) null);
    }

    @Deprecated
    public static ContainerSerializer<?> indexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        return indexedListSerializer(javaType, z, typeSerializer, jsonSerializer);
    }

    @Deprecated
    public static ContainerSerializer<?> collectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        return collectionSerializer(javaType, z, typeSerializer, jsonSerializer);
    }
}
