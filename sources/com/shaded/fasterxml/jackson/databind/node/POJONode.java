package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public final class POJONode extends ValueNode {
    protected final Object _value;

    public POJONode(Object obj) {
        this._value = obj;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.POJO;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    public byte[] binaryValue() throws IOException {
        if (this._value instanceof byte[]) {
            return (byte[]) this._value;
        }
        return super.binaryValue();
    }

    public String asText() {
        return this._value == null ? "null" : this._value.toString();
    }

    public boolean asBoolean(boolean z) {
        if (this._value == null || !(this._value instanceof Boolean)) {
            return z;
        }
        return ((Boolean) this._value).booleanValue();
    }

    public int asInt(int i) {
        if (this._value instanceof Number) {
            return ((Number) this._value).intValue();
        }
        return i;
    }

    public long asLong(long j) {
        if (this._value instanceof Number) {
            return ((Number) this._value).longValue();
        }
        return j;
    }

    public double asDouble(double d) {
        if (this._value instanceof Number) {
            return ((Number) this._value).doubleValue();
        }
        return d;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (this._value == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeObject(this._value);
        }
    }

    public Object getPojo() {
        return this._value;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        POJONode pOJONode = (POJONode) obj;
        if (this._value != null) {
            return this._value.equals(pOJONode._value);
        }
        if (pOJONode._value != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public String toString() {
        return String.valueOf(this._value);
    }
}
