package com.shaded.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonTypeInfo {

    /* renamed from: com.shaded.fasterxml.jackson.annotation.JsonTypeInfo$As */
    public enum C1447As {
        PROPERTY,
        WRAPPER_OBJECT,
        WRAPPER_ARRAY,
        EXTERNAL_PROPERTY
    }

    public static abstract class None {
    }

    Class<?> defaultImpl() default None.class;

    C1447As include() default C1447As.PROPERTY;

    String property() default "";

    C1448Id use();

    boolean visible() default false;

    /* renamed from: com.shaded.fasterxml.jackson.annotation.JsonTypeInfo$Id */
    public enum C1448Id {
        NONE((String) null),
        CLASS("@class"),
        MINIMAL_CLASS("@c"),
        NAME("@type"),
        CUSTOM((String) null);
        
        private final String _defaultPropertyName;

        private C1448Id(String str) {
            this._defaultPropertyName = str;
        }

        public String getDefaultPropertyName() {
            return this._defaultPropertyName;
        }
    }
}
