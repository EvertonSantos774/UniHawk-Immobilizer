package org.shaded.apache.http.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.conn.routing.HttpRoute;

@Immutable
public class BasicPoolEntryRef extends WeakReference<BasicPoolEntry> {
    private final HttpRoute route;

    public BasicPoolEntryRef(BasicPoolEntry entry, ReferenceQueue<Object> queue) {
        super(entry, queue);
        if (entry == null) {
            throw new IllegalArgumentException("Pool entry must not be null.");
        }
        this.route = entry.getPlannedRoute();
    }

    public final HttpRoute getRoute() {
        return this.route;
    }
}
