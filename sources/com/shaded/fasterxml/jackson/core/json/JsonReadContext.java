package com.shaded.fasterxml.jackson.core.json;

import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonStreamContext;
import com.shaded.fasterxml.jackson.core.p005io.CharTypes;

public final class JsonReadContext extends JsonStreamContext {
    protected JsonReadContext _child = null;
    protected int _columnNr;
    protected String _currentName;
    protected int _lineNr;
    protected final JsonReadContext _parent;

    public JsonReadContext(JsonReadContext jsonReadContext, int i, int i2, int i3) {
        this._type = i;
        this._parent = jsonReadContext;
        this._lineNr = i2;
        this._columnNr = i3;
        this._index = -1;
    }

    /* access modifiers changed from: protected */
    public void reset(int i, int i2, int i3) {
        this._type = i;
        this._index = -1;
        this._lineNr = i2;
        this._columnNr = i3;
        this._currentName = null;
    }

    public static JsonReadContext createRootContext(int i, int i2) {
        return new JsonReadContext((JsonReadContext) null, 0, i, i2);
    }

    public static JsonReadContext createRootContext() {
        return new JsonReadContext((JsonReadContext) null, 0, 1, 0);
    }

    public JsonReadContext createChildArrayContext(int i, int i2) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            JsonReadContext jsonReadContext2 = new JsonReadContext(this, 1, i, i2);
            this._child = jsonReadContext2;
            return jsonReadContext2;
        }
        jsonReadContext.reset(1, i, i2);
        return jsonReadContext;
    }

    public JsonReadContext createChildObjectContext(int i, int i2) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            JsonReadContext jsonReadContext2 = new JsonReadContext(this, 2, i, i2);
            this._child = jsonReadContext2;
            return jsonReadContext2;
        }
        jsonReadContext.reset(2, i, i2);
        return jsonReadContext;
    }

    public String getCurrentName() {
        return this._currentName;
    }

    public JsonReadContext getParent() {
        return this._parent;
    }

    public JsonLocation getStartLocation(Object obj) {
        return new JsonLocation(obj, -1, this._lineNr, this._columnNr);
    }

    public boolean expectComma() {
        int i = this._index + 1;
        this._index = i;
        return this._type != 0 && i > 0;
    }

    public void setCurrentName(String str) {
        this._currentName = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        switch (this._type) {
            case 0:
                sb.append("/");
                break;
            case 1:
                sb.append('[');
                sb.append(getCurrentIndex());
                sb.append(']');
                break;
            case 2:
                sb.append('{');
                if (this._currentName != null) {
                    sb.append('\"');
                    CharTypes.appendQuoted(sb, this._currentName);
                    sb.append('\"');
                } else {
                    sb.append('?');
                }
                sb.append('}');
                break;
        }
        return sb.toString();
    }
}
