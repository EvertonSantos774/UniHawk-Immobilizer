package com.google.appinventor.common.version;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public final class GitBuildId {
    public static final String ACRA_URI = "${acra.uri}";
    public static final String ANT_BUILD_DATE = "September 22 2022";
    public static final String GIT_BUILD_FINGERPRINT = "f6360be9c531f0c8dbfd10af151b23e315949ddd";
    public static final String GIT_BUILD_VERSION = "nb190";

    private GitBuildId() {
    }

    public static String getVersion() {
        if (GIT_BUILD_VERSION == "" || GIT_BUILD_VERSION.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
            return "none";
        }
        return GIT_BUILD_VERSION;
    }

    public static String getFingerprint() {
        return GIT_BUILD_FINGERPRINT;
    }

    public static String getDate() {
        return ANT_BUILD_DATE;
    }

    public static String getAcraUri() {
        if (ACRA_URI.equals(ACRA_URI)) {
            return "";
        }
        return ACRA_URI.trim();
    }
}
