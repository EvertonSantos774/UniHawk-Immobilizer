package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.annotation.JsonInclude;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.shaded.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shaded.fasterxml.jackson.databind.annotation.NoClass;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnotationIntrospectorPair extends AnnotationIntrospector implements Serializable {
    private static final long serialVersionUID = 1;
    protected final AnnotationIntrospector _primary;
    protected final AnnotationIntrospector _secondary;

    public AnnotationIntrospectorPair(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        this._primary = annotationIntrospector;
        this._secondary = annotationIntrospector2;
    }

    public Version version() {
        return this._primary.version();
    }

    public static AnnotationIntrospector create(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        if (annotationIntrospector == null) {
            return annotationIntrospector2;
        }
        if (annotationIntrospector2 == null) {
            return annotationIntrospector;
        }
        return new AnnotationIntrospectorPair(annotationIntrospector, annotationIntrospector2);
    }

    public Collection<AnnotationIntrospector> allIntrospectors() {
        return allIntrospectors(new ArrayList());
    }

    public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> collection) {
        this._primary.allIntrospectors(collection);
        this._secondary.allIntrospectors(collection);
        return collection;
    }

    public boolean isAnnotationBundle(Annotation annotation) {
        return this._primary.isAnnotationBundle(annotation) || this._secondary.isAnnotationBundle(annotation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        r1 = r2._secondary.findRootName(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.shaded.fasterxml.jackson.databind.PropertyName findRootName(com.shaded.fasterxml.jackson.databind.introspect.AnnotatedClass r3) {
        /*
            r2 = this;
            com.shaded.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._primary
            com.shaded.fasterxml.jackson.databind.PropertyName r0 = r0.findRootName(r3)
            if (r0 != 0) goto L_0x000f
            com.shaded.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._secondary
            com.shaded.fasterxml.jackson.databind.PropertyName r0 = r0.findRootName(r3)
        L_0x000e:
            return r0
        L_0x000f:
            boolean r1 = r0.hasSimpleName()
            if (r1 != 0) goto L_0x000e
            com.shaded.fasterxml.jackson.databind.AnnotationIntrospector r1 = r2._secondary
            com.shaded.fasterxml.jackson.databind.PropertyName r1 = r1.findRootName(r3)
            if (r1 == 0) goto L_0x000e
            r0 = r1
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair.findRootName(com.shaded.fasterxml.jackson.databind.introspect.AnnotatedClass):com.shaded.fasterxml.jackson.databind.PropertyName");
    }

    public String[] findPropertiesToIgnore(Annotated annotated) {
        String[] findPropertiesToIgnore = this._primary.findPropertiesToIgnore(annotated);
        if (findPropertiesToIgnore == null) {
            return this._secondary.findPropertiesToIgnore(annotated);
        }
        return findPropertiesToIgnore;
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        Boolean findIgnoreUnknownProperties = this._primary.findIgnoreUnknownProperties(annotatedClass);
        if (findIgnoreUnknownProperties == null) {
            return this._secondary.findIgnoreUnknownProperties(annotatedClass);
        }
        return findIgnoreUnknownProperties;
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        Boolean isIgnorableType = this._primary.isIgnorableType(annotatedClass);
        if (isIgnorableType == null) {
            return this._secondary.isIgnorableType(annotatedClass);
        }
        return isIgnorableType;
    }

    public Object findFilterId(AnnotatedClass annotatedClass) {
        Object findFilterId = this._primary.findFilterId(annotatedClass);
        if (findFilterId == null) {
            return this._secondary.findFilterId(annotatedClass);
        }
        return findFilterId;
    }

    public Object findNamingStrategy(AnnotatedClass annotatedClass) {
        Object findNamingStrategy = this._primary.findNamingStrategy(annotatedClass);
        if (findNamingStrategy == null) {
            return this._secondary.findNamingStrategy(annotatedClass);
        }
        return findNamingStrategy;
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        return this._primary.findAutoDetectVisibility(annotatedClass, this._secondary.findAutoDetectVisibility(annotatedClass, visibilityChecker));
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        TypeResolverBuilder<?> findTypeResolver = this._primary.findTypeResolver(mapperConfig, annotatedClass, javaType);
        if (findTypeResolver == null) {
            return this._secondary.findTypeResolver(mapperConfig, annotatedClass, javaType);
        }
        return findTypeResolver;
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        TypeResolverBuilder<?> findPropertyTypeResolver = this._primary.findPropertyTypeResolver(mapperConfig, annotatedMember, javaType);
        if (findPropertyTypeResolver == null) {
            return this._secondary.findPropertyTypeResolver(mapperConfig, annotatedMember, javaType);
        }
        return findPropertyTypeResolver;
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        TypeResolverBuilder<?> findPropertyContentTypeResolver = this._primary.findPropertyContentTypeResolver(mapperConfig, annotatedMember, javaType);
        if (findPropertyContentTypeResolver == null) {
            return this._secondary.findPropertyContentTypeResolver(mapperConfig, annotatedMember, javaType);
        }
        return findPropertyContentTypeResolver;
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        List<NamedType> findSubtypes = this._primary.findSubtypes(annotated);
        List<NamedType> findSubtypes2 = this._secondary.findSubtypes(annotated);
        if (findSubtypes == null || findSubtypes.isEmpty()) {
            return findSubtypes2;
        }
        if (findSubtypes2 == null || findSubtypes2.isEmpty()) {
            return findSubtypes;
        }
        ArrayList arrayList = new ArrayList(findSubtypes.size() + findSubtypes2.size());
        arrayList.addAll(findSubtypes);
        arrayList.addAll(findSubtypes2);
        return arrayList;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        String findTypeName = this._primary.findTypeName(annotatedClass);
        if (findTypeName == null || findTypeName.length() == 0) {
            return this._secondary.findTypeName(annotatedClass);
        }
        return findTypeName;
    }

    public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        AnnotationIntrospector.ReferenceProperty findReferenceType = this._primary.findReferenceType(annotatedMember);
        if (findReferenceType == null) {
            return this._secondary.findReferenceType(annotatedMember);
        }
        return findReferenceType;
    }

    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember annotatedMember) {
        NameTransformer findUnwrappingNameTransformer = this._primary.findUnwrappingNameTransformer(annotatedMember);
        if (findUnwrappingNameTransformer == null) {
            return this._secondary.findUnwrappingNameTransformer(annotatedMember);
        }
        return findUnwrappingNameTransformer;
    }

    public Object findInjectableValueId(AnnotatedMember annotatedMember) {
        Object findInjectableValueId = this._primary.findInjectableValueId(annotatedMember);
        if (findInjectableValueId == null) {
            return this._secondary.findInjectableValueId(annotatedMember);
        }
        return findInjectableValueId;
    }

    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return this._primary.hasIgnoreMarker(annotatedMember) || this._secondary.hasIgnoreMarker(annotatedMember);
    }

    public Boolean hasRequiredMarker(AnnotatedMember annotatedMember) {
        Boolean hasRequiredMarker = this._primary.hasRequiredMarker(annotatedMember);
        if (hasRequiredMarker == null) {
            return this._secondary.hasRequiredMarker(annotatedMember);
        }
        return hasRequiredMarker;
    }

    public Object findSerializer(Annotated annotated) {
        Object findSerializer = this._primary.findSerializer(annotated);
        if (findSerializer == null) {
            return this._secondary.findSerializer(annotated);
        }
        return findSerializer;
    }

    public Object findKeySerializer(Annotated annotated) {
        Object findKeySerializer = this._primary.findKeySerializer(annotated);
        if (findKeySerializer == null || findKeySerializer == JsonSerializer.None.class || findKeySerializer == NoClass.class) {
            return this._secondary.findKeySerializer(annotated);
        }
        return findKeySerializer;
    }

    public Object findContentSerializer(Annotated annotated) {
        Object findContentSerializer = this._primary.findContentSerializer(annotated);
        if (findContentSerializer == null || findContentSerializer == JsonSerializer.None.class || findContentSerializer == NoClass.class) {
            return this._secondary.findContentSerializer(annotated);
        }
        return findContentSerializer;
    }

    public JsonInclude.Include findSerializationInclusion(Annotated annotated, JsonInclude.Include include) {
        return this._primary.findSerializationInclusion(annotated, this._secondary.findSerializationInclusion(annotated, include));
    }

    public Class<?> findSerializationType(Annotated annotated) {
        Class<?> findSerializationType = this._primary.findSerializationType(annotated);
        if (findSerializationType == null) {
            return this._secondary.findSerializationType(annotated);
        }
        return findSerializationType;
    }

    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        Class<?> findSerializationKeyType = this._primary.findSerializationKeyType(annotated, javaType);
        if (findSerializationKeyType == null) {
            return this._secondary.findSerializationKeyType(annotated, javaType);
        }
        return findSerializationKeyType;
    }

    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        Class<?> findSerializationContentType = this._primary.findSerializationContentType(annotated, javaType);
        if (findSerializationContentType == null) {
            return this._secondary.findSerializationContentType(annotated, javaType);
        }
        return findSerializationContentType;
    }

    public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
        JsonSerialize.Typing findSerializationTyping = this._primary.findSerializationTyping(annotated);
        if (findSerializationTyping == null) {
            return this._secondary.findSerializationTyping(annotated);
        }
        return findSerializationTyping;
    }

    public Object findSerializationConverter(Annotated annotated) {
        Object findSerializationConverter = this._primary.findSerializationConverter(annotated);
        if (findSerializationConverter == null) {
            return this._secondary.findSerializationConverter(annotated);
        }
        return findSerializationConverter;
    }

    public Object findSerializationContentConverter(AnnotatedMember annotatedMember) {
        Object findSerializationContentConverter = this._primary.findSerializationContentConverter(annotatedMember);
        if (findSerializationContentConverter == null) {
            return this._secondary.findSerializationContentConverter(annotatedMember);
        }
        return findSerializationContentConverter;
    }

    public Class<?>[] findViews(Annotated annotated) {
        Class<?>[] findViews = this._primary.findViews(annotated);
        if (findViews == null) {
            return this._secondary.findViews(annotated);
        }
        return findViews;
    }

    public Boolean isTypeId(AnnotatedMember annotatedMember) {
        Boolean isTypeId = this._primary.isTypeId(annotatedMember);
        if (isTypeId == null) {
            return this._secondary.isTypeId(annotatedMember);
        }
        return isTypeId;
    }

    public ObjectIdInfo findObjectIdInfo(Annotated annotated) {
        ObjectIdInfo findObjectIdInfo = this._primary.findObjectIdInfo(annotated);
        if (findObjectIdInfo == null) {
            return this._secondary.findObjectIdInfo(annotated);
        }
        return findObjectIdInfo;
    }

    public ObjectIdInfo findObjectReferenceInfo(Annotated annotated, ObjectIdInfo objectIdInfo) {
        return this._primary.findObjectReferenceInfo(annotated, this._secondary.findObjectReferenceInfo(annotated, objectIdInfo));
    }

    public JsonFormat.Value findFormat(Annotated annotated) {
        JsonFormat.Value findFormat = this._primary.findFormat(annotated);
        if (findFormat == null) {
            return this._secondary.findFormat(annotated);
        }
        return findFormat;
    }

    public PropertyName findWrapperName(Annotated annotated) {
        PropertyName findWrapperName;
        PropertyName findWrapperName2 = this._primary.findWrapperName(annotated);
        if (findWrapperName2 == null) {
            return this._secondary.findWrapperName(annotated);
        }
        if (findWrapperName2 != PropertyName.USE_DEFAULT || (findWrapperName = this._secondary.findWrapperName(annotated)) == null) {
            return findWrapperName2;
        }
        return findWrapperName;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        String[] findSerializationPropertyOrder = this._primary.findSerializationPropertyOrder(annotatedClass);
        if (findSerializationPropertyOrder == null) {
            return this._secondary.findSerializationPropertyOrder(annotatedClass);
        }
        return findSerializationPropertyOrder;
    }

    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
        Boolean findSerializationSortAlphabetically = this._primary.findSerializationSortAlphabetically(annotatedClass);
        if (findSerializationSortAlphabetically == null) {
            return this._secondary.findSerializationSortAlphabetically(annotatedClass);
        }
        return findSerializationSortAlphabetically;
    }

    public PropertyName findNameForSerialization(Annotated annotated) {
        PropertyName findNameForSerialization;
        PropertyName findNameForSerialization2 = this._primary.findNameForSerialization(annotated);
        if (findNameForSerialization2 == null) {
            return this._secondary.findNameForSerialization(annotated);
        }
        if (findNameForSerialization2 != PropertyName.USE_DEFAULT || (findNameForSerialization = this._secondary.findNameForSerialization(annotated)) == null) {
            return findNameForSerialization2;
        }
        return findNameForSerialization;
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        return this._primary.hasAsValueAnnotation(annotatedMethod) || this._secondary.hasAsValueAnnotation(annotatedMethod);
    }

    public String findEnumValue(Enum<?> enumR) {
        String findEnumValue = this._primary.findEnumValue(enumR);
        if (findEnumValue == null) {
            return this._secondary.findEnumValue(enumR);
        }
        return findEnumValue;
    }

    public Object findDeserializer(Annotated annotated) {
        Object findDeserializer = this._primary.findDeserializer(annotated);
        if (findDeserializer == null) {
            return this._secondary.findDeserializer(annotated);
        }
        return findDeserializer;
    }

    public Object findKeyDeserializer(Annotated annotated) {
        Object findKeyDeserializer = this._primary.findKeyDeserializer(annotated);
        if (findKeyDeserializer == null || findKeyDeserializer == KeyDeserializer.None.class || findKeyDeserializer == NoClass.class) {
            return this._secondary.findKeyDeserializer(annotated);
        }
        return findKeyDeserializer;
    }

    public Object findContentDeserializer(Annotated annotated) {
        Object findContentDeserializer = this._primary.findContentDeserializer(annotated);
        if (findContentDeserializer == null || findContentDeserializer == JsonDeserializer.None.class || findContentDeserializer == NoClass.class) {
            return this._secondary.findContentDeserializer(annotated);
        }
        return findContentDeserializer;
    }

    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType) {
        Class<?> findDeserializationType = this._primary.findDeserializationType(annotated, javaType);
        if (findDeserializationType == null) {
            return this._secondary.findDeserializationType(annotated, javaType);
        }
        return findDeserializationType;
    }

    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType) {
        Class<?> findDeserializationKeyType = this._primary.findDeserializationKeyType(annotated, javaType);
        if (findDeserializationKeyType == null) {
            return this._secondary.findDeserializationKeyType(annotated, javaType);
        }
        return findDeserializationKeyType;
    }

    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType) {
        Class<?> findDeserializationContentType = this._primary.findDeserializationContentType(annotated, javaType);
        if (findDeserializationContentType == null) {
            return this._secondary.findDeserializationContentType(annotated, javaType);
        }
        return findDeserializationContentType;
    }

    public Object findDeserializationConverter(Annotated annotated) {
        Object findDeserializationConverter = this._primary.findDeserializationConverter(annotated);
        if (findDeserializationConverter == null) {
            return this._secondary.findDeserializationConverter(annotated);
        }
        return findDeserializationConverter;
    }

    public Object findDeserializationContentConverter(AnnotatedMember annotatedMember) {
        Object findDeserializationContentConverter = this._primary.findDeserializationContentConverter(annotatedMember);
        if (findDeserializationContentConverter == null) {
            return this._secondary.findDeserializationContentConverter(annotatedMember);
        }
        return findDeserializationContentConverter;
    }

    public Object findValueInstantiator(AnnotatedClass annotatedClass) {
        Object findValueInstantiator = this._primary.findValueInstantiator(annotatedClass);
        if (findValueInstantiator == null) {
            return this._secondary.findValueInstantiator(annotatedClass);
        }
        return findValueInstantiator;
    }

    public Class<?> findPOJOBuilder(AnnotatedClass annotatedClass) {
        Class<?> findPOJOBuilder = this._primary.findPOJOBuilder(annotatedClass);
        if (findPOJOBuilder == null) {
            return this._secondary.findPOJOBuilder(annotatedClass);
        }
        return findPOJOBuilder;
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass annotatedClass) {
        JsonPOJOBuilder.Value findPOJOBuilderConfig = this._primary.findPOJOBuilderConfig(annotatedClass);
        if (findPOJOBuilderConfig == null) {
            return this._secondary.findPOJOBuilderConfig(annotatedClass);
        }
        return findPOJOBuilderConfig;
    }

    public PropertyName findNameForDeserialization(Annotated annotated) {
        PropertyName findNameForDeserialization;
        PropertyName findNameForDeserialization2 = this._primary.findNameForDeserialization(annotated);
        if (findNameForDeserialization2 == null) {
            return this._secondary.findNameForDeserialization(annotated);
        }
        if (findNameForDeserialization2 != PropertyName.USE_DEFAULT || (findNameForDeserialization = this._secondary.findNameForDeserialization(annotated)) == null) {
            return findNameForDeserialization2;
        }
        return findNameForDeserialization;
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return this._primary.hasAnySetterAnnotation(annotatedMethod) || this._secondary.hasAnySetterAnnotation(annotatedMethod);
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        return this._primary.hasAnyGetterAnnotation(annotatedMethod) || this._secondary.hasAnyGetterAnnotation(annotatedMethod);
    }

    public boolean hasCreatorAnnotation(Annotated annotated) {
        return this._primary.hasCreatorAnnotation(annotated) || this._secondary.hasCreatorAnnotation(annotated);
    }

    @Deprecated
    public boolean isHandled(Annotation annotation) {
        return this._primary.isHandled(annotation) || this._secondary.isHandled(annotation);
    }

    @Deprecated
    public String findDeserializationName(AnnotatedMethod annotatedMethod) {
        String findDeserializationName;
        String findDeserializationName2 = this._primary.findDeserializationName(annotatedMethod);
        if (findDeserializationName2 == null) {
            return this._secondary.findDeserializationName(annotatedMethod);
        }
        if (findDeserializationName2.length() != 0 || (findDeserializationName = this._secondary.findDeserializationName(annotatedMethod)) == null) {
            return findDeserializationName2;
        }
        return findDeserializationName;
    }

    @Deprecated
    public String findDeserializationName(AnnotatedField annotatedField) {
        String findDeserializationName;
        String findDeserializationName2 = this._primary.findDeserializationName(annotatedField);
        if (findDeserializationName2 == null) {
            return this._secondary.findDeserializationName(annotatedField);
        }
        if (findDeserializationName2.length() != 0 || (findDeserializationName = this._secondary.findDeserializationName(annotatedField)) == null) {
            return findDeserializationName2;
        }
        return findDeserializationName;
    }

    @Deprecated
    public String findDeserializationName(AnnotatedParameter annotatedParameter) {
        String findDeserializationName = this._primary.findDeserializationName(annotatedParameter);
        if (findDeserializationName == null) {
            return this._secondary.findDeserializationName(annotatedParameter);
        }
        return findDeserializationName;
    }

    @Deprecated
    public String findSerializationName(AnnotatedMethod annotatedMethod) {
        String findSerializationName;
        String findSerializationName2 = this._primary.findSerializationName(annotatedMethod);
        if (findSerializationName2 == null) {
            return this._secondary.findSerializationName(annotatedMethod);
        }
        if (findSerializationName2.length() != 0 || (findSerializationName = this._secondary.findSerializationName(annotatedMethod)) == null) {
            return findSerializationName2;
        }
        return findSerializationName;
    }

    @Deprecated
    public String findSerializationName(AnnotatedField annotatedField) {
        String findSerializationName;
        String findSerializationName2 = this._primary.findSerializationName(annotatedField);
        if (findSerializationName2 == null) {
            return this._secondary.findSerializationName(annotatedField);
        }
        if (findSerializationName2.length() != 0 || (findSerializationName = this._secondary.findSerializationName(annotatedField)) == null) {
            return findSerializationName2;
        }
        return findSerializationName;
    }
}
