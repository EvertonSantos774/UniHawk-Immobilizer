package com.shaded.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;

public class ReadableObjectId {

    /* renamed from: id */
    public final Object f372id;
    public Object item;

    public ReadableObjectId(Object obj) {
        this.f372id = obj;
    }

    public void bindItem(Object obj) throws IOException {
        if (this.item != null) {
            throw new IllegalStateException("Already had POJO for id (" + this.f372id.getClass().getName() + ") [" + this.f372id + "]");
        }
        this.item = obj;
    }
}
