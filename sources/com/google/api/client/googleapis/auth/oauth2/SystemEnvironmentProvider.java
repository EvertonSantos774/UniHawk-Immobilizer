package com.google.api.client.googleapis.auth.oauth2;

class SystemEnvironmentProvider {
    static final SystemEnvironmentProvider INSTANCE = new SystemEnvironmentProvider();

    SystemEnvironmentProvider() {
    }

    /* access modifiers changed from: package-private */
    public String getEnv(String name) {
        return System.getenv(name);
    }

    /* access modifiers changed from: package-private */
    public boolean getEnvEquals(String name, String value) {
        return System.getenv().containsKey(name) && System.getenv(name).equals(value);
    }
}
