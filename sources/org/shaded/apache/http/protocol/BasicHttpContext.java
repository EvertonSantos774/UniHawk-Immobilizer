package org.shaded.apache.http.protocol;

import java.util.HashMap;
import java.util.Map;

public class BasicHttpContext implements HttpContext {
    private Map map;
    private final HttpContext parentContext;

    public BasicHttpContext() {
        this((HttpContext) null);
    }

    public BasicHttpContext(HttpContext parentContext2) {
        this.map = null;
        this.parentContext = parentContext2;
    }

    public Object getAttribute(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id may not be null");
        }
        Object obj = null;
        if (this.map != null) {
            obj = this.map.get(id);
        }
        if (obj != null || this.parentContext == null) {
            return obj;
        }
        return this.parentContext.getAttribute(id);
    }

    public void setAttribute(String id, Object obj) {
        if (id == null) {
            throw new IllegalArgumentException("Id may not be null");
        }
        if (this.map == null) {
            this.map = new HashMap();
        }
        this.map.put(id, obj);
    }

    public Object removeAttribute(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id may not be null");
        } else if (this.map != null) {
            return this.map.remove(id);
        } else {
            return null;
        }
    }
}
