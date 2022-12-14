package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class ArrayMap<K, V> extends AbstractMap<K, V> implements Cloneable {
    private Object[] data;
    int size;

    public static <K, V> ArrayMap<K, V> create() {
        return new ArrayMap<>();
    }

    public static <K, V> ArrayMap<K, V> create(int initialCapacity) {
        ArrayMap<K, V> result = create();
        result.ensureCapacity(initialCapacity);
        return result;
    }

    /* renamed from: of */
    public static <K, V> ArrayMap<K, V> m33of(Object... keyValuePairs) {
        ArrayMap<K, V> result = create(1);
        int length = keyValuePairs.length;
        if (1 == length % 2) {
            throw new IllegalArgumentException("missing value for last key: " + keyValuePairs[length - 1]);
        }
        result.size = keyValuePairs.length / 2;
        Object[] data2 = new Object[length];
        result.data = data2;
        System.arraycopy(keyValuePairs, 0, data2, 0, length);
        return result;
    }

    public final int size() {
        return this.size;
    }

    public final K getKey(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return this.data[index << 1];
    }

    public final V getValue(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return valueAtDataIndex((index << 1) + 1);
    }

    public final V set(int index, K key, V value) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int minSize = index + 1;
        ensureCapacity(minSize);
        int dataIndex = index << 1;
        V result = valueAtDataIndex(dataIndex + 1);
        setData(dataIndex, key, value);
        if (minSize > this.size) {
            this.size = minSize;
        }
        return result;
    }

    public final V set(int index, V value) {
        int size2 = this.size;
        if (index < 0 || index >= size2) {
            throw new IndexOutOfBoundsException();
        }
        int valueDataIndex = (index << 1) + 1;
        V result = valueAtDataIndex(valueDataIndex);
        this.data[valueDataIndex] = value;
        return result;
    }

    public final void add(K key, V value) {
        set(this.size, key, value);
    }

    public final V remove(int index) {
        return removeFromDataIndexOfKey(index << 1);
    }

    public final boolean containsKey(Object key) {
        return -2 != getDataIndexOfKey(key);
    }

    public final int getIndexOfKey(K key) {
        return getDataIndexOfKey(key) >> 1;
    }

    public final V get(Object key) {
        return valueAtDataIndex(getDataIndexOfKey(key) + 1);
    }

    public final V put(K key, V value) {
        int index = getIndexOfKey(key);
        if (index == -1) {
            index = this.size;
        }
        return set(index, key, value);
    }

    public final V remove(Object key) {
        return removeFromDataIndexOfKey(getDataIndexOfKey(key));
    }

    public final void trim() {
        setDataCapacity(this.size << 1);
    }

    public final void ensureCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] data2 = this.data;
        int minDataCapacity = minCapacity << 1;
        int oldDataCapacity = data2 == null ? 0 : data2.length;
        if (minDataCapacity > oldDataCapacity) {
            int newDataCapacity = ((oldDataCapacity / 2) * 3) + 1;
            if (newDataCapacity % 2 != 0) {
                newDataCapacity++;
            }
            if (newDataCapacity < minDataCapacity) {
                newDataCapacity = minDataCapacity;
            }
            setDataCapacity(newDataCapacity);
        }
    }

    private void setDataCapacity(int newDataCapacity) {
        if (newDataCapacity == 0) {
            this.data = null;
            return;
        }
        int size2 = this.size;
        Object[] oldData = this.data;
        if (size2 == 0 || newDataCapacity != oldData.length) {
            Object[] newData = new Object[newDataCapacity];
            this.data = newData;
            if (size2 != 0) {
                System.arraycopy(oldData, 0, newData, 0, size2 << 1);
            }
        }
    }

    private void setData(int dataIndexOfKey, K key, V value) {
        Object[] data2 = this.data;
        data2[dataIndexOfKey] = key;
        data2[dataIndexOfKey + 1] = value;
    }

    private V valueAtDataIndex(int dataIndex) {
        if (dataIndex < 0) {
            return null;
        }
        return this.data[dataIndex];
    }

    private int getDataIndexOfKey(Object key) {
        int dataSize = this.size << 1;
        Object[] data2 = this.data;
        for (int i = 0; i < dataSize; i += 2) {
            Object k = data2[i];
            if (key == null) {
                if (k == null) {
                    return i;
                }
            } else if (key.equals(k)) {
                return i;
            }
        }
        return -2;
    }

    private V removeFromDataIndexOfKey(int dataIndexOfKey) {
        int dataSize = this.size << 1;
        if (dataIndexOfKey < 0 || dataIndexOfKey >= dataSize) {
            return null;
        }
        V valueAtDataIndex = valueAtDataIndex(dataIndexOfKey + 1);
        Object[] data2 = this.data;
        int moved = (dataSize - dataIndexOfKey) - 2;
        if (moved != 0) {
            System.arraycopy(data2, dataIndexOfKey + 2, data2, dataIndexOfKey, moved);
        }
        this.size--;
        setData(dataSize - 2, (Object) null, (Object) null);
        return valueAtDataIndex;
    }

    public void clear() {
        this.size = 0;
        this.data = null;
    }

    public final boolean containsValue(Object value) {
        int dataSize = this.size << 1;
        Object[] data2 = this.data;
        int i = 1;
        while (i < dataSize) {
            Object v = data2[i];
            if (value == null) {
                if (v != null) {
                    i += 2;
                }
            } else if (!value.equals(v)) {
                i += 2;
            }
            return true;
        }
        return false;
    }

    public final Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    public ArrayMap<K, V> clone() {
        try {
            ArrayMap<K, V> result = (ArrayMap) super.clone();
            Object[] data2 = this.data;
            if (data2 == null) {
                return result;
            }
            int length = data2.length;
            Object[] resultData = new Object[length];
            result.data = resultData;
            System.arraycopy(data2, 0, resultData, 0, length);
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        public int size() {
            return ArrayMap.this.size;
        }
    }

    final class EntryIterator implements Iterator<Map.Entry<K, V>> {
        private int nextIndex;
        private boolean removed;

        EntryIterator() {
        }

        public boolean hasNext() {
            return this.nextIndex < ArrayMap.this.size;
        }

        public Map.Entry<K, V> next() {
            int index = this.nextIndex;
            if (index == ArrayMap.this.size) {
                throw new NoSuchElementException();
            }
            this.nextIndex++;
            this.removed = false;
            return new Entry(index);
        }

        public void remove() {
            int index = this.nextIndex - 1;
            if (this.removed || index < 0) {
                throw new IllegalArgumentException();
            }
            ArrayMap.this.remove(index);
            this.nextIndex--;
            this.removed = true;
        }
    }

    final class Entry implements Map.Entry<K, V> {
        private int index;

        Entry(int index2) {
            this.index = index2;
        }

        public K getKey() {
            return ArrayMap.this.getKey(this.index);
        }

        public V getValue() {
            return ArrayMap.this.getValue(this.index);
        }

        public V setValue(V value) {
            return ArrayMap.this.set(this.index, value);
        }

        public int hashCode() {
            int i = 0;
            K key = getKey();
            V value = getValue();
            int hashCode = key != null ? key.hashCode() : 0;
            if (value != null) {
                i = value.hashCode();
            }
            return i ^ hashCode;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> other = (Map.Entry) obj;
            if (!Objects.equal(getKey(), other.getKey()) || !Objects.equal(getValue(), other.getValue())) {
                return false;
            }
            return true;
        }
    }
}
