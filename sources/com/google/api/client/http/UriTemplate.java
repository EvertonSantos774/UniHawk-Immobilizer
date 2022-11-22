package com.google.api.client.http;

import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import com.google.common.base.Splitter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

public class UriTemplate {
    private static final String COMPOSITE_NON_EXPLODE_JOINER = ",";
    /* access modifiers changed from: private */
    public static final Map<Character, CompositeOutput> COMPOSITE_PREFIXES = new HashMap();

    static {
        CompositeOutput.values();
    }

    private enum CompositeOutput {
        PLUS('+', "", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, true),
        HASH('#', "#", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, true),
        DOT('.', ".", ".", false, false),
        FORWARD_SLASH('/', "/", "/", false, false),
        SEMI_COLON(';', ";", ";", true, false),
        QUERY('?', "?", "&", true, false),
        AMP('&', "&", "&", true, false),
        SIMPLE((String) null, "", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, false);
        
        private final String explodeJoiner;
        private final String outputPrefix;
        private final Character propertyPrefix;
        private final boolean requiresVarAssignment;
        private final boolean reservedExpansion;

        private CompositeOutput(Character propertyPrefix2, String outputPrefix2, String explodeJoiner2, boolean requiresVarAssignment2, boolean reservedExpansion2) {
            this.propertyPrefix = propertyPrefix2;
            this.outputPrefix = (String) Preconditions.checkNotNull(outputPrefix2);
            this.explodeJoiner = (String) Preconditions.checkNotNull(explodeJoiner2);
            this.requiresVarAssignment = requiresVarAssignment2;
            this.reservedExpansion = reservedExpansion2;
            if (propertyPrefix2 != null) {
                UriTemplate.COMPOSITE_PREFIXES.put(propertyPrefix2, this);
            }
        }

        /* access modifiers changed from: package-private */
        public String getOutputPrefix() {
            return this.outputPrefix;
        }

        /* access modifiers changed from: package-private */
        public String getExplodeJoiner() {
            return this.explodeJoiner;
        }

        /* access modifiers changed from: package-private */
        public boolean requiresVarAssignment() {
            return this.requiresVarAssignment;
        }

        /* access modifiers changed from: package-private */
        public int getVarNameStartIndex() {
            return this.propertyPrefix == null ? 0 : 1;
        }

        /* access modifiers changed from: private */
        public String getEncodedValue(String value) {
            if (this.reservedExpansion) {
                return CharEscapers.escapeUriPathWithoutReserved(value);
            }
            return CharEscapers.escapeUriConformant(value);
        }
    }

    static CompositeOutput getCompositeOutput(String propertyName) {
        CompositeOutput compositeOutput = COMPOSITE_PREFIXES.get(Character.valueOf(propertyName.charAt(0)));
        return compositeOutput == null ? CompositeOutput.SIMPLE : compositeOutput;
    }

