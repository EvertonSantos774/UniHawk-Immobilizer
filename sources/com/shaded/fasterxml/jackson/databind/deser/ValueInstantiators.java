package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;

public interface ValueInstantiators {
    ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription, ValueInstantiator valueInstantiator);

    public static class Base implements ValueInstantiators {
        public ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription, ValueInstantiator valueInstantiator) {
            return valueInstantiator;
        }
    }
}
