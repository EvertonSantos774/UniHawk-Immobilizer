package org.shaded.apache.http.protocol;

public class SyncBasicHttpContext extends BasicHttpContext {
    public SyncBasicHttpContext(HttpContext parentContext) {
        super(parentContext);
    }

    public synchronized Object getAttribute(String id) {
        return super.getAttribute(id);
    }

    public synchronized void setAttribute(String id, Object obj) {
        super.setAttribute(id, obj);
    }

    public synchronized Object removeAttribute(String id) {
        return super.removeAttribute(id);
    }
}
