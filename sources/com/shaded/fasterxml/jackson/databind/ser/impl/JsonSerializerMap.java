package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.ser.SerializerCache;
import java.util.Map;

public class JsonSerializerMap {
    private final Bucket[] _buckets;
    private final int _size;

    public JsonSerializerMap(Map<SerializerCache.TypeKey, JsonSerializer<Object>> map) {
        int findSize = findSize(map.size());
        this._size = findSize;
        int i = findSize - 1;
        Bucket[] bucketArr = new Bucket[findSize];
        for (Map.Entry next : map.entrySet()) {
            SerializerCache.TypeKey typeKey = (SerializerCache.TypeKey) next.getKey();
            int hashCode = typeKey.hashCode() & i;
            bucketArr[hashCode] = new Bucket(bucketArr[hashCode], typeKey, (JsonSerializer) next.getValue());
        }
        this._buckets = bucketArr;
    }

    private static final int findSize(int i) {
        int i2 = 8;
        while (i2 < (i <= 64 ? i + i : (i >> 2) + i)) {
            i2 += i2;
        }
        return i2;
    }

    public int size() {
        return this._size;
    }

    public JsonSerializer<Object> find(SerializerCache.TypeKey typeKey) {
        Bucket bucket = this._buckets[typeKey.hashCode() & (this._buckets.length - 1)];
        if (bucket == null) {
            return null;
        }
        if (typeKey.equals(bucket.key)) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return null;
            }
        } while (!typeKey.equals(bucket.key));
        return bucket.value;
    }

    private static final class Bucket {
        public final SerializerCache.TypeKey key;
        public final Bucket next;
        public final JsonSerializer<Object> value;

        public Bucket(Bucket bucket, SerializerCache.TypeKey typeKey, JsonSerializer<Object> jsonSerializer) {
            this.next = bucket;
            this.key = typeKey;
            this.value = jsonSerializer;
        }
    }
}
