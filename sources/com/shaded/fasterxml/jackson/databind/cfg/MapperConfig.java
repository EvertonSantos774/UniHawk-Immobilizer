package com.shaded.fasterxml.jackson.databind.cfg;

import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.type.TypeReference;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.MapperFeature;
import com.shaded.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.type.TypeBindings;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class MapperConfig<T extends MapperConfig<T>> implements ClassIntrospector.MixInResolver, Serializable {
    private static final long serialVersionUID = 8891625428805876137L;
    protected final BaseSettings _base;
    protected final int _mapperFeatures;

    public abstract Class<?> getActiveView();

    public abstract SubtypeResolver getSubtypeResolver();

    public abstract BeanDescription introspectClassAnnotations(JavaType javaType);

    public abstract BeanDescription introspectDirectClassAnnotations(JavaType javaType);

    public abstract boolean useRootWrapping();

    public abstract T with(MapperFeature... mapperFeatureArr);

    public abstract T without(MapperFeature... mapperFeatureArr);

    protected MapperConfig(BaseSettings baseSettings, int i) {
        this._base = baseSettings;
        this._mapperFeatures = i;
    }

    protected MapperConfig(MapperConfig<T> mapperConfig) {
        this._base = mapperConfig._base;
        this._mapperFeatures = mapperConfig._mapperFeatures;
    }

    public static <F extends Enum<F> & ConfigFeature> int collectFeatureDefaults(Class<F> cls) {
        int i;
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        int length = enumArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            Enum enumR = enumArr[i2];
            if (((ConfigFeature) enumR).enabledByDefault()) {
                i = ((ConfigFeature) enumR).getMask() | i3;
            } else {
                i = i3;
            }
            i2++;
            i3 = i;
        }
        return i3;
    }

    public final boolean isEnabled(MapperFeature mapperFeature) {
        return (this._mapperFeatures & mapperFeature.getMask()) != 0;
    }

    public final boolean isAnnotationProcessingEnabled() {
        return isEnabled(MapperFeature.USE_ANNOTATIONS);
    }

    public final boolean canOverrideAccessModifiers() {
        return isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS);
    }

    public final boolean shouldSortPropertiesAlphabetically() {
        return isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
    }

    public ClassIntrospector getClassIntrospector() {
        return this._base.getClassIntrospector();
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._base.getAnnotationIntrospector();
    }

    public VisibilityChecker<?> getDefaultVisibilityChecker() {
        return this._base.getVisibilityChecker();
    }

    public final PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._base.getPropertyNamingStrategy();
    }

    public final HandlerInstantiator getHandlerInstantiator() {
        return this._base.getHandlerInstantiator();
    }

    public final TypeResolverBuilder<?> getDefaultTyper(JavaType javaType) {
        return this._base.getTypeResolverBuilder();
    }

    public final TypeFactory getTypeFactory() {
        return this._base.getTypeFactory();
    }

    public final JavaType constructType(Class<?> cls) {
        return getTypeFactory().constructType((Type) cls, (TypeBindings) null);
    }

    public final JavaType constructType(TypeReference<?> typeReference) {
        return getTypeFactory().constructType(typeReference.getType(), (TypeBindings) null);
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        return getTypeFactory().constructSpecializedType(javaType, cls);
    }

    public BeanDescription introspectClassAnnotations(Class<?> cls) {
        return introspectClassAnnotations(constructType(cls));
    }

    public BeanDescription introspectDirectClassAnnotations(Class<?> cls) {
        return introspectDirectClassAnnotations(constructType(cls));
    }

    public final DateFormat getDateFormat() {
        return this._base.getDateFormat();
    }

    public final Locale getLocale() {
        return this._base.getLocale();
    }

    public final TimeZone getTimeZone() {
        return this._base.getTimeZone();
    }

    public Base64Variant getBase64Variant() {
        return this._base.getBase64Variant();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.typeResolverBuilderInstance(r1, r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder<?> typeResolverBuilderInstance(com.shaded.fasterxml.jackson.databind.introspect.Annotated r2, java.lang.Class<? extends com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder<?>> r3) {
        /*
            r1 = this;
            com.shaded.fasterxml.jackson.databind.cfg.HandlerInstantiator r0 = r1.getHandlerInstantiator()
            if (r0 == 0) goto L_0x000d
            com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder r0 = r0.typeResolverBuilderInstance(r1, r2, r3)
            if (r0 == 0) goto L_0x000d
        L_0x000c:
            return r0
        L_0x000d:
            boolean r0 = r1.canOverrideAccessModifiers()
            java.lang.Object r0 = com.shaded.fasterxml.jackson.databind.util.ClassUtil.createInstance(r3, r0)
            com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder r0 = (com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder) r0
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.cfg.MapperConfig.typeResolverBuilderInstance(com.shaded.fasterxml.jackson.databind.introspect.Annotated, java.lang.Class):com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.typeIdResolverInstance(r1, r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver typeIdResolverInstance(com.shaded.fasterxml.jackson.databind.introspect.Annotated r2, java.lang.Class<? extends com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver> r3) {
        /*
            r1 = this;
            com.shaded.fasterxml.jackson.databind.cfg.HandlerInstantiator r0 = r1.getHandlerInstantiator()
            if (r0 == 0) goto L_0x000d
            com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver r0 = r0.typeIdResolverInstance(r1, r2, r3)
            if (r0 == 0) goto L_0x000d
        L_0x000c:
            return r0
        L_0x000d:
            boolean r0 = r1.canOverrideAccessModifiers()
            java.lang.Object r0 = com.shaded.fasterxml.jackson.databind.util.ClassUtil.createInstance(r3, r0)
            com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver r0 = (com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver) r0
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.cfg.MapperConfig.typeIdResolverInstance(com.shaded.fasterxml.jackson.databind.introspect.Annotated, java.lang.Class):com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver");
    }
}
