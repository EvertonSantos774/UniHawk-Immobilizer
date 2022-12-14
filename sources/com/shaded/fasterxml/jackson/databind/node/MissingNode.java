package com.shaded.fasterxml.jackson.databind.node;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public final class MissingNode extends ValueNode {
    private static final MissingNode instance = new MissingNode();

    private MissingNode() {
    }

    public <T extends JsonNode> T deepCopy() {
        return this;
    }

    public static MissingNode getInstance() {
        return instance;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.MISSING;
    }

    public JsonToken asToken() {
        return JsonToken.NOT_AVAILABLE;
    }

    public String asText() {
        return "";
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNull();
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        jsonGenerator.writeNull();
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public String toString() {
        return "";
    }
}
