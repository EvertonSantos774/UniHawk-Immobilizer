package org.shaded.apache.commons.logging.impl;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public final class WeakHashtable extends Hashtable {
    private static final int MAX_CHANGES_BEFORE_PURGE = 100;
    private static final int PARTIAL_PURGE_COUNT = 10;
    private int changeCount = 0;
    private ReferenceQueue queue = new ReferenceQueue();

    public boolean containsKey(Object key) {
        return super.containsKey(new Referenced(key, (C15081) null));
    }

    public Enumeration elements() {
        purge();
        return super.elements();
    }

    public Set entrySet() {
        purge();
        Set<Map.Entry> referencedEntries = super.entrySet();
        Set unreferencedEntries = new HashSet();
        for (Map.Entry entry : referencedEntries) {
            Object key = Referenced.access$100((Referenced) entry.getKey());
            Object value = entry.getValue();
            if (key != null) {
                unreferencedEntries.add(new Entry(key, value, (C15081) null));
            }
        }
        return unreferencedEntries;
    }

    public Object get(Object key) {
        return super.get(new Referenced(key, (C15081) null));
    }

    public Enumeration keys() {
        purge();
        return new Enumeration(this, super.keys()) {
            private final WeakHashtable this$0;
            private final Enumeration val$enumer;

            public boolean hasMoreElements() {
                return this.val$enumer.hasMoreElements();
            }

            {
                this.this$0 = this$0;
                this.val$enumer = val$enumer;
            }

            public Object nextElement() {
                return Referenced.access$100((Referenced) this.val$enumer.nextElement());
            }
        };
    }

    public Set keySet() {
        purge();
        Set<Referenced> referencedKeys = super.keySet();
        Set unreferencedKeys = new HashSet();
        for (Referenced referenceKey : referencedKeys) {
            Object keyValue = Referenced.access$100(referenceKey);
            if (keyValue != null) {
                unreferencedKeys.add(keyValue);
            }
        }
        return unreferencedKeys;
    }

    public Object put(Object key, Object value) {
        if (key == null) {
            throw new NullPointerException("Null keys are not allowed");
        } else if (value == null) {
            throw new NullPointerException("Null values are not allowed");
        } else {
            int i = this.changeCount;
            this.changeCount = i + 1;
            if (i > 100) {
                purge();
                this.changeCount = 0;
            } else if (this.changeCount % 10 == 0) {
                purgeOne();
            }
            return super.put(new Referenced(key, this.queue, (C15081) null), value);
        }
    }

    public void putAll(Map t) {
        if (t != null) {
            for (Map.Entry entry : t.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Collection values() {
        purge();
        return super.values();
    }

    public Object remove(Object key) {
        int i = this.changeCount;
        this.changeCount = i + 1;
        if (i > 100) {
            purge();
            this.changeCount = 0;
        } else if (this.changeCount % 10 == 0) {
            purgeOne();
        }
        return super.remove(new Referenced(key, (C15081) null));
    }

    public boolean isEmpty() {
        purge();
        return super.isEmpty();
    }

    public int size() {
        purge();
        return super.size();
    }

    public String toString() {
        purge();
        return super.toString();
    }

    /* access modifiers changed from: protected */
    public void rehash() {
        purge();
        super.rehash();
    }

    private void purge() {
        synchronized (this.queue) {
            while (true) {
                WeakKey key = (WeakKey) this.queue.poll();
                if (key != null) {
                    super.remove(WeakKey.access$400(key));
                }
            }
        }
    }

    private void purgeOne() {
        synchronized (this.queue) {
            WeakKey key = (WeakKey) this.queue.poll();
            if (key != null) {
                super.remove(WeakKey.access$400(key));
            }
        }
    }

    private static final class Entry implements Map.Entry {
        private final Object key;
        private final Object value;

        Entry(Object x0, Object x1, C15081 x2) {
            this(x0, x1);
        }

        private Entry(Object key2, Object value2) {
            this.key = key2;
            this.value = value2;
        }

        public boolean equals(Object o) {
            if (o == null || !(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) o;
            if (getKey() != null ? getKey().equals(entry.getKey()) : entry.getKey() == null) {
                return getValue() != null ? getValue().equals(entry.getValue()) : entry.getValue() == null;
            }
        }

        public int hashCode() {
            int i = 0;
            int hashCode = getKey() == null ? 0 : getKey().hashCode();
            if (getValue() != null) {
                i = getValue().hashCode();
            }
            return hashCode ^ i;
        }

        public Object setValue(Object value2) {
            throw new UnsupportedOperationException("Entry.setValue is not supported.");
        }

        public Object getValue() {
            return this.value;
        }

        public Object getKey() {
            return this.key;
        }
    }

    private static final class Referenced {
        private final int hashCode;
        private final WeakReference reference;

        Referenced(Object x0, ReferenceQueue x1, C15081 x2) {
            this(x0, x1);
        }

        Referenced(Object x0, C15081 x1) {
            this(x0);
        }

        static Object access$100(Referenced x0) {
            return x0.getValue();
        }

        private Referenced(Object referant) {
            this.reference = new WeakReference(referant);
            this.hashCode = referant.hashCode();
        }

        private Referenced(Object key, ReferenceQueue queue) {
            this.reference = new WeakKey(key, queue, this, (C15081) null);
            this.hashCode = key.hashCode();
        }

        public int hashCode() {
            return this.hashCode;
        }

        private Object getValue() {
            return this.reference.get();
        }

        public boolean equals(Object o) {
            boolean result;
            if (!(o instanceof Referenced)) {
                return false;
            }
            Referenced otherKey = (Referenced) o;
            Object thisKeyValue = getValue();
            Object otherKeyValue = otherKey.getValue();
            if (thisKeyValue != null) {
                return thisKeyValue.equals(otherKeyValue);
            }
            if (otherKeyValue == null) {
                result = true;
            } else {
                result = false;
            }
            if (!result) {
                return result;
            }
            if (hashCode() == otherKey.hashCode()) {
                return true;
            }
            return false;
        }
    }

    private static final class WeakKey extends WeakReference {
        private final Referenced referenced;

        WeakKey(Object x0, ReferenceQueue x1, Referenced x2, C15081 x3) {
            this(x0, x1, x2);
        }

        static Referenced access$400(WeakKey x0) {
            return x0.getReferenced();
        }

        private WeakKey(Object key, ReferenceQueue queue, Referenced referenced2) {
            super(key, queue);
            this.referenced = referenced2;
        }

        private Referenced getReferenced() {
            return this.referenced;
        }
    }
}
