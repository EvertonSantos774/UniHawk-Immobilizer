package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractMemoryDataStore<V extends Serializable> extends AbstractDataStore<V> {
    protected HashMap<String, byte[]> keyValueMap = Maps.newHashMap();
    private final Lock lock = new ReentrantLock();

    protected AbstractMemoryDataStore(DataStoreFactory dataStoreFactory, String id) {
        super(dataStoreFactory, id);
    }

    public final Set<String> keySet() throws IOException {
        this.lock.lock();
        try {
            return Collections.unmodifiableSet(this.keyValueMap.keySet());
        } finally {
            this.lock.unlock();
        }
    }

    public final Collection<V> values() throws IOException {
        this.lock.lock();
        try {
            List<V> result = Lists.newArrayList();
            for (byte[] bytes : this.keyValueMap.values()) {
                result.add(IOUtils.deserialize(bytes));
            }
            return Collections.unmodifiableList(result);
        } finally {
            this.lock.unlock();
        }
    }

    public final V get(String key) throws IOException {
        if (key == null) {
            return null;
        }
        this.lock.lock();
        try {
            return IOUtils.deserialize(this.keyValueMap.get(key));
        } finally {
            this.lock.unlock();
        }
    }

    public final DataStore<V> set(String key, V value) throws IOException {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        this.lock.lock();
        try {
            this.keyValueMap.put(key, IOUtils.serialize(value));
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public DataStore<V> delete(String key) throws IOException {
        if (key != null) {
            this.lock.lock();
            try {
                this.keyValueMap.remove(key);
                save();
            } finally {
                this.lock.unlock();
            }
        }
        return this;
    }

    public final DataStore<V> clear() throws IOException {
        this.lock.lock();
        try {
            this.keyValueMap.clear();
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean containsKey(String key) throws IOException {
        if (key == null) {
            return false;
        }
        this.lock.lock();
        try {
            return this.keyValueMap.containsKey(key);
        } finally {
            this.lock.unlock();
        }
    }

    public boolean containsValue(V value) throws IOException {
        if (value == null) {
            return false;
        }
        this.lock.lock();
        try {
            byte[] serialized = IOUtils.serialize(value);
            for (byte[] bytes : this.keyValueMap.values()) {
                if (Arrays.equals(serialized, bytes)) {
                    return true;
                }
            }
            this.lock.unlock();
            return false;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isEmpty() throws IOException {
        this.lock.lock();
        try {
            return this.keyValueMap.isEmpty();
        } finally {
            this.lock.unlock();
        }
    }

    public int size() throws IOException {
        this.lock.lock();
        try {
            return this.keyValueMap.size();
        } finally {
            this.lock.unlock();
        }
    }

    public void save() throws IOException {
    }

    public String toString() {
        return DataStoreUtils.toString(this);
    }
}
