package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;

public class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected final Converter<Object, T> _converter;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JavaType _delegateType;

    public StdDelegatingDeserializer(Converter<?, T> converter) {
        super((Class<?>) Object.class);
        this._converter = converter;
        this._delegateType = null;
        this._delegateDeserializer = null;
    }

    public StdDelegatingDeserializer(Converter<Object, T> converter, JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        super(javaType);
        this._converter = converter;
        this._delegateType = javaType;
        this._delegateDeserializer = jsonDeserializer;
    }

    /* access modifiers changed from: protected */
    public StdDelegatingDeserializer<T> withDelegate(Converter<Object, T> converter, JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        if (getClass() == StdDelegatingDeserializer.class) {
            return new StdDelegatingDeserializer<>(converter, javaType, jsonDeserializer);
        }
        throw new IllegalStateException("Sub-class " + getClass().getName() + " must override 'withDelegate'");
    }

    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        if (this._delegateDeserializer != null && (this._delegateDeserializer instanceof ResolvableDeserializer)) {
            ((ResolvableDeserializer) this._delegateDeserializer).resolve(deserializationContext);
        }
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> createContextual;
        if (this._delegateDeserializer == null) {
            JavaType inputType = this._converter.getInputType(deserializationContext.getTypeFactory());
            return withDelegate(this._converter, inputType, deserializationContext.findContextualValueDeserializer(inputType, beanProperty));
        } else if (!(this._delegateDeserializer instanceof ContextualDeserializer) || (createContextual = ((ContextualDeserializer) this._delegateDeserializer).createContextual(deserializationContext, beanProperty)) == this._delegateDeserializer) {
            return this;
        } else {
            return withDelegate(this._converter, this._delegateType, createContextual);
        }
    }

    public JsonDeserializer<?> getDelegatee() {
        return this._delegateDeserializer;
    }

    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object deserialize = this._delegateDeserializer.deserialize(jsonParser, deserializationContext);
        if (deserialize == null) {
            return null;
        }
        return convertValue(deserialize);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        Object deserializeWithType = this._delegateDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        if (deserializeWithType == null) {
            return null;
        }
        return convertValue(deserializeWithType);
    }

    /* access modifiers changed from: protected */
    public T convertValue(Object obj) {
        return this._converter.convert(obj);
    }
}
