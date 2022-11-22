package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.reflect.Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
public final class TypeResolver {
    private final TypeTable typeTable;

    public TypeResolver() {
        this.typeTable = new TypeTable();
    }

    private TypeResolver(TypeTable typeTable2) {
        this.typeTable = typeTable2;
    }

    static TypeResolver covariantly(Type contextType) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(contextType));
    }

    static TypeResolver invariantly(Type contextType) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(WildcardCapturer.INSTANCE.capture(contextType)));
    }

    public TypeResolver where(Type formal, Type actual) {
        Map<TypeVariableKey, Type> mappings = Maps.newHashMap();
        populateTypeMappings(mappings, (Type) Preconditions.checkNotNull(formal), (Type) Preconditions.checkNotNull(actual));
        return where(mappings);
    }

    /* access modifiers changed from: package-private */
    public TypeResolver where(Map<TypeVariableKey, ? extends Type> mappings) {
        return new TypeResolver(this.typeTable.where(mappings));
    }

    /* access modifiers changed from: private */
    public static void populateTypeMappings(final Map<TypeVariableKey, Type> mappings, Type from, final Type to) {
        if (!from.equals(to)) {
            new TypeVisitor() {
                /* access modifiers changed from: package-private */
                public void visitTypeVariable(TypeVariable<?> typeVariable) {
                    mappings.put(new TypeVariableKey(typeVariable), to);
                }

                /* access modifiers changed from: package-private */
                public void visitWildcardType(WildcardType fromWildcardType) {
                    if (to instanceof WildcardType) {
                        WildcardType toWildcardType = (WildcardType) to;
                        Type[] fromUpperBounds = fromWildcardType.getUpperBounds();
                        Type[] toUpperBounds = toWildcardType.getUpperBounds();
                        Type[] fromLowerBounds = fromWildcardType.getLowerBounds();
                        Type[] toLowerBounds = toWildcardType.getLowerBounds();
                        Preconditions.checkArgument(fromUpperBounds.length == toUpperBounds.length && fromLowerBounds.length == toLowerBounds.length, "Incompatible type: %s vs. %s", (Object) fromWildcardType, (Object) to);
                        for (int i = 0; i < fromUpperBounds.length; i++) {
                            TypeResolver.populateTypeMappings(mappings, fromUpperBounds[i], toUpperBounds[i]);
                        }
                        for (int i2 = 0; i2 < fromLowerBounds.length; i2++) {
                            TypeResolver.populateTypeMappings(mappings, fromLowerBounds[i2], toLowerBounds[i2]);
                        }
                    }
                }

                /* access modifiers changed from: package-private */
                public void visitParameterizedType(ParameterizedType fromParameterizedType) {
                    if (!(to instanceof WildcardType)) {
                        ParameterizedType toParameterizedType = (ParameterizedType) TypeResolver.expectArgument(ParameterizedType.class, to);
                        if (!(fromParameterizedType.getOwnerType() == null || toParameterizedType.getOwnerType() == null)) {
                            TypeResolver.populateTypeMappings(mappings, fromParameterizedType.getOwnerType(), toParameterizedType.getOwnerType());
                        }
                        Preconditions.checkArgument(fromParameterizedType.getRawType().equals(toParameterizedType.getRawType()), "Inconsistent raw type: %s vs. %s", (Object) fromParameterizedType, (Object) to);
                        Type[] fromArgs = fromParameterizedType.getActualTypeArguments();
                        Type[] toArgs = toParameterizedType.getActualTypeArguments();
                        Preconditions.checkArgument(fromArgs.length == toArgs.length, "%s not compatible with %s", (Object) fromParameterizedType, (Object) toParameterizedType);
                        for (int i = 0; i < fromArgs.length; i++) {
                            TypeResolver.populateTypeMappings(mappings, fromArgs[i], toArgs[i]);
                        }
                    }
                }

                /* access modifiers changed from: package-private */
                public void visitGenericArrayType(GenericArrayType fromArrayType) {
                    if (!(to instanceof WildcardType)) {
                        Type componentType = Types.getComponentType(to);
                        Preconditions.checkArgument(componentType != null, "%s is not an array type.", (Object) to);
                        TypeResolver.populateTypeMappings(mappings, fromArrayType.getGenericComponentType(), componentType);
                    }
                }

                /* access modifiers changed from: package-private */
                public void visitClass(Class<?> fromClass) {
                    if (!(to instanceof WildcardType)) {
                        throw new IllegalArgumentException("No type mapping from " + fromClass + " to " + to);
                    }
                }
            }.visit(from);
        }
    }

    public Type resolveType(Type type) {
        Preconditions.checkNotNull(type);
        if (type instanceof TypeVariable) {
            return this.typeTable.resolve((TypeVariable) type);
        }
        if (type instanceof ParameterizedType) {
            return resolveParameterizedType((ParameterizedType) type);
        }
        if (type instanceof GenericArrayType) {
            return resolveGenericArrayType((GenericArrayType) type);
        }
        if (type instanceof WildcardType) {
            return resolveWildcardType((WildcardType) type);
        }
        return type;
    }

    /* access modifiers changed from: package-private */
    public Type[] resolveTypesInPlace(Type[] types) {
        for (int i = 0; i < types.length; i++) {
            types[i] = resolveType(types[i]);
        }
        return types;
    }

    /* access modifiers changed from: private */
    public Type[] resolveTypes(Type[] types) {
        Type[] result = new Type[types.length];
        for (int i = 0; i < types.length; i++) {
            result[i] = resolveType(types[i]);
        }
        return result;
    }

    private WildcardType resolveWildcardType(WildcardType type) {
        return new Types.WildcardTypeImpl(resolveTypes(type.getLowerBounds()), resolveTypes(type.getUpperBounds()));
    }

    private Type resolveGenericArrayType(GenericArrayType type) {
        return Types.newArrayType(resolveType(type.getGenericComponentType()));
    }

    private ParameterizedType resolveParameterizedType(ParameterizedType type) {
        Type owner = type.getOwnerType();
        return Types.newParameterizedTypeWithOwner(owner == null ? null : resolveType(owner), (Class) resolveType(type.getRawType()), resolveTypes(type.getActualTypeArguments()));
    }

    /* access modifiers changed from: private */
    public static <T> T expectArgument(Class<T> type, Object arg) {
        try {
            return type.cast(arg);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(arg + " is not a " + type.getSimpleName());
        }
    }

    private static class TypeTable {
        private final ImmutableMap<TypeVariableKey, Type> map;

        TypeTable() {
            this.map = ImmutableMap.m99of();
        }

        private TypeTable(ImmutableMap<TypeVariableKey, Type> map2) {
            this.map = map2;
        }

        /* access modifiers changed from: package-private */
        public final TypeTable where(Map<TypeVariableKey, ? extends Type> mappings) {
            ImmutableMap.Builder<TypeVariableKey, Type> builder = ImmutableMap.builder();
            builder.putAll((Map<? extends TypeVariableKey, ? extends Type>) this.map);
            for (Map.Entry<TypeVariableKey, ? extends Type> mapping : mappings.entrySet()) {
                TypeVariableKey variable = mapping.getKey();
                Type type = (Type) mapping.getValue();
                Preconditions.checkArgument(!variable.equalsType(type), "Type variable %s bound to itself", (Object) variable);
                builder.put(variable, type);
            }
            return new TypeTable(builder.build());
        }

        /* access modifiers changed from: package-private */
        public final Type resolve(final TypeVariable<?> var) {
            return resolveInternal(var, new TypeTable() {
                public Type resolveInternal(TypeVariable<?> intermediateVar, TypeTable forDependent) {
                    return intermediateVar.getGenericDeclaration().equals(var.getGenericDeclaration()) ? intermediateVar : this.resolveInternal(intermediateVar, forDependent);
                }
            });
        }

        /* JADX WARNING: type inference failed for: r7v0, types: [java.lang.reflect.Type, java.lang.reflect.TypeVariable, java.lang.reflect.TypeVariable<?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.reflect.Type resolveInternal(java.lang.reflect.TypeVariable<?> r7, com.google.common.reflect.TypeResolver.TypeTable r8) {
            /*
                r6 = this;
                r5 = 0
                com.google.common.collect.ImmutableMap<com.google.common.reflect.TypeResolver$TypeVariableKey, java.lang.reflect.Type> r3 = r6.map
                com.google.common.reflect.TypeResolver$TypeVariableKey r4 = new com.google.common.reflect.TypeResolver$TypeVariableKey
                r4.<init>(r7)
                java.lang.Object r2 = r3.get(r4)
                java.lang.reflect.Type r2 = (java.lang.reflect.Type) r2
                if (r2 != 0) goto L_0x0038
                java.lang.reflect.Type[] r0 = r7.getBounds()
                int r3 = r0.length
                if (r3 != 0) goto L_0x0018
            L_0x0017:
                return r7
            L_0x0018:
                com.google.common.reflect.TypeResolver r3 = new com.google.common.reflect.TypeResolver
                r3.<init>(r8)
                java.lang.reflect.Type[] r1 = r3.resolveTypes(r0)
                boolean r3 = com.google.common.reflect.Types.NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY
                if (r3 == 0) goto L_0x002b
                boolean r3 = java.util.Arrays.equals(r0, r1)
                if (r3 != 0) goto L_0x0017
            L_0x002b:
                java.lang.reflect.GenericDeclaration r3 = r7.getGenericDeclaration()
                java.lang.String r4 = r7.getName()
                java.lang.reflect.TypeVariable r7 = com.google.common.reflect.Types.newArtificialTypeVariable(r3, r4, r1)
                goto L_0x0017
            L_0x0038:
                com.google.common.reflect.TypeResolver r3 = new com.google.common.reflect.TypeResolver
                r3.<init>(r8)
                java.lang.reflect.Type r7 = r3.resolveType(r2)
                goto L_0x0017
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.reflect.TypeResolver.TypeTable.resolveInternal(java.lang.reflect.TypeVariable, com.google.common.reflect.TypeResolver$TypeTable):java.lang.reflect.Type");
        }
    }

    private static final class TypeMappingIntrospector extends TypeVisitor {
        private final Map<TypeVariableKey, Type> mappings = Maps.newHashMap();

        private TypeMappingIntrospector() {
        }

        static ImmutableMap<TypeVariableKey, Type> getTypeMappings(Type contextType) {
            Preconditions.checkNotNull(contextType);
            TypeMappingIntrospector introspector = new TypeMappingIntrospector();
            introspector.visit(contextType);
            return ImmutableMap.copyOf(introspector.mappings);
        }

        /* access modifiers changed from: package-private */
        public void visitClass(Class<?> clazz) {
            visit(clazz.getGenericSuperclass());
            visit(clazz.getGenericInterfaces());
        }

        /* access modifiers changed from: package-private */
        public void visitParameterizedType(ParameterizedType parameterizedType) {
            boolean z;
            Class<?> rawClass = (Class) parameterizedType.getRawType();
            TypeVariable<?>[] vars = rawClass.getTypeParameters();
            Type[] typeArgs = parameterizedType.getActualTypeArguments();
            if (vars.length == typeArgs.length) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkState(z);
            for (int i = 0; i < vars.length; i++) {
                map(new TypeVariableKey(vars[i]), typeArgs[i]);
            }
            visit(rawClass);
            visit(parameterizedType.getOwnerType());
        }

        /* access modifiers changed from: package-private */
        public void visitTypeVariable(TypeVariable<?> t) {
            visit(t.getBounds());
        }

        /* access modifiers changed from: package-private */
        public void visitWildcardType(WildcardType t) {
            visit(t.getUpperBounds());
        }

        private void map(TypeVariableKey var, Type arg) {
            if (!this.mappings.containsKey(var)) {
                Type t = arg;
                while (t != null) {
                    if (var.equalsType(t)) {
                        Type x = arg;
                        while (x != null) {
                            x = this.mappings.remove(TypeVariableKey.forLookup(x));
                        }
                        return;
                    }
                    t = this.mappings.get(TypeVariableKey.forLookup(t));
                }
                this.mappings.put(var, arg);
            }
        }
    }

    private static class WildcardCapturer {
        static final WildcardCapturer INSTANCE = new WildcardCapturer();

        /* renamed from: id */
        private final AtomicInteger f368id;

        private WildcardCapturer() {
            this(new AtomicInteger());
        }

        private WildcardCapturer(AtomicInteger id) {
            this.f368id = id;
        }

        /* access modifiers changed from: package-private */
        public final Type capture(Type type) {
            Preconditions.checkNotNull(type);
            if ((type instanceof Class) || (type instanceof TypeVariable)) {
                return type;
            }
            if (type instanceof GenericArrayType) {
                return Types.newArrayType(notForTypeVariable().capture(((GenericArrayType) type).getGenericComponentType()));
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class<?> rawType = (Class) parameterizedType.getRawType();
                TypeVariable<?>[] typeVars = rawType.getTypeParameters();
                Type[] typeArgs = parameterizedType.getActualTypeArguments();
                for (int i = 0; i < typeArgs.length; i++) {
                    typeArgs[i] = forTypeVariable(typeVars[i]).capture(typeArgs[i]);
                }
                return Types.newParameterizedTypeWithOwner(notForTypeVariable().captureNullable(parameterizedType.getOwnerType()), rawType, typeArgs);
            } else if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                if (wildcardType.getLowerBounds().length == 0) {
                    return captureAsTypeVariable(wildcardType.getUpperBounds());
                }
                return type;
            } else {
                throw new AssertionError("must have been one of the known types");
            }
        }

        /* access modifiers changed from: package-private */
        public TypeVariable<?> captureAsTypeVariable(Type[] upperBounds) {
            return Types.newArtificialTypeVariable(WildcardCapturer.class, "capture#" + this.f368id.incrementAndGet() + "-of ? extends " + Joiner.m49on('&').join((Object[]) upperBounds), upperBounds);
        }

        private WildcardCapturer forTypeVariable(final TypeVariable<?> typeParam) {
            return new WildcardCapturer(this.f368id) {
                /* access modifiers changed from: package-private */
                public TypeVariable<?> captureAsTypeVariable(Type[] upperBounds) {
                    Set<Type> combined = new LinkedHashSet<>(Arrays.asList(upperBounds));
                    combined.addAll(Arrays.asList(typeParam.getBounds()));
                    if (combined.size() > 1) {
                        combined.remove(Object.class);
                    }
                    return super.captureAsTypeVariable((Type[]) combined.toArray(new Type[0]));
                }
            };
        }

        private WildcardCapturer notForTypeVariable() {
            return new WildcardCapturer(this.f368id);
        }

        private Type captureNullable(@NullableDecl Type type) {
            if (type == null) {
                return null;
            }
            return capture(type);
        }
    }

    static final class TypeVariableKey {
        private final TypeVariable<?> var;

        TypeVariableKey(TypeVariable<?> var2) {
            this.var = (TypeVariable) Preconditions.checkNotNull(var2);
        }

        public int hashCode() {
            return Objects.hashCode(this.var.getGenericDeclaration(), this.var.getName());
        }

        public boolean equals(Object obj) {
            if (obj instanceof TypeVariableKey) {
                return equalsTypeVariable(((TypeVariableKey) obj).var);
            }
            return false;
        }

        public String toString() {
            return this.var.toString();
        }

        static TypeVariableKey forLookup(Type t) {
            if (t instanceof TypeVariable) {
                return new TypeVariableKey((TypeVariable) t);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean equalsType(Type type) {
            if (type instanceof TypeVariable) {
                return equalsTypeVariable((TypeVariable) type);
            }
            return false;
        }

        private boolean equalsTypeVariable(TypeVariable<?> that) {
            return this.var.getGenericDeclaration().equals(that.getGenericDeclaration()) && this.var.getName().equals(that.getName());
        }
    }
}
