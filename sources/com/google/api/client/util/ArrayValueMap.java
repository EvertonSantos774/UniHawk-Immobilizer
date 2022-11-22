package com.google.api.client.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

public final class ArrayValueMap {
    private final Object destination;
    private final Map<Field, ArrayValue> fieldMap = ArrayMap.create();
    private final Map<String, ArrayValue> keyMap = ArrayMap.create();

    static class ArrayValue {
        final Class<?> componentType;
        final ArrayList<Object> values = new ArrayList<>();

        ArrayValue(Class<?> componentType2) {
            this.componentType = componentType2;
        }

        /* access modifiers changed from: package-private */
        public Object toArray() {
            return Types.toArray(this.values, this.componentType);
        }

        /* access modifiers changed from: package-private */
        public void addValue(Class<?> componentType2, Object value) {
            Preconditions.checkArgument(componentType2 == this.componentType);
            this.values.add(value);
        }
    }

    public ArrayValueMap(Object destination2) {
        this.destination = destination2;
    }

    public void setValues() {
        for (Map.Entry<String, ArrayValue> entry : this.keyMap.entrySet()) {
            ((Map) this.destination).put(entry.getKey(), entry.getValue().toArray());
        }
        for (Map.Entry<Field, ArrayValue> entry2 : this.fieldMap.entrySet()) {
            FieldInfo.setFieldValue(entry2.getKey(), this.destination, entry2.getValue().toArray());
        }
    }

    public void put(Field field, Class<?> arrayComponentType, Object value) {
        ArrayValue arrayValue = this.fieldMap.get(field);
        if (arrayValue == null) {
            arrayValue = new ArrayValue(arrayComponentType);
            this.fieldMap.put(field, arrayValue);
        }
        arrayValue.addValue(arrayComponentType, value);
    }

    public void put(String keyName, Class<?> arrayComponentType, Object value) {
        ArrayValue arrayValue = this.keyMap.get(keyName);
        if (arrayValue == null) {
            arrayValue = new ArrayValue(arrayComponentType);
            this.keyMap.put(keyName, arrayValue);
        }
        arrayValue.addValue(arrayComponentType, value);
    }
}