    private static Map<String, Object> getMap(Object obj) {
        Map<String, Object> map = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : Data.mapOf(obj).entrySet()) {
            Object value = entry.getValue();
            if (value != null && !Data.isNull(value)) {
                map.put(entry.getKey(), value);
            }
        }
        return map;
    }

    public static String expand(String baseUrl, String uriTemplate, Object parameters, boolean addUnusedParamsAsQueryParams) {
        String pathUri;
        if (uriTemplate.startsWith("/")) {
            GenericUrl url = new GenericUrl(baseUrl);
            url.setRawPath((String) null);
            pathUri = url.build() + uriTemplate;
        } else if (uriTemplate.startsWith("http://") || uriTemplate.startsWith("https://")) {
            pathUri = uriTemplate;
        } else {
            pathUri = baseUrl + uriTemplate;
        }
        return expand(pathUri, parameters, addUnusedParamsAsQueryParams);
    }

    public static String expand(String pathUri, Object parameters, boolean addUnusedParamsAsQueryParams) {
        String value;
        Map<String, Object> variableMap = getMap(parameters);
        StringBuilder pathBuf = new StringBuilder();
        int cur = 0;
        int length = pathUri.length();
        while (true) {
            if (cur >= length) {
                break;
            }
            int next = pathUri.indexOf(123, cur);
            if (next != -1) {
                pathBuf.append(pathUri.substring(cur, next));
                int close = pathUri.indexOf(125, next + 2);
                cur = close + 1;
                String templates = pathUri.substring(next + 1, close);
                CompositeOutput compositeOutput = getCompositeOutput(templates);
                ListIterator<String> templateIterator = Splitter.m62on(',').splitToList(templates).listIterator();
                boolean isFirstParameter = true;
                while (templateIterator.hasNext()) {
                    String template = templateIterator.next();
                    boolean containsExplodeModifier = template.endsWith("*");
                    int varNameStartIndex = templateIterator.nextIndex() == 1 ? compositeOutput.getVarNameStartIndex() : 0;
                    int varNameEndIndex = template.length();
                    if (containsExplodeModifier) {
                        varNameEndIndex--;
                    }
                    String varName = template.substring(varNameStartIndex, varNameEndIndex);
                    Object value2 = variableMap.remove(varName);
                    if (value2 != null) {
                        if (!isFirstParameter) {
                            pathBuf.append(compositeOutput.getExplodeJoiner());
                        } else {
                            pathBuf.append(compositeOutput.getOutputPrefix());
                            isFirstParameter = false;
                        }
                        if (value2 instanceof Iterator) {
                            value = getListPropertyValue(varName, (Iterator) value2, containsExplodeModifier, compositeOutput);
                        } else if ((value2 instanceof Iterable) || value2.getClass().isArray()) {
                            value = getListPropertyValue(varName, Types.iterableOf(value2).iterator(), containsExplodeModifier, compositeOutput);
                        } else if (value2.getClass().isEnum()) {
                            String name = FieldInfo.m36of((Enum<?>) (Enum) value2).getName();
                            if (name == null) {
                                name = value2.toString();
                            }
                            value = getSimpleValue(varName, name, compositeOutput);
                        } else if (!Data.isValueOfPrimitiveType(value2)) {
                            value = getMapPropertyValue(varName, getMap(value2), containsExplodeModifier, compositeOutput);
                        } else {
                            value = getSimpleValue(varName, value2.toString(), compositeOutput);
                        }
                        pathBuf.append(value);
                    }
                }
            } else if (cur == 0 && !addUnusedParamsAsQueryParams) {
                return pathUri;
            } else {
                pathBuf.append(pathUri.substring(cur));
            }
        }
        if (addUnusedParamsAsQueryParams) {
            GenericUrl.addQueryParams(variableMap.entrySet(), pathBuf, false);
        }
        return pathBuf.toString();
    }

    private static String getSimpleValue(String name, String value, CompositeOutput compositeOutput) {
        if (!compositeOutput.requiresVarAssignment()) {
            return compositeOutput.getEncodedValue(value);
        }
        return String.format("%s=%s", new Object[]{name, compositeOutput.getEncodedValue(value)});
    }

    private static String getListPropertyValue(String varName, Iterator<?> iterator, boolean containsExplodeModifier, CompositeOutput compositeOutput) {
        String joiner;
        if (!iterator.hasNext()) {
            return "";
        }
        StringBuilder retBuf = new StringBuilder();
        if (containsExplodeModifier) {
            joiner = compositeOutput.getExplodeJoiner();
        } else {
            joiner = COMPOSITE_NON_EXPLODE_JOINER;
            if (compositeOutput.requiresVarAssignment()) {
                retBuf.append(CharEscapers.escapeUriPath(varName));
                retBuf.append("=");
            }
        }
        while (iterator.hasNext()) {
            if (containsExplodeModifier && compositeOutput.requiresVarAssignment()) {
                retBuf.append(CharEscapers.escapeUriPath(varName));
                retBuf.append("=");
            }
            retBuf.append(compositeOutput.getEncodedValue(iterator.next().toString()));
            if (iterator.hasNext()) {
                retBuf.append(joiner);
            }
        }
        return retBuf.toString();
    }

    private static String getMapPropertyValue(String varName, Map<String, Object> map, boolean containsExplodeModifier, CompositeOutput compositeOutput) {
        String joiner;
        String mapElementsJoiner;
        if (map.isEmpty()) {
            return "";
        }
        StringBuilder retBuf = new StringBuilder();
        if (containsExplodeModifier) {
            joiner = compositeOutput.getExplodeJoiner();
            mapElementsJoiner = "=";
        } else {
            joiner = COMPOSITE_NON_EXPLODE_JOINER;
            mapElementsJoiner = COMPOSITE_NON_EXPLODE_JOINER;
            if (compositeOutput.requiresVarAssignment()) {
                retBuf.append(CharEscapers.escapeUriPath(varName));
                retBuf.append("=");
            }
        }
        Iterator<Map.Entry<String, Object>> mapIterator = map.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry<String, Object> entry = mapIterator.next();
            String encodedKey = compositeOutput.getEncodedValue(entry.getKey());
            String encodedValue = compositeOutput.getEncodedValue(entry.getValue().toString());
            retBuf.append(encodedKey);
            retBuf.append(mapElementsJoiner);
            retBuf.append(encodedValue);
            if (mapIterator.hasNext()) {
                retBuf.append(joiner);
            }
        }
        return retBuf.toString();
    }
}
