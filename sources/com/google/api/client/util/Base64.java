package com.google.api.client.util;

import com.google.common.p004io.BaseEncoding;

public class Base64 {
    public static byte[] encodeBase64(byte[] binaryData) {
        return StringUtils.getBytesUtf8(encodeBase64String(binaryData));
    }

    public static String encodeBase64String(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        return BaseEncoding.base64().encode(binaryData);
    }

    public static byte[] encodeBase64URLSafe(byte[] binaryData) {
        return StringUtils.getBytesUtf8(encodeBase64URLSafeString(binaryData));
    }

    public static String encodeBase64URLSafeString(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        return BaseEncoding.base64Url().omitPadding().encode(binaryData);
    }

    public static byte[] decodeBase64(byte[] base64Data) {
        return decodeBase64(StringUtils.newStringUtf8(base64Data));
    }

    public static byte[] decodeBase64(String base64String) {
        if (base64String == null) {
            return null;
        }
        try {
            return BaseEncoding.base64().decode(base64String);
        } catch (IllegalArgumentException e) {
            if (e.getCause() instanceof BaseEncoding.DecodingException) {
                return BaseEncoding.base64Url().decode(base64String.trim());
            }
            throw e;
        }
    }

    private Base64() {
    }
}
