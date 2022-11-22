package com.google.api.client.extensions.android3.json;

import android.util.JsonReader;
import android.util.JsonWriter;
import com.google.api.client.json.JsonEncoding;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.common.base.Charsets;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;

public class AndroidJsonFactory extends JsonFactory {
    public JsonParser createJsonParser(InputStream in) {
        return createJsonParser((Reader) new InputStreamReader(in, Charsets.UTF_8));
    }

    public JsonParser createJsonParser(InputStream in, Charset charset) {
        return createJsonParser((Reader) new InputStreamReader(in, charset));
    }

    public JsonParser createJsonParser(String value) {
        return createJsonParser((Reader) new StringReader(value));
    }

    public JsonParser createJsonParser(Reader reader) {
        return new AndroidJsonParser(this, new JsonReader(reader));
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(OutputStream out, JsonEncoding enc) {
        return createJsonGenerator(new OutputStreamWriter(out, Charsets.UTF_8));
    }

    public JsonGenerator createJsonGenerator(OutputStream out, Charset enc) {
        return createJsonGenerator(new OutputStreamWriter(out, enc));
    }

    public JsonGenerator createJsonGenerator(Writer writer) {
        return new AndroidJsonGenerator(this, new JsonWriter(writer));
    }
}
