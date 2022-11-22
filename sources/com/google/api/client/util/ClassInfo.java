package com.google.api.client.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class ClassInfo {
    private static final ConcurrentMap<Class<?>, ClassInfo> CACHE = new ConcurrentHashMap();
    private static final ConcurrentMap<Class<?>, ClassInfo> CACHE_IGNORE_CASE = new ConcurrentHashMap();
    private final Class<?> clazz;
    private final boolean ignoreCase;
    private final IdentityHashMap<String, FieldInfo> nameToFieldInfoMap = new IdentityHashMap<>();
    final List<String> names;

    /* renamed from: of */
    public static ClassInfo m34of(Class<?> underlyingClass) {
        return m35of(underlyingClass, false);
    }

    /* renamed from: of */
    public static ClassInfo m35of(Class<?> underlyingClass, boolean ignoreCase2) {
        ClassInfo newValue;
        if (underlyingClass == null) {
            return null;
        }
        ConcurrentMap<Class<?>, ClassInfo> cache = ignoreCase2 ? CACHE_IGNORE_CASE : CACHE;
        ClassInfo v = (ClassInfo) cache.get(underlyingClass);
        if (v == null && (newValue = new ClassInfo(underlyingClass, ignoreCase2)) != null && (v = cache.putIfAbsent(underlyingClass, newValue)) == null) {
            return newValue;
        }
        return v;
    }

    public Class<?> getUnderlyingClass() {
        return this.clazz;
    }

    public final boolean getIgnoreCase() {
        return this.ignoreCase;
    }

    public FieldInfo getFieldInfo(String name) {
        if (name != null) {
            if (this.ignoreCase) {
                name = name.toLowerCase(Locale.US);
            }
            name = name.intern();
        }
        return this.nameToFieldInfoMap.get(name);
    }

    public Field getField(String name) {
        FieldInfo fieldInfo = getFieldInfo(name);
        if (fieldInfo == null) {
            return null;
        }
        return fieldInfo.getField();
    }

    public boolean isEnum() {
        return this.clazz.isEnum();
    }

    public Collection<String> getNames() {
        return this.names;
    }

    private ClassInfo(Class<?> srcClass, boolean ignoreCase2) {
        List<String> unmodifiableList;
        Field field;
        this.clazz = srcClass;
        this.ignoreCase = ignoreCase2;
        Preconditions.checkArgument(!ignoreCase2 || !srcClass.isEnum(), "cannot ignore case on an enum: " + srcClass);
        TreeSet<String> nameSet = new TreeSet<>(new Comparator<String>() {
            public int compare(String s0, String s1) {
                if (Objects.equal(s0, s1)) {
                    return 0;
                }
                if (s0 == null) {
                    return -1;
                }
                if (s1 == null) {
                    return 1;
                }
                return s0.compareTo(s1);
            }
        });
        for (Field field2 : srcClass.getDeclaredFields()) {
            FieldInfo fieldInfo = FieldInfo.m37of(field2);
            if (fieldInfo != null) {
                String fieldName = fieldInfo.getName();
                fieldName = ignoreCase2 ? fieldName.toLowerCase(Locale.US).intern() : fieldName;
                FieldInfo conflictingFieldInfo = this.nameToFieldInfoMap.get(fieldName);
                boolean z = conflictingFieldInfo == null;
                Object[] objArr = new Object[4];
                objArr[0] = ignoreCase2 ? "case-insensitive " : "";
                objArr[1] = fieldName;
                objArr[2] = field2;
                if (conflictingFieldInfo == null) {
                    field = null;
                } else {
                    field = conflictingFieldInfo.getField();
                }
                objArr[3] = field;
                Preconditions.checkArgument(z, "two fields have the same %sname <%s>: %s and %s", objArr);
                this.nameToFieldInfoMap.put(fieldName, fieldInfo);
                nameSet.add(fieldName);
            }
        }
        Class<? super Object> superclass = srcClass.getSuperclass();
        if (superclass != null) {
            ClassInfo superClassInfo = m35of(superclass, ignoreCase2);
            nameSet.addAll(superClassInfo.names);
            for (Map.Entry<String, FieldInfo> e : superClassInfo.nameToFieldInfoMap.entrySet()) {
                String name = e.getKey();
                if (!this.nameToFieldInfoMap.containsKey(name)) {
                    this.nameToFieldInfoMap.put(name, e.getValue());
                }
            }
        }
        if (nameSet.isEmpty()) {
            unmodifiableList = Collections.emptyList();
        } else {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(nameSet));
        }
        this.names = unmodifiableList;
    }

    public Collection<FieldInfo> getFieldInfos() {
        return Collections.unmodifiableCollection(this.nameToFieldInfoMap.values());
    }
}
