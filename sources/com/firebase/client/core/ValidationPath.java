package com.firebase.client.core;

import com.firebase.client.FirebaseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ValidationPath {
    public static final int MAX_PATH_DEPTH = 32;
    public static final int MAX_PATH_LENGTH_BYTES = 768;
    private int byteLength = 0;
    private final List<String> parts = new ArrayList();

    private ValidationPath(Path path) throws FirebaseException {
        Iterator i$ = path.iterator();
        while (i$.hasNext()) {
            this.parts.add(i$.next().asString());
        }
        this.byteLength = Math.max(1, this.parts.size());
        for (int i = 0; i < this.parts.size(); i++) {
            this.byteLength = utf8Bytes(this.parts.get(i)) + this.byteLength;
        }
        checkValid();
    }

    public static void validateWithObject(Path path, Object value) throws FirebaseException {
        new ValidationPath(path).withObject(value);
    }

    private void withObject(Object value) throws FirebaseException {
        if (value instanceof Map) {
            Map<String, Object> mapValue = (Map) value;
            for (String key : mapValue.keySet()) {
                if (!key.startsWith(".")) {
                    push(key);
                    withObject(mapValue.get(key));
                    pop();
                }
            }
        } else if (value instanceof List) {
            List listValue = (List) value;
            for (int i = 0; i < listValue.size(); i++) {
                push(Integer.toString(i));
                withObject(listValue.get(i));
                pop();
            }
        }
    }

    private void push(String child) throws FirebaseException {
        if (this.parts.size() > 0) {
            this.byteLength++;
        }
        this.parts.add(child);
        this.byteLength += utf8Bytes(child);
        checkValid();
    }

    private String pop() {
        String last = this.parts.remove(this.parts.size() - 1);
        this.byteLength -= utf8Bytes(last);
        if (this.parts.size() > 0) {
            this.byteLength--;
        }
        return last;
    }

    private void checkValid() throws FirebaseException {
        if (this.byteLength > 768) {
            throw new FirebaseException("Data has a key path longer than 768 bytes (" + this.byteLength + ").");
        } else if (this.parts.size() > 32) {
            throw new FirebaseException("Path specified exceeds the maximum depth that can be written (32) or object contains a cycle " + toErrorString());
        }
    }

    private String toErrorString() {
        if (this.parts.size() == 0) {
            return "";
        }
        return "in path '" + joinStringList("/", this.parts) + "'";
    }

    private static String joinStringList(String delimeter, List<String> parts2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts2.size(); i++) {
            if (i > 0) {
                sb.append(delimeter);
            }
            sb.append(parts2.get(i));
        }
        return sb.toString();
    }

    private static int utf8Bytes(CharSequence sequence) {
        int count = 0;
        int i = 0;
        int len = sequence.length();
        while (i < len) {
            char ch = sequence.charAt(i);
            if (ch <= 127) {
                count++;
            } else if (ch <= 2047) {
                count += 2;
            } else if (Character.isHighSurrogate(ch)) {
                count += 4;
                i++;
            } else {
                count += 3;
            }
            i++;
        }
        return count;
    }
}
