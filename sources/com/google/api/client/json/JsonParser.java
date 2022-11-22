package com.google.api.client.json;

import com.google.api.client.json.JsonPolymorphicTypeMap;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import com.google.api.client.util.Types;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class JsonParser implements Closeable {
    private static WeakHashMap<Class<?>, Field> cachedTypemapFields = new WeakHashMap<>();
    private static final Lock lock = new ReentrantLock();

    public abstract void close() throws IOException;

    public abstract BigInteger getBigIntegerValue() throws IOException;

    public abstract byte getByteValue() throws IOException;

    public abstract String getCurrentName() throws IOException;

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue() throws IOException;

    public abstract double getDoubleValue() throws IOException;

    public abstract JsonFactory getFactory();

    public abstract float getFloatValue() throws IOException;

    public abstract int getIntValue() throws IOException;

    public abstract long getLongValue() throws IOException;

    public abstract short getShortValue() throws IOException;

    public abstract String getText() throws IOException;

    public abstract JsonToken nextToken() throws IOException;

    public abstract JsonParser skipChildren() throws IOException;

    public final <T> T parseAndClose(Class<T> destinationClass) throws IOException {
        return parseAndClose(destinationClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> T parseAndClose(Class<T> destinationClass, CustomizeJsonParser customizeParser) throws IOException {
        try {
            return parse(destinationClass, customizeParser);
        } finally {
            close();
        }
    }

    public final void skipToKey(String keyToFind) throws IOException {
        skipToKey((Set<String>) Collections.singleton(keyToFind));
    }

    public final String skipToKey(Set<String> keysToFind) throws IOException {
        JsonToken curToken = startParsingObjectOrArray();
        while (curToken == JsonToken.FIELD_NAME) {
            String key = getText();
            nextToken();
            if (keysToFind.contains(key)) {
                return key;
            }
            skipChildren();
            curToken = nextToken();
        }
        return null;
    }

    private JsonToken startParsing() throws IOException {
        JsonToken currentToken = getCurrentToken();
        if (currentToken == null) {
            currentToken = nextToken();
        }
        Preconditions.checkArgument(currentToken != null, "no JSON input found");
        return currentToken;
    }

    private JsonToken startParsingObjectOrArray() throws IOException {
        JsonToken currentToken = startParsing();
        switch (currentToken) {
            case START_OBJECT:
                JsonToken currentToken2 = nextToken();
                Preconditions.checkArgument(currentToken2 == JsonToken.FIELD_NAME || currentToken2 == JsonToken.END_OBJECT, currentToken2);
                return currentToken2;
            case START_ARRAY:
                return nextToken();
            default:
                return currentToken;
        }
    }

    public final void parseAndClose(Object destination) throws IOException {
        parseAndClose(destination, (CustomizeJsonParser) null);
    }

    @Beta
    public final void parseAndClose(Object destination, CustomizeJsonParser customizeParser) throws IOException {
        try {
            parse(destination, customizeParser);
        } finally {
            close();
        }
    }

    public final <T> T parse(Class<T> destinationClass) throws IOException {
        return parse(destinationClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> T parse(Class<T> destinationClass, CustomizeJsonParser customizeParser) throws IOException {
        return parse((Type) destinationClass, false, customizeParser);
    }

    public Object parse(Type dataType, boolean close) throws IOException {
        return parse(dataType, close, (CustomizeJsonParser) null);
    }

    @Beta
    public Object parse(Type dataType, boolean close, CustomizeJsonParser customizeParser) throws IOException {
        try {
            if (!Void.class.equals(dataType)) {
                startParsing();
            }
            return parseValue((Field) null, dataType, new ArrayList(), (Object) null, customizeParser, true);
        } finally {
            if (close) {
                close();
            }
        }
    }

    public final void parse(Object destination) throws IOException {
        parse(destination, (CustomizeJsonParser) null);
    }

    @Beta
    public final void parse(Object destination, CustomizeJsonParser customizeParser) throws IOException {
        ArrayList<Type> context = new ArrayList<>();
        context.add(destination.getClass());
        parse(context, destination, customizeParser);
    }

    private void parse(ArrayList<Type> context, Object destination, CustomizeJsonParser customizeParser) throws IOException {
        if (destination instanceof GenericJson) {
            ((GenericJson) destination).setFactory(getFactory());
        }
        JsonToken curToken = startParsingObjectOrArray();
        Class<?> destinationClass = destination.getClass();
        ClassInfo classInfo = ClassInfo.m34of(destinationClass);
        boolean isGenericData = GenericData.class.isAssignableFrom(destinationClass);
        if (isGenericData || !Map.class.isAssignableFrom(destinationClass)) {
            while (curToken == JsonToken.FIELD_NAME) {
                String key = getText();
                nextToken();
                if (customizeParser == null || !customizeParser.stopAt(destination, key)) {
                    FieldInfo fieldInfo = classInfo.getFieldInfo(key);
                    if (fieldInfo != null) {
                        if (!fieldInfo.isFinal() || fieldInfo.isPrimitive()) {
                            Field field = fieldInfo.getField();
                            int contextSize = context.size();
                            context.add(field.getGenericType());
                            Object fieldValue = parseValue(field, fieldInfo.getGenericType(), context, destination, customizeParser, true);
                            context.remove(contextSize);
                            fieldInfo.setValue(destination, fieldValue);
                        } else {
                            throw new IllegalArgumentException("final array/object fields are not supported");
                        }
                    } else if (isGenericData) {
                        ((GenericData) destination).set(key, parseValue((Field) null, (Type) null, context, destination, customizeParser, true));
                    } else {
                        if (customizeParser != null) {
                            customizeParser.handleUnrecognizedKey(destination, key);
                        }
                        skipChildren();
                    }
                    curToken = nextToken();
                } else {
                    return;
                }
            }
            return;
        }
        parseMap((Field) null, (Map) destination, Types.getMapValueParameter(destinationClass), context, customizeParser);
    }

    public final <T> Collection<T> parseArrayAndClose(Class<?> destinationCollectionClass, Class<T> destinationItemClass) throws IOException {
        return parseArrayAndClose(destinationCollectionClass, destinationItemClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> Collection<T> parseArrayAndClose(Class<?> destinationCollectionClass, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        try {
            return parseArray(destinationCollectionClass, destinationItemClass, customizeParser);
        } finally {
            close();
        }
    }

    public final <T> void parseArrayAndClose(Collection<? super T> destinationCollection, Class<T> destinationItemClass) throws IOException {
        parseArrayAndClose(destinationCollection, destinationItemClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> void parseArrayAndClose(Collection<? super T> destinationCollection, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        try {
            parseArray(destinationCollection, destinationItemClass, customizeParser);
        } finally {
            close();
        }
    }

    public final <T> Collection<T> parseArray(Class<?> destinationCollectionClass, Class<T> destinationItemClass) throws IOException {
        return parseArray(destinationCollectionClass, destinationItemClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> Collection<T> parseArray(Class<?> destinationCollectionClass, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        Collection<Object> newCollectionInstance = Data.newCollectionInstance(destinationCollectionClass);
        parseArray(newCollectionInstance, destinationItemClass, customizeParser);
        return newCollectionInstance;
    }

    public final <T> void parseArray(Collection<? super T> destinationCollection, Class<T> destinationItemClass) throws IOException {
        parseArray(destinationCollection, destinationItemClass, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> void parseArray(Collection<? super T> destinationCollection, Class<T> destinationItemClass, CustomizeJsonParser customizeParser) throws IOException {
        parseArray((Field) null, destinationCollection, destinationItemClass, new ArrayList(), customizeParser);
    }

    private <T> void parseArray(Field fieldContext, Collection<T> destinationCollection, Type destinationItemType, ArrayList<Type> context, CustomizeJsonParser customizeParser) throws IOException {
        JsonToken curToken = startParsingObjectOrArray();
        while (curToken != JsonToken.END_ARRAY) {
            destinationCollection.add(parseValue(fieldContext, destinationItemType, context, destinationCollection, customizeParser, true));
            curToken = nextToken();
        }
    }

    private void parseMap(Field fieldContext, Map<String, Object> destinationMap, Type valueType, ArrayList<Type> context, CustomizeJsonParser customizeParser) throws IOException {
        JsonToken curToken = startParsingObjectOrArray();
        while (curToken == JsonToken.FIELD_NAME) {
            String key = getText();
            nextToken();
            if (customizeParser == null || !customizeParser.stopAt(destinationMap, key)) {
                destinationMap.put(key, parseValue(fieldContext, valueType, context, destinationMap, customizeParser, true));
                curToken = nextToken();
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0247, code lost:
        if (r34.isAssignableFrom(java.lang.Boolean.class) != false) goto L_0x0249;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0390, code lost:
        if (r36.getAnnotation(com.google.api.client.json.JsonString.class) != null) goto L_0x0392;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a9, code lost:
        if (com.google.api.client.util.Types.isAssignableToOrFrom(r34, java.util.Collection.class) != false) goto L_0x00ab;
     */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x025b A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0261 A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0279 A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:210:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c8 A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00cf A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e6 A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f4 A[ADDED_TO_REGION, Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0105 A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0140 A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014d A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x016a A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0170 A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0189 A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x019d A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01aa A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01b3 A[Catch:{ IllegalArgumentException -> 0x005a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object parseValue(java.lang.reflect.Field r36, java.lang.reflect.Type r37, java.util.ArrayList<java.lang.reflect.Type> r38, java.lang.Object r39, com.google.api.client.json.CustomizeJsonParser r40, boolean r41) throws java.io.IOException {
        /*
            r35 = this;
            r0 = r38
            r1 = r37
            java.lang.reflect.Type r37 = com.google.api.client.util.Data.resolveWildcardTypeOrTypeVariable(r0, r1)
            r0 = r37
            boolean r4 = r0 instanceof java.lang.Class
            if (r4 == 0) goto L_0x002d
            r4 = r37
            java.lang.Class r4 = (java.lang.Class) r4
            r34 = r4
        L_0x0014:
            r0 = r37
            boolean r4 = r0 instanceof java.lang.reflect.ParameterizedType
            if (r4 == 0) goto L_0x0022
            r4 = r37
            java.lang.reflect.ParameterizedType r4 = (java.lang.reflect.ParameterizedType) r4
            java.lang.Class r34 = com.google.api.client.util.Types.getRawClass(r4)
        L_0x0022:
            java.lang.Class<java.lang.Void> r4 = java.lang.Void.class
            r0 = r34
            if (r0 != r4) goto L_0x0030
            r35.skipChildren()
            r4 = 0
        L_0x002c:
            return r4
        L_0x002d:
            r34 = 0
            goto L_0x0014
        L_0x0030:
            com.google.api.client.json.JsonToken r28 = r35.getCurrentToken()
            int[] r4 = com.google.api.client.json.JsonParser.C09311.$SwitchMap$com$google$api$client$json$JsonToken     // Catch:{ IllegalArgumentException -> 0x005a }
            int r5 = r28.ordinal()     // Catch:{ IllegalArgumentException -> 0x005a }
            r4 = r4[r5]     // Catch:{ IllegalArgumentException -> 0x005a }
            switch(r4) {
                case 1: goto L_0x0108;
                case 2: goto L_0x0097;
                case 3: goto L_0x0097;
                case 4: goto L_0x0108;
                case 5: goto L_0x0108;
                case 6: goto L_0x0235;
                case 7: goto L_0x0235;
                case 8: goto L_0x0265;
                case 9: goto L_0x0265;
                case 10: goto L_0x0336;
                case 11: goto L_0x03a6;
                default: goto L_0x003f;
            }     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x003f:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x005a }
            r5.<init>()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.String r8 = "unexpected JSON node type: "
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r28
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.String r5 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x005a }
            r4.<init>(r5)     // Catch:{ IllegalArgumentException -> 0x005a }
            throw r4     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x005a:
            r22 = move-exception
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r20.<init>()
            java.lang.String r21 = r35.getCurrentName()
            if (r21 == 0) goto L_0x0073
            java.lang.String r4 = "key "
            r0 = r20
            java.lang.StringBuilder r4 = r0.append(r4)
            r0 = r21
            r4.append(r0)
        L_0x0073:
            if (r36 == 0) goto L_0x008b
            if (r21 == 0) goto L_0x007e
            java.lang.String r4 = ", "
            r0 = r20
            r0.append(r4)
        L_0x007e:
            java.lang.String r4 = "field "
            r0 = r20
            java.lang.StringBuilder r4 = r0.append(r4)
            r0 = r36
            r4.append(r0)
        L_0x008b:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = r20.toString()
            r0 = r22
            r4.<init>(r5, r0)
            throw r4
        L_0x0097:
            boolean r24 = com.google.api.client.util.Types.isArray(r37)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r37 == 0) goto L_0x00ab
            if (r24 != 0) goto L_0x00ab
            if (r34 == 0) goto L_0x00f2
            java.lang.Class<java.util.Collection> r4 = java.util.Collection.class
            r0 = r34
            boolean r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 == 0) goto L_0x00f2
        L_0x00ab:
            r4 = 1
        L_0x00ac:
            java.lang.String r5 = "expected collection or array type but got %s"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ IllegalArgumentException -> 0x005a }
            r9 = 0
            r8[r9] = r37     // Catch:{ IllegalArgumentException -> 0x005a }
            com.google.api.client.util.Preconditions.checkArgument(r4, r5, r8)     // Catch:{ IllegalArgumentException -> 0x005a }
            r6 = 0
            if (r40 == 0) goto L_0x00c6
            if (r36 == 0) goto L_0x00c6
            r0 = r40
            r1 = r39
            r2 = r36
            java.util.Collection r6 = r0.newInstanceForArray(r1, r2)     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x00c6:
            if (r6 != 0) goto L_0x00cc
            java.util.Collection r6 = com.google.api.client.util.Data.newCollectionInstance(r37)     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x00cc:
            r7 = 0
            if (r24 == 0) goto L_0x00f4
            java.lang.reflect.Type r7 = com.google.api.client.util.Types.getArrayComponentType(r37)     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x00d3:
            r0 = r38
            java.lang.reflect.Type r7 = com.google.api.client.util.Data.resolveWildcardTypeOrTypeVariable(r0, r7)     // Catch:{ IllegalArgumentException -> 0x005a }
            r4 = r35
            r5 = r36
            r8 = r38
            r9 = r40
            r4.parseArray(r5, r6, r7, r8, r9)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r24 == 0) goto L_0x0105
            r0 = r38
            java.lang.Class r4 = com.google.api.client.util.Types.getRawArrayComponentType(r0, r7)     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Object r4 = com.google.api.client.util.Types.toArray(r6, r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x00f2:
            r4 = 0
            goto L_0x00ac
        L_0x00f4:
            if (r34 == 0) goto L_0x00d3
            java.lang.Class<java.lang.Iterable> r4 = java.lang.Iterable.class
            r0 = r34
            boolean r4 = r4.isAssignableFrom(r0)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 == 0) goto L_0x00d3
            java.lang.reflect.Type r7 = com.google.api.client.util.Types.getIterableParameter(r37)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x00d3
        L_0x0105:
            r4 = r6
            goto L_0x002c
        L_0x0108:
            boolean r4 = com.google.api.client.util.Types.isArray(r37)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 != 0) goto L_0x0181
            r4 = 1
        L_0x010f:
            java.lang.String r5 = "expected object or map type but got %s"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ IllegalArgumentException -> 0x005a }
            r9 = 0
            r8[r9] = r37     // Catch:{ IllegalArgumentException -> 0x005a }
            com.google.api.client.util.Preconditions.checkArgument(r4, r5, r8)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r41 == 0) goto L_0x0183
            java.lang.reflect.Field r33 = getCachedTypemapFieldFor(r34)     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x0120:
            r26 = 0
            if (r34 == 0) goto L_0x0130
            if (r40 == 0) goto L_0x0130
            r0 = r40
            r1 = r39
            r2 = r34
            java.lang.Object r26 = r0.newInstanceForObject(r1, r2)     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x0130:
            if (r34 == 0) goto L_0x0186
            java.lang.Class<java.util.Map> r4 = java.util.Map.class
            r0 = r34
            boolean r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 == 0) goto L_0x0186
            r25 = 1
        L_0x013e:
            if (r33 == 0) goto L_0x0189
            com.google.api.client.json.GenericJson r26 = new com.google.api.client.json.GenericJson     // Catch:{ IllegalArgumentException -> 0x005a }
            r26.<init>()     // Catch:{ IllegalArgumentException -> 0x005a }
            r4 = r26
        L_0x0147:
            int r19 = r38.size()     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r37 == 0) goto L_0x0154
            r0 = r38
            r1 = r37
            r0.add(r1)     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x0154:
            if (r25 == 0) goto L_0x019f
            java.lang.Class<com.google.api.client.util.GenericData> r5 = com.google.api.client.util.GenericData.class
            r0 = r34
            boolean r5 = r5.isAssignableFrom(r0)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r5 != 0) goto L_0x019f
            java.lang.Class<java.util.Map> r5 = java.util.Map.class
            r0 = r34
            boolean r5 = r5.isAssignableFrom(r0)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r5 == 0) goto L_0x019d
            java.lang.reflect.Type r11 = com.google.api.client.util.Types.getMapValueParameter(r37)     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x016e:
            if (r11 == 0) goto L_0x019f
            r0 = r4
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ IllegalArgumentException -> 0x005a }
            r10 = r0
            r8 = r35
            r9 = r36
            r12 = r38
            r13 = r40
            r8.parseMap(r9, r10, r11, r12, r13)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x0181:
            r4 = 0
            goto L_0x010f
        L_0x0183:
            r33 = 0
            goto L_0x0120
        L_0x0186:
            r25 = 0
            goto L_0x013e
        L_0x0189:
            if (r26 != 0) goto L_0x03fe
            if (r25 != 0) goto L_0x018f
            if (r34 != 0) goto L_0x0196
        L_0x018f:
            java.util.Map r26 = com.google.api.client.util.Data.newMapInstance(r34)     // Catch:{ IllegalArgumentException -> 0x005a }
            r4 = r26
            goto L_0x0147
        L_0x0196:
            java.lang.Object r26 = com.google.api.client.util.Types.newInstance(r34)     // Catch:{ IllegalArgumentException -> 0x005a }
            r4 = r26
            goto L_0x0147
        L_0x019d:
            r11 = 0
            goto L_0x016e
        L_0x019f:
            r0 = r35
            r1 = r38
            r2 = r40
            r0.parse((java.util.ArrayList<java.lang.reflect.Type>) r1, (java.lang.Object) r4, (com.google.api.client.json.CustomizeJsonParser) r2)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r37 == 0) goto L_0x01b1
            r0 = r38
            r1 = r19
            r0.remove(r1)     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x01b1:
            if (r33 == 0) goto L_0x002c
            r0 = r4
            com.google.api.client.json.GenericJson r0 = (com.google.api.client.json.GenericJson) r0     // Catch:{ IllegalArgumentException -> 0x005a }
            r5 = r0
            java.lang.String r8 = r33.getName()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Object r32 = r5.get(r8)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r32 == 0) goto L_0x022e
            r5 = 1
        L_0x01c2:
            java.lang.String r8 = "No value specified for @JsonPolymorphicTypeMap field"
            com.google.api.client.util.Preconditions.checkArgument(r5, r8)     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.String r31 = r32.toString()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Class<com.google.api.client.json.JsonPolymorphicTypeMap> r5 = com.google.api.client.json.JsonPolymorphicTypeMap.class
            r0 = r33
            java.lang.annotation.Annotation r30 = r0.getAnnotation(r5)     // Catch:{ IllegalArgumentException -> 0x005a }
            com.google.api.client.json.JsonPolymorphicTypeMap r30 = (com.google.api.client.json.JsonPolymorphicTypeMap) r30     // Catch:{ IllegalArgumentException -> 0x005a }
            r14 = 0
            com.google.api.client.json.JsonPolymorphicTypeMap$TypeDef[] r8 = r30.typeDefinitions()     // Catch:{ IllegalArgumentException -> 0x005a }
            int r9 = r8.length     // Catch:{ IllegalArgumentException -> 0x005a }
            r5 = 0
        L_0x01dc:
            if (r5 >= r9) goto L_0x01f0
            r29 = r8[r5]     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.String r13 = r29.key()     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r31
            boolean r13 = r13.equals(r0)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r13 == 0) goto L_0x0230
            java.lang.Class r14 = r29.ref()     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x01f0:
            if (r14 == 0) goto L_0x0233
            r5 = 1
        L_0x01f3:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x005a }
            r8.<init>()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.String r9 = "No TypeDef annotation found with key: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r31
            java.lang.StringBuilder r8 = r8.append(r0)     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.String r8 = r8.toString()     // Catch:{ IllegalArgumentException -> 0x005a }
            com.google.api.client.util.Preconditions.checkArgument(r5, r8)     // Catch:{ IllegalArgumentException -> 0x005a }
            com.google.api.client.json.JsonFactory r23 = r35.getFactory()     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r23
            java.lang.String r4 = r0.toString(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r23
            com.google.api.client.json.JsonParser r12 = r0.createJsonParser((java.lang.String) r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            r12.startParsing()     // Catch:{ IllegalArgumentException -> 0x005a }
            r16 = 0
            r17 = 0
            r18 = 0
            r13 = r36
            r15 = r38
            java.lang.Object r4 = r12.parseValue(r13, r14, r15, r16, r17, r18)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x022e:
            r5 = 0
            goto L_0x01c2
        L_0x0230:
            int r5 = r5 + 1
            goto L_0x01dc
        L_0x0233:
            r5 = 0
            goto L_0x01f3
        L_0x0235:
            if (r37 == 0) goto L_0x0249
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r34
            if (r0 == r4) goto L_0x0249
            if (r34 == 0) goto L_0x025f
            java.lang.Class<java.lang.Boolean> r4 = java.lang.Boolean.class
            r0 = r34
            boolean r4 = r0.isAssignableFrom(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 == 0) goto L_0x025f
        L_0x0249:
            r4 = 1
        L_0x024a:
            java.lang.String r5 = "expected type Boolean or boolean but got %s"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ IllegalArgumentException -> 0x005a }
            r9 = 0
            r8[r9] = r37     // Catch:{ IllegalArgumentException -> 0x005a }
            com.google.api.client.util.Preconditions.checkArgument(r4, r5, r8)     // Catch:{ IllegalArgumentException -> 0x005a }
            com.google.api.client.json.JsonToken r4 = com.google.api.client.json.JsonToken.VALUE_TRUE     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r28
            if (r0 != r4) goto L_0x0261
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x025f:
            r4 = 0
            goto L_0x024a
        L_0x0261:
            java.lang.Boolean r4 = java.lang.Boolean.FALSE     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x0265:
            if (r36 == 0) goto L_0x0271
            java.lang.Class<com.google.api.client.json.JsonString> r4 = com.google.api.client.json.JsonString.class
            r0 = r36
            java.lang.annotation.Annotation r4 = r0.getAnnotation(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 != 0) goto L_0x0289
        L_0x0271:
            r4 = 1
        L_0x0272:
            java.lang.String r5 = "number type formatted as a JSON number cannot use @JsonString annotation"
            com.google.api.client.util.Preconditions.checkArgument(r4, r5)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r34 == 0) goto L_0x0283
            java.lang.Class<java.math.BigDecimal> r4 = java.math.BigDecimal.class
            r0 = r34
            boolean r4 = r0.isAssignableFrom(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 == 0) goto L_0x028b
        L_0x0283:
            java.math.BigDecimal r4 = r35.getDecimalValue()     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x0289:
            r4 = 0
            goto L_0x0272
        L_0x028b:
            java.lang.Class<java.math.BigInteger> r4 = java.math.BigInteger.class
            r0 = r34
            if (r0 != r4) goto L_0x0297
            java.math.BigInteger r4 = r35.getBigIntegerValue()     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x0297:
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            r0 = r34
            if (r0 == r4) goto L_0x02a3
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r34
            if (r0 != r4) goto L_0x02ad
        L_0x02a3:
            double r4 = r35.getDoubleValue()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x02ad:
            java.lang.Class<java.lang.Long> r4 = java.lang.Long.class
            r0 = r34
            if (r0 == r4) goto L_0x02b9
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r34
            if (r0 != r4) goto L_0x02c3
        L_0x02b9:
            long r4 = r35.getLongValue()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x02c3:
            java.lang.Class<java.lang.Float> r4 = java.lang.Float.class
            r0 = r34
            if (r0 == r4) goto L_0x02cf
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r34
            if (r0 != r4) goto L_0x02d9
        L_0x02cf:
            float r4 = r35.getFloatValue()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x02d9:
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            r0 = r34
            if (r0 == r4) goto L_0x02e5
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r34
            if (r0 != r4) goto L_0x02ef
        L_0x02e5:
            int r4 = r35.getIntValue()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x02ef:
            java.lang.Class<java.lang.Short> r4 = java.lang.Short.class
            r0 = r34
            if (r0 == r4) goto L_0x02fb
            java.lang.Class r4 = java.lang.Short.TYPE     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r34
            if (r0 != r4) goto L_0x0305
        L_0x02fb:
            short r4 = r35.getShortValue()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x0305:
            java.lang.Class<java.lang.Byte> r4 = java.lang.Byte.class
            r0 = r34
            if (r0 == r4) goto L_0x0311
            java.lang.Class r4 = java.lang.Byte.TYPE     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r34
            if (r0 != r4) goto L_0x031b
        L_0x0311:
            byte r4 = r35.getByteValue()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Byte r4 = java.lang.Byte.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x031b:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x005a }
            r5.<init>()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.String r8 = "expected numeric type but got "
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r37
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.String r5 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x005a }
            r4.<init>(r5)     // Catch:{ IllegalArgumentException -> 0x005a }
            throw r4     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x0336:
            java.lang.String r4 = r35.getText()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.String r4 = r4.trim()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.util.Locale r5 = java.util.Locale.US     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.String r27 = r4.toLowerCase(r5)     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r34
            if (r0 == r4) goto L_0x035c
            java.lang.Class<java.lang.Float> r4 = java.lang.Float.class
            r0 = r34
            if (r0 == r4) goto L_0x035c
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r34
            if (r0 == r4) goto L_0x035c
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            r0 = r34
            if (r0 != r4) goto L_0x037a
        L_0x035c:
            java.lang.String r4 = "nan"
            r0 = r27
            boolean r4 = r0.equals(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 != 0) goto L_0x0398
            java.lang.String r4 = "infinity"
            r0 = r27
            boolean r4 = r0.equals(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 != 0) goto L_0x0398
            java.lang.String r4 = "-infinity"
            r0 = r27
            boolean r4 = r0.equals(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 != 0) goto L_0x0398
        L_0x037a:
            if (r34 == 0) goto L_0x0392
            java.lang.Class<java.lang.Number> r4 = java.lang.Number.class
            r0 = r34
            boolean r4 = r4.isAssignableFrom(r0)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 == 0) goto L_0x0392
            if (r36 == 0) goto L_0x03a4
            java.lang.Class<com.google.api.client.json.JsonString> r4 = com.google.api.client.json.JsonString.class
            r0 = r36
            java.lang.annotation.Annotation r4 = r0.getAnnotation(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 == 0) goto L_0x03a4
        L_0x0392:
            r4 = 1
        L_0x0393:
            java.lang.String r5 = "number field formatted as a JSON string must use the @JsonString annotation"
            com.google.api.client.util.Preconditions.checkArgument(r4, r5)     // Catch:{ IllegalArgumentException -> 0x005a }
        L_0x0398:
            java.lang.String r4 = r35.getText()     // Catch:{ IllegalArgumentException -> 0x005a }
            r0 = r37
            java.lang.Object r4 = com.google.api.client.util.Data.parsePrimitiveValue(r0, r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x03a4:
            r4 = 0
            goto L_0x0393
        L_0x03a6:
            if (r34 == 0) goto L_0x03ae
            boolean r4 = r34.isPrimitive()     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 != 0) goto L_0x03d6
        L_0x03ae:
            r4 = 1
        L_0x03af:
            java.lang.String r5 = "primitive number field but found a JSON null"
            com.google.api.client.util.Preconditions.checkArgument(r4, r5)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r34 == 0) goto L_0x03f0
            int r4 = r34.getModifiers()     // Catch:{ IllegalArgumentException -> 0x005a }
            r4 = r4 & 1536(0x600, float:2.152E-42)
            if (r4 == 0) goto L_0x03f0
            java.lang.Class<java.util.Collection> r4 = java.util.Collection.class
            r0 = r34
            boolean r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 == 0) goto L_0x03d8
            java.util.Collection r4 = com.google.api.client.util.Data.newCollectionInstance(r37)     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Class r4 = r4.getClass()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Object r4 = com.google.api.client.util.Data.nullOf(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x03d6:
            r4 = 0
            goto L_0x03af
        L_0x03d8:
            java.lang.Class<java.util.Map> r4 = java.util.Map.class
            r0 = r34
            boolean r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            if (r4 == 0) goto L_0x03f0
            java.util.Map r4 = com.google.api.client.util.Data.newMapInstance(r34)     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Class r4 = r4.getClass()     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Object r4 = com.google.api.client.util.Data.nullOf(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x03f0:
            r0 = r38
            r1 = r37
            java.lang.Class r4 = com.google.api.client.util.Types.getRawArrayComponentType(r0, r1)     // Catch:{ IllegalArgumentException -> 0x005a }
            java.lang.Object r4 = com.google.api.client.util.Data.nullOf(r4)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x002c
        L_0x03fe:
            r4 = r26
            goto L_0x0147
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.json.JsonParser.parseValue(java.lang.reflect.Field, java.lang.reflect.Type, java.util.ArrayList, java.lang.Object, com.google.api.client.json.CustomizeJsonParser, boolean):java.lang.Object");
    }

    private static Field getCachedTypemapFieldFor(Class<?> key) {
        if (key == null) {
            return null;
        }
        lock.lock();
        try {
            if (cachedTypemapFields.containsKey(key)) {
                return cachedTypemapFields.get(key);
            }
            Field value = null;
            for (FieldInfo fieldInfo : ClassInfo.m34of(key).getFieldInfos()) {
                Field field = fieldInfo.getField();
                JsonPolymorphicTypeMap typemapAnnotation = (JsonPolymorphicTypeMap) field.getAnnotation(JsonPolymorphicTypeMap.class);
                if (typemapAnnotation != null) {
                    Preconditions.checkArgument(value == null, "Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", key);
                    Preconditions.checkArgument(Data.isPrimitive(field.getType()), "Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", key, field.getType());
                    value = field;
                    JsonPolymorphicTypeMap.TypeDef[] typeDefs = typemapAnnotation.typeDefinitions();
                    HashSet<String> typeDefKeys = Sets.newHashSet();
                    Preconditions.checkArgument(typeDefs.length > 0, "@JsonPolymorphicTypeMap must have at least one @TypeDef");
                    for (JsonPolymorphicTypeMap.TypeDef typeDef : typeDefs) {
                        Preconditions.checkArgument(typeDefKeys.add(typeDef.key()), "Class contains two @TypeDef annotations with identical key: %s", typeDef.key());
                    }
                }
            }
            cachedTypemapFields.put(key, value);
            lock.unlock();
            return value;
        } finally {
            lock.unlock();
        }
    }
}
