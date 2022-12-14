package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.shaded.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.shaded.fasterxml.jackson.databind.deser.impl.InnerClassProperty;
import com.shaded.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty;
import com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.shaded.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.shaded.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.shaded.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import com.shaded.fasterxml.jackson.databind.util.ArrayBuilders;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import com.shaded.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BeanDeserializerBase extends StdDeserializer<Object> implements ContextualDeserializer, ResolvableDeserializer, Serializable {
    private static final long serialVersionUID = -2038793552422727904L;
    protected SettableAnyProperty _anySetter;
    protected final Map<String, SettableBeanProperty> _backRefs;
    protected final BeanPropertyMap _beanProperties;
    protected final JavaType _beanType;
    private final transient Annotations _classAnnotations;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected ExternalTypeHandler _externalTypeIdHandler;
    protected final HashSet<String> _ignorableProps;
    protected final boolean _ignoreAllUnknown;
    protected final ValueInjector[] _injectables;
    protected final boolean _needViewProcesing;
    protected boolean _nonStandardCreation;
    protected final ObjectIdReader _objectIdReader;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected final JsonFormat.Shape _serializationShape;
    protected transient HashMap<ClassKey, JsonDeserializer<Object>> _subDeserializers;
    protected UnwrappedPropertyHandler _unwrappedPropertyHandler;
    protected final ValueInstantiator _valueInstantiator;
    protected boolean _vanillaProcessing;

    /* access modifiers changed from: protected */
    public abstract Object _deserializeUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    /* access modifiers changed from: protected */
    public abstract BeanDeserializerBase asArrayDeserializer();

    public abstract Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    public abstract JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer);

    public abstract BeanDeserializerBase withIgnorableProperties(HashSet<String> hashSet);

    public abstract BeanDeserializerBase withObjectIdReader(ObjectIdReader objectIdReader);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BeanDeserializerBase(BeanDeserializerBuilder beanDeserializerBuilder, BeanDescription beanDescription, BeanPropertyMap beanPropertyMap, Map<String, SettableBeanProperty> map, HashSet<String> hashSet, boolean z, boolean z2) {
        super(beanDescription.getType());
        boolean z3;
        boolean z4 = true;
        JsonFormat.Shape shape = null;
        this._classAnnotations = beanDescription.getClassInfo().getAnnotations();
        this._beanType = beanDescription.getType();
        this._valueInstantiator = beanDeserializerBuilder.getValueInstantiator();
        this._beanProperties = beanPropertyMap;
        this._backRefs = map;
        this._ignorableProps = hashSet;
        this._ignoreAllUnknown = z;
        this._anySetter = beanDeserializerBuilder.getAnySetter();
        List<ValueInjector> injectables = beanDeserializerBuilder.getInjectables();
        this._injectables = (injectables == null || injectables.isEmpty()) ? null : (ValueInjector[]) injectables.toArray(new ValueInjector[injectables.size()]);
        this._objectIdReader = beanDeserializerBuilder.getObjectIdReader();
        if (this._unwrappedPropertyHandler != null || this._valueInstantiator.canCreateUsingDelegate() || this._valueInstantiator.canCreateFromObjectWith() || !this._valueInstantiator.canCreateUsingDefault()) {
            z3 = true;
        } else {
            z3 = false;
        }
        this._nonStandardCreation = z3;
        JsonFormat.Value findExpectedFormat = beanDescription.findExpectedFormat((JsonFormat.Value) null);
        this._serializationShape = findExpectedFormat != null ? findExpectedFormat.getShape() : shape;
        this._needViewProcesing = z2;
        this._vanillaProcessing = (this._nonStandardCreation || this._injectables != null || this._needViewProcesing || this._objectIdReader == null) ? false : z4;
    }

    protected BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase) {
        this(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
    }

    protected BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase, boolean z) {
        super(beanDeserializerBase._beanType);
        this._classAnnotations = beanDeserializerBase._classAnnotations;
        this._beanType = beanDeserializerBase._beanType;
        this._valueInstantiator = beanDeserializerBase._valueInstantiator;
        this._delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        this._propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        this._beanProperties = beanDeserializerBase._beanProperties;
        this._backRefs = beanDeserializerBase._backRefs;
        this._ignorableProps = beanDeserializerBase._ignorableProps;
        this._ignoreAllUnknown = z;
        this._anySetter = beanDeserializerBase._anySetter;
        this._injectables = beanDeserializerBase._injectables;
        this._objectIdReader = beanDeserializerBase._objectIdReader;
        this._nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        this._unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        this._needViewProcesing = beanDeserializerBase._needViewProcesing;
        this._serializationShape = beanDeserializerBase._serializationShape;
        this._vanillaProcessing = beanDeserializerBase._vanillaProcessing;
    }

    protected BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase, NameTransformer nameTransformer) {
        super(beanDeserializerBase._beanType);
        this._classAnnotations = beanDeserializerBase._classAnnotations;
        this._beanType = beanDeserializerBase._beanType;
        this._valueInstantiator = beanDeserializerBase._valueInstantiator;
        this._delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        this._propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        this._backRefs = beanDeserializerBase._backRefs;
        this._ignorableProps = beanDeserializerBase._ignorableProps;
        this._ignoreAllUnknown = nameTransformer != null || beanDeserializerBase._ignoreAllUnknown;
        this._anySetter = beanDeserializerBase._anySetter;
        this._injectables = beanDeserializerBase._injectables;
        this._objectIdReader = beanDeserializerBase._objectIdReader;
        this._nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        UnwrappedPropertyHandler unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        if (nameTransformer != null) {
            unwrappedPropertyHandler = unwrappedPropertyHandler != null ? unwrappedPropertyHandler.renameAll(nameTransformer) : unwrappedPropertyHandler;
            this._beanProperties = beanDeserializerBase._beanProperties.renameAll(nameTransformer);
        } else {
            this._beanProperties = beanDeserializerBase._beanProperties;
        }
        this._unwrappedPropertyHandler = unwrappedPropertyHandler;
        this._needViewProcesing = beanDeserializerBase._needViewProcesing;
        this._serializationShape = beanDeserializerBase._serializationShape;
        this._vanillaProcessing = false;
    }

    public BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase, ObjectIdReader objectIdReader) {
        super(beanDeserializerBase._beanType);
        this._classAnnotations = beanDeserializerBase._classAnnotations;
        this._beanType = beanDeserializerBase._beanType;
        this._valueInstantiator = beanDeserializerBase._valueInstantiator;
        this._delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        this._propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        this._backRefs = beanDeserializerBase._backRefs;
        this._ignorableProps = beanDeserializerBase._ignorableProps;
        this._ignoreAllUnknown = beanDeserializerBase._ignoreAllUnknown;
        this._anySetter = beanDeserializerBase._anySetter;
        this._injectables = beanDeserializerBase._injectables;
        this._nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        this._unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        this._needViewProcesing = beanDeserializerBase._needViewProcesing;
        this._serializationShape = beanDeserializerBase._serializationShape;
        this._vanillaProcessing = beanDeserializerBase._vanillaProcessing;
        this._objectIdReader = objectIdReader;
        if (objectIdReader == null) {
            this._beanProperties = beanDeserializerBase._beanProperties;
            return;
        }
        this._beanProperties = beanDeserializerBase._beanProperties.withProperty(new ObjectIdValueProperty(objectIdReader, true));
    }

    public BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase, HashSet<String> hashSet) {
        super(beanDeserializerBase._beanType);
        this._classAnnotations = beanDeserializerBase._classAnnotations;
        this._beanType = beanDeserializerBase._beanType;
        this._valueInstantiator = beanDeserializerBase._valueInstantiator;
        this._delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        this._propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        this._backRefs = beanDeserializerBase._backRefs;
        this._ignorableProps = hashSet;
        this._ignoreAllUnknown = beanDeserializerBase._ignoreAllUnknown;
        this._anySetter = beanDeserializerBase._anySetter;
        this._injectables = beanDeserializerBase._injectables;
        this._nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        this._unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        this._needViewProcesing = beanDeserializerBase._needViewProcesing;
        this._serializationShape = beanDeserializerBase._serializationShape;
        this._vanillaProcessing = beanDeserializerBase._vanillaProcessing;
        this._objectIdReader = beanDeserializerBase._objectIdReader;
        this._beanProperties = beanDeserializerBase._beanProperties;
    }

    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        ExternalTypeHandler.Builder builder;
        SettableBeanProperty settableBeanProperty;
        JsonDeserializer<?> createContextual;
        ExternalTypeHandler.Builder builder2;
        UnwrappedPropertyHandler unwrappedPropertyHandler;
        boolean z = false;
        if (this._valueInstantiator.canCreateFromObjectWith()) {
            this._propertyBasedCreator = PropertyBasedCreator.construct(deserializationContext, this._valueInstantiator, this._valueInstantiator.getFromObjectArguments(deserializationContext.getConfig()));
            builder = null;
            for (SettableBeanProperty next : this._propertyBasedCreator.properties()) {
                if (next.hasValueTypeDeserializer()) {
                    TypeDeserializer valueTypeDeserializer = next.getValueTypeDeserializer();
                    if (valueTypeDeserializer.getTypeInclusion() == JsonTypeInfo.C1447As.EXTERNAL_PROPERTY) {
                        if (builder == null) {
                            builder = new ExternalTypeHandler.Builder();
                        }
                        builder.addExternal(next, valueTypeDeserializer);
                    }
                }
            }
        } else {
            builder = null;
        }
        Iterator<SettableBeanProperty> it = this._beanProperties.iterator();
        UnwrappedPropertyHandler unwrappedPropertyHandler2 = null;
        ExternalTypeHandler.Builder builder3 = builder;
        while (it.hasNext()) {
            SettableBeanProperty next2 = it.next();
            if (!next2.hasValueDeserializer()) {
                JsonDeserializer<Object> findConvertingDeserializer = findConvertingDeserializer(deserializationContext, next2);
                if (findConvertingDeserializer == null) {
                    findConvertingDeserializer = findDeserializer(deserializationContext, next2.getType(), next2);
                }
                settableBeanProperty = next2.withValueDeserializer(findConvertingDeserializer);
            } else {
                JsonDeserializer<Object> valueDeserializer = next2.getValueDeserializer();
                if (!(valueDeserializer instanceof ContextualDeserializer) || (createContextual = ((ContextualDeserializer) valueDeserializer).createContextual(deserializationContext, next2)) == valueDeserializer) {
                    settableBeanProperty = next2;
                } else {
                    settableBeanProperty = next2.withValueDeserializer(createContextual);
                }
            }
            SettableBeanProperty _resolveManagedReferenceProperty = _resolveManagedReferenceProperty(deserializationContext, settableBeanProperty);
            SettableBeanProperty _resolveUnwrappedProperty = _resolveUnwrappedProperty(deserializationContext, _resolveManagedReferenceProperty);
            if (_resolveUnwrappedProperty != null) {
                if (unwrappedPropertyHandler2 == null) {
                    unwrappedPropertyHandler = new UnwrappedPropertyHandler();
                } else {
                    unwrappedPropertyHandler = unwrappedPropertyHandler2;
                }
                unwrappedPropertyHandler.addProperty(_resolveUnwrappedProperty);
                unwrappedPropertyHandler2 = unwrappedPropertyHandler;
            } else {
                SettableBeanProperty _resolveInnerClassValuedProperty = _resolveInnerClassValuedProperty(deserializationContext, _resolveManagedReferenceProperty);
                if (_resolveInnerClassValuedProperty != next2) {
                    this._beanProperties.replace(_resolveInnerClassValuedProperty);
                }
                if (_resolveInnerClassValuedProperty.hasValueTypeDeserializer()) {
                    TypeDeserializer valueTypeDeserializer2 = _resolveInnerClassValuedProperty.getValueTypeDeserializer();
                    if (valueTypeDeserializer2.getTypeInclusion() == JsonTypeInfo.C1447As.EXTERNAL_PROPERTY) {
                        if (builder3 == null) {
                            builder2 = new ExternalTypeHandler.Builder();
                        } else {
                            builder2 = builder3;
                        }
                        builder2.addExternal(_resolveInnerClassValuedProperty, valueTypeDeserializer2);
                        this._beanProperties.remove(_resolveInnerClassValuedProperty);
                        builder3 = builder2;
                    }
                }
            }
        }
        if (this._anySetter != null && !this._anySetter.hasValueDeserializer()) {
            this._anySetter = this._anySetter.withValueDeserializer(findDeserializer(deserializationContext, this._anySetter.getType(), this._anySetter.getProperty()));
        }
        if (this._valueInstantiator.canCreateUsingDelegate()) {
            JavaType delegateType = this._valueInstantiator.getDelegateType(deserializationContext.getConfig());
            if (delegateType == null) {
                throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._beanType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
            }
            this._delegateDeserializer = findDeserializer(deserializationContext, delegateType, new BeanProperty.Std((String) null, delegateType, (PropertyName) null, this._classAnnotations, this._valueInstantiator.getDelegateCreator(), false));
        }
        if (builder3 != null) {
            this._externalTypeIdHandler = builder3.build();
            this._nonStandardCreation = true;
        }
        this._unwrappedPropertyHandler = unwrappedPropertyHandler2;
        if (unwrappedPropertyHandler2 != null) {
            this._nonStandardCreation = true;
        }
        if (this._vanillaProcessing && !this._nonStandardCreation) {
            z = true;
        }
        this._vanillaProcessing = z;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        Object findDeserializationConverter;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null || (findDeserializationConverter = annotationIntrospector.findDeserializationConverter(settableBeanProperty.getMember())) == null) {
            return null;
        }
        Converter<Object, Object> converterInstance = deserializationContext.converterInstance(settableBeanProperty.getMember(), findDeserializationConverter);
        JavaType inputType = converterInstance.getInputType(deserializationContext.getTypeFactory());
        return new StdDelegatingDeserializer(converterInstance, inputType, deserializationContext.findContextualValueDeserializer(inputType, settableBeanProperty));
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        ObjectIdReader objectIdReader;
        String[] strArr;
        BeanDeserializerBase beanDeserializerBase;
        JsonFormat.Shape shape;
        JsonFormat.Value findFormat;
        JavaType javaType;
        ObjectIdGenerator objectIdGeneratorInstance;
        SettableBeanProperty settableBeanProperty;
        ObjectIdReader objectIdReader2 = this._objectIdReader;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        AnnotatedMember member = (beanProperty == null || annotationIntrospector == null) ? null : beanProperty.getMember();
        if (beanProperty == null || annotationIntrospector == null) {
            objectIdReader = objectIdReader2;
            strArr = null;
        } else {
            String[] findPropertiesToIgnore = annotationIntrospector.findPropertiesToIgnore(member);
            ObjectIdInfo findObjectIdInfo = annotationIntrospector.findObjectIdInfo(member);
            if (findObjectIdInfo != null) {
                ObjectIdInfo findObjectReferenceInfo = annotationIntrospector.findObjectReferenceInfo(member, findObjectIdInfo);
                Class<? extends ObjectIdGenerator<?>> generatorType = findObjectReferenceInfo.getGeneratorType();
                if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
                    String propertyName = findObjectReferenceInfo.getPropertyName();
                    settableBeanProperty = findProperty(propertyName);
                    if (settableBeanProperty == null) {
                        throw new IllegalArgumentException("Invalid Object Id definition for " + getBeanClass().getName() + ": can not find property with name '" + propertyName + "'");
                    }
                    javaType = settableBeanProperty.getType();
                    objectIdGeneratorInstance = new PropertyBasedObjectIdGenerator(findObjectReferenceInfo.getScope());
                } else {
                    javaType = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType(generatorType), (Class<?>) ObjectIdGenerator.class)[0];
                    objectIdGeneratorInstance = deserializationContext.objectIdGeneratorInstance(member, findObjectReferenceInfo);
                    settableBeanProperty = null;
                }
                objectIdReader = ObjectIdReader.construct(javaType, findObjectReferenceInfo.getPropertyName(), objectIdGeneratorInstance, deserializationContext.findRootValueDeserializer(javaType), settableBeanProperty);
                strArr = findPropertiesToIgnore;
            } else {
                objectIdReader = objectIdReader2;
                strArr = findPropertiesToIgnore;
            }
        }
        if (objectIdReader == null || objectIdReader == this._objectIdReader) {
            beanDeserializerBase = this;
        } else {
            beanDeserializerBase = withObjectIdReader(objectIdReader);
        }
        if (!(strArr == null || strArr.length == 0)) {
            beanDeserializerBase = beanDeserializerBase.withIgnorableProperties(ArrayBuilders.setAndArray(beanDeserializerBase._ignorableProps, strArr));
        }
        if (member == null || (findFormat = annotationIntrospector.findFormat((Annotated) member)) == null) {
            shape = null;
        } else {
            shape = findFormat.getShape();
        }
        if (shape == null) {
            shape = this._serializationShape;
        }
        if (shape == JsonFormat.Shape.ARRAY) {
            return beanDeserializerBase.asArrayDeserializer();
        }
        return beanDeserializerBase;
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty _resolveManagedReferenceProperty(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) {
        SettableBeanProperty findBackReference;
        String managedReferenceName = settableBeanProperty.getManagedReferenceName();
        if (managedReferenceName == null) {
            return settableBeanProperty;
        }
        JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
        boolean z = false;
        if (valueDeserializer instanceof BeanDeserializerBase) {
            findBackReference = ((BeanDeserializerBase) valueDeserializer).findBackReference(managedReferenceName);
        } else if (valueDeserializer instanceof ContainerDeserializerBase) {
            JsonDeserializer<Object> contentDeserializer = ((ContainerDeserializerBase) valueDeserializer).getContentDeserializer();
            if (!(contentDeserializer instanceof BeanDeserializerBase)) {
                throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': value deserializer is of type ContainerDeserializerBase, but content type is not handled by a BeanDeserializer " + " (instead it's of type " + (contentDeserializer == null ? "NULL" : contentDeserializer.getClass().getName()) + ")");
            }
            findBackReference = ((BeanDeserializerBase) contentDeserializer).findBackReference(managedReferenceName);
            z = true;
        } else if (valueDeserializer instanceof AbstractDeserializer) {
            findBackReference = ((AbstractDeserializer) valueDeserializer).findBackReference(managedReferenceName);
        } else {
            throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': type for value deserializer is not BeanDeserializer or ContainerDeserializerBase, but " + valueDeserializer.getClass().getName());
        }
        if (findBackReference == null) {
            throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': no back reference property found from type " + settableBeanProperty.getType());
        }
        JavaType javaType = this._beanType;
        JavaType type = findBackReference.getType();
        if (!type.getRawClass().isAssignableFrom(javaType.getRawClass())) {
            throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': back reference type (" + type.getRawClass().getName() + ") not compatible with managed type (" + javaType.getRawClass().getName() + ")");
        }
        return new ManagedReferenceProperty(settableBeanProperty, managedReferenceName, findBackReference, this._classAnnotations, z);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r1 = r4.getValueDeserializer();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty _resolveUnwrappedProperty(com.shaded.fasterxml.jackson.databind.DeserializationContext r3, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r4) {
        /*
            r2 = this;
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = r4.getMember()
            if (r0 == 0) goto L_0x0021
            com.shaded.fasterxml.jackson.databind.AnnotationIntrospector r1 = r3.getAnnotationIntrospector()
            com.shaded.fasterxml.jackson.databind.util.NameTransformer r0 = r1.findUnwrappingNameTransformer(r0)
            if (r0 == 0) goto L_0x0021
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r1 = r4.getValueDeserializer()
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r0 = r1.unwrappingDeserializer(r0)
            if (r0 == r1) goto L_0x0021
            if (r0 == 0) goto L_0x0021
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r0 = r4.withValueDeserializer(r0)
        L_0x0020:
            return r0
        L_0x0021:
            r0 = 0
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase._resolveUnwrappedProperty(com.shaded.fasterxml.jackson.databind.DeserializationContext, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty):com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty");
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty _resolveInnerClassValuedProperty(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) {
        Class<?> rawClass;
        Class<?> outerClass;
        JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
        if (!(valueDeserializer instanceof BeanDeserializerBase) || ((BeanDeserializerBase) valueDeserializer).getValueInstantiator().canCreateUsingDefault() || (outerClass = ClassUtil.getOuterClass(rawClass)) == null || outerClass != this._beanType.getRawClass()) {
            return settableBeanProperty;
        }
        for (Constructor constructor : (rawClass = settableBeanProperty.getType().getRawClass()).getConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0] == outerClass) {
                if (deserializationContext.getConfig().canOverrideAccessModifiers()) {
                    ClassUtil.checkAndFixAccess(constructor);
                }
                return new InnerClassProperty(settableBeanProperty, (Constructor<?>) constructor);
            }
        }
        return settableBeanProperty;
    }

    public boolean isCachable() {
        return true;
    }

    public ObjectIdReader getObjectIdReader() {
        return this._objectIdReader;
    }

    public boolean hasProperty(String str) {
        return this._beanProperties.find(str) != null;
    }

    public boolean hasViews() {
        return this._needViewProcesing;
    }

    public int getPropertyCount() {
        return this._beanProperties.size();
    }

    public Collection<Object> getKnownPropertyNames() {
        ArrayList arrayList = new ArrayList();
        Iterator<SettableBeanProperty> it = this._beanProperties.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        return arrayList;
    }

    public final Class<?> getBeanClass() {
        return this._beanType.getRawClass();
    }

    public JavaType getValueType() {
        return this._beanType;
    }

    public Iterator<SettableBeanProperty> properties() {
        if (this._beanProperties != null) {
            return this._beanProperties.iterator();
        }
        throw new IllegalStateException("Can only call after BeanDeserializer has been resolved");
    }

    public Iterator<SettableBeanProperty> creatorProperties() {
        if (this._propertyBasedCreator == null) {
            return Collections.emptyList().iterator();
        }
        return this._propertyBasedCreator.properties().iterator();
    }

    public SettableBeanProperty findProperty(String str) {
        SettableBeanProperty find = this._beanProperties == null ? null : this._beanProperties.find(str);
        if (find != null || this._propertyBasedCreator == null) {
            return find;
        }
        return this._propertyBasedCreator.findCreatorProperty(str);
    }

    public SettableBeanProperty findBackReference(String str) {
        if (this._backRefs == null) {
            return null;
        }
        return this._backRefs.get(str);
    }

    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    public void replaceProperty(SettableBeanProperty settableBeanProperty, SettableBeanProperty settableBeanProperty2) {
        this._beanProperties.replace(settableBeanProperty2);
    }

    public final Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        JsonToken currentToken;
        if (this._objectIdReader == null || (currentToken = jsonParser.getCurrentToken()) == null || !currentToken.isScalarValue()) {
            return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
        }
        return deserializeFromObjectId(jsonParser, deserializationContext);
    }

    /* access modifiers changed from: protected */
    public Object deserializeWithObjectId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String str = this._objectIdReader.propertyName;
        if (str.equals(jsonParser.getCurrentName())) {
            return deserializeFromObject(jsonParser, deserializationContext);
        }
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        TokenBuffer tokenBuffer2 = null;
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            if (tokenBuffer2 != null) {
                tokenBuffer2.writeFieldName(currentName);
                jsonParser.nextToken();
                tokenBuffer2.copyCurrentStructure(jsonParser);
            } else if (str.equals(currentName)) {
                tokenBuffer2 = new TokenBuffer(jsonParser.getCodec());
                tokenBuffer2.writeFieldName(currentName);
                jsonParser.nextToken();
                tokenBuffer2.copyCurrentStructure(jsonParser);
                tokenBuffer2.append(tokenBuffer);
                tokenBuffer = null;
            } else {
                tokenBuffer.writeFieldName(currentName);
                jsonParser.nextToken();
                tokenBuffer.copyCurrentStructure(jsonParser);
            }
            jsonParser.nextToken();
        }
        if (tokenBuffer2 != null) {
            tokenBuffer = tokenBuffer2;
        }
        tokenBuffer.writeEndObject();
        JsonParser asParser = tokenBuffer.asParser();
        asParser.nextToken();
        return deserializeFromObject(asParser, deserializationContext);
    }

    /* access modifiers changed from: protected */
    public Object deserializeFromObjectId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object deserialize = this._objectIdReader.deserializer.deserialize(jsonParser, deserializationContext);
        Object obj = deserializationContext.findObjectId(deserialize, this._objectIdReader.generator).item;
        if (obj != null) {
            return obj;
        }
        throw new IllegalStateException("Could not resolve Object Id [" + deserialize + "] (for " + this._beanType + ") -- unresolved forward-reference?");
    }

    /* access modifiers changed from: protected */
    public Object deserializeFromObjectUsingNonDefault(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        if (this._beanType.isAbstract()) {
            throw JsonMappingException.from(jsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
        }
        throw JsonMappingException.from(jsonParser, "No suitable constructor found for type " + this._beanType + ": can not instantiate from JSON object (need to add/enable type information?)");
    }

    public Object deserializeFromNumber(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._objectIdReader != null) {
            return deserializeFromObjectId(jsonParser, deserializationContext);
        }
        switch (jsonParser.getNumberType()) {
            case INT:
                if (this._delegateDeserializer == null || this._valueInstantiator.canCreateFromInt()) {
                    return this._valueInstantiator.createFromInt(deserializationContext, jsonParser.getIntValue());
                }
                Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
                if (this._injectables == null) {
                    return createUsingDelegate;
                }
                injectValues(deserializationContext, createUsingDelegate);
                return createUsingDelegate;
            case LONG:
                if (this._delegateDeserializer == null || this._valueInstantiator.canCreateFromInt()) {
                    return this._valueInstantiator.createFromLong(deserializationContext, jsonParser.getLongValue());
                }
                Object createUsingDelegate2 = this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
                if (this._injectables == null) {
                    return createUsingDelegate2;
                }
                injectValues(deserializationContext, createUsingDelegate2);
                return createUsingDelegate2;
            default:
                if (this._delegateDeserializer != null) {
                    Object createUsingDelegate3 = this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
                    if (this._injectables == null) {
                        return createUsingDelegate3;
                    }
                    injectValues(deserializationContext, createUsingDelegate3);
                    return createUsingDelegate3;
                }
                throw deserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON integer number");
        }
    }

    public Object deserializeFromString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._objectIdReader != null) {
            return deserializeFromObjectId(jsonParser, deserializationContext);
        }
        if (this._delegateDeserializer == null || this._valueInstantiator.canCreateFromString()) {
            return this._valueInstantiator.createFromString(deserializationContext, jsonParser.getText());
        }
        Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        if (this._injectables == null) {
            return createUsingDelegate;
        }
        injectValues(deserializationContext, createUsingDelegate);
        return createUsingDelegate;
    }

    public Object deserializeFromDouble(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        switch (jsonParser.getNumberType()) {
            case FLOAT:
            case DOUBLE:
                if (this._delegateDeserializer == null || this._valueInstantiator.canCreateFromDouble()) {
                    return this._valueInstantiator.createFromDouble(deserializationContext, jsonParser.getDoubleValue());
                }
                Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
                if (this._injectables == null) {
                    return createUsingDelegate;
                }
                injectValues(deserializationContext, createUsingDelegate);
                return createUsingDelegate;
            default:
                if (this._delegateDeserializer != null) {
                    return this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
                }
                throw deserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON floating-point number");
        }
    }

    public Object deserializeFromBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._delegateDeserializer == null || this._valueInstantiator.canCreateFromBoolean()) {
            return this._valueInstantiator.createFromBoolean(deserializationContext, jsonParser.getCurrentToken() == JsonToken.VALUE_TRUE);
        }
        Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        if (this._injectables == null) {
            return createUsingDelegate;
        }
        injectValues(deserializationContext, createUsingDelegate);
        return createUsingDelegate;
    }

    public Object deserializeFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._delegateDeserializer != null) {
            try {
                Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
                if (this._injectables != null) {
                    injectValues(deserializationContext, createUsingDelegate);
                }
                return createUsingDelegate;
            } catch (Exception e) {
                wrapInstantiationProblem(e, deserializationContext);
            }
        }
        throw deserializationContext.mappingException(getBeanClass());
    }

    /* access modifiers changed from: protected */
    public void injectValues(DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        for (ValueInjector inject : this._injectables) {
            inject.inject(deserializationContext, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        if (this._ignoreAllUnknown || (this._ignorableProps != null && this._ignorableProps.contains(str))) {
            jsonParser.skipChildren();
        } else {
            super.handleUnknownProperty(jsonParser, deserializationContext, obj, str);
        }
    }

    /* access modifiers changed from: protected */
    public Object handleUnknownProperties(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        tokenBuffer.writeEndObject();
        JsonParser asParser = tokenBuffer.asParser();
        while (asParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = asParser.getCurrentName();
            asParser.nextToken();
            handleUnknownProperty(asParser, deserializationContext, obj, currentName);
        }
        return obj;
    }

    /* access modifiers changed from: protected */
    public void handleUnknownVanilla(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        if (this._ignorableProps != null && this._ignorableProps.contains(str)) {
            jsonParser.skipChildren();
        } else if (this._anySetter != null) {
            try {
                this._anySetter.deserializeAndSet(jsonParser, deserializationContext, obj, str);
            } catch (Exception e) {
                wrapAndThrow((Throwable) e, obj, str, deserializationContext);
            }
        } else {
            handleUnknownProperty(jsonParser, deserializationContext, obj, str);
        }
    }

    /* access modifiers changed from: protected */
    public Object handlePolymorphic(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        Object obj2;
        Object obj3;
        JsonDeserializer<Object> _findSubclassDeserializer = _findSubclassDeserializer(deserializationContext, obj, tokenBuffer);
        if (_findSubclassDeserializer != null) {
            if (tokenBuffer != null) {
                tokenBuffer.writeEndObject();
                JsonParser asParser = tokenBuffer.asParser();
                asParser.nextToken();
                obj3 = _findSubclassDeserializer.deserialize(asParser, deserializationContext, obj);
            } else {
                obj3 = obj;
            }
            if (jsonParser != null) {
                return _findSubclassDeserializer.deserialize(jsonParser, deserializationContext, obj3);
            }
            return obj3;
        }
        if (tokenBuffer != null) {
            obj2 = handleUnknownProperties(deserializationContext, obj, tokenBuffer);
        } else {
            obj2 = obj;
        }
        if (jsonParser != null) {
            return deserialize(jsonParser, deserializationContext, obj2);
        }
        return obj2;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findSubclassDeserializer(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this) {
            jsonDeserializer = this._subDeserializers == null ? null : this._subDeserializers.get(new ClassKey(obj.getClass()));
        }
        if (jsonDeserializer == null && (jsonDeserializer = deserializationContext.findRootValueDeserializer(deserializationContext.constructType(obj.getClass()))) != null) {
            synchronized (this) {
                if (this._subDeserializers == null) {
                    this._subDeserializers = new HashMap<>();
                }
                this._subDeserializers.put(new ClassKey(obj.getClass()), jsonDeserializer);
            }
        }
        return jsonDeserializer;
    }

    public void wrapAndThrow(Throwable th, Object obj, String str, DeserializationContext deserializationContext) throws IOException {
        throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(th, deserializationContext), obj, str);
    }

    public void wrapAndThrow(Throwable th, Object obj, int i, DeserializationContext deserializationContext) throws IOException {
        throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(th, deserializationContext), obj, i);
    }

    private Throwable throwOrReturnThrowable(Throwable th, DeserializationContext deserializationContext) throws IOException {
        Throwable th2 = th;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        }
        boolean z = deserializationContext == null || deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (th2 instanceof IOException) {
            if (!z || !(th2 instanceof JsonProcessingException)) {
                throw ((IOException) th2);
            }
        } else if (!z && (th2 instanceof RuntimeException)) {
            throw ((RuntimeException) th2);
        }
        return th2;
    }

    /* access modifiers changed from: protected */
    public void wrapInstantiationProblem(Throwable th, DeserializationContext deserializationContext) throws IOException {
        Throwable th2 = th;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        }
        boolean z = deserializationContext == null || deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (th2 instanceof IOException) {
            throw ((IOException) th2);
        } else if (z || !(th2 instanceof RuntimeException)) {
            throw deserializationContext.instantiationException(this._beanType.getRawClass(), th2);
        } else {
            throw ((RuntimeException) th2);
        }
    }
}
