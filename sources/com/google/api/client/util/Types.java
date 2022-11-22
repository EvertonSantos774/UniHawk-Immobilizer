package com.google.api.client.util;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Types {
    public static ParameterizedType getSuperParameterizedType(Type type, Class<?> superClass) {
        Class<?> rawType;
        Class<?> interfaceClass;
        if ((type instanceof Class) || (type instanceof ParameterizedType)) {
            while (type != null && type != Object.class) {
                if (type instanceof Class) {
                    rawType = (Class) type;
                } else {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    rawType = getRawClass(parameterizedType);
                    if (rawType == superClass) {
                        return parameterizedType;
                    }
                    if (superClass.isInterface()) {
                        Type[] genericInterfaces = rawType.getGenericInterfaces();
                        int length = genericInterfaces.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            Type interfaceType = genericInterfaces[i];
                            if (interfaceType instanceof Class) {
                                interfaceClass = (Class) interfaceType;
                            } else {
                                interfaceClass = getRawClass((ParameterizedType) interfaceType);
                            }
                            if (superClass.isAssignableFrom(interfaceClass)) {
                                type = interfaceType;
                                break;
                            }
                            i++;
                        }
                    }
                }
                type = rawType.getGenericSuperclass();
            }
        }
        return null;
    }

    public static boolean isAssignableToOrFrom(Class<?> classToCheck, Class<?> anotherClass) {
        return classToCheck.isAssignableFrom(anotherClass) || anotherClass.isAssignableFrom(classToCheck);
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException e) {
            throw handleExceptionForNewInstance(e, clazz);
        } catch (InstantiationException e2) {
            throw handleExceptionForNewInstance(e2, clazz);
        }
    }

    private static IllegalArgumentException handleExceptionForNewInstance(Exception e, Class<?> clazz) {
        StringBuilder buf = new StringBuilder("unable to create new instance of class ").append(clazz.getName());
        ArrayList<String> reasons = new ArrayList<>();
        if (clazz.isArray()) {
            reasons.add("because it is an array");
        } else if (clazz.isPrimitive()) {
            reasons.add("because it is primitive");
        } else if (clazz == Void.class) {
            reasons.add("because it is void");
        } else {
            if (Modifier.isInterface(clazz.getModifiers())) {
                reasons.add("because it is an interface");
            } else if (Modifier.isAbstract(clazz.getModifiers())) {
                reasons.add("because it is abstract");
            }
            if (clazz.getEnclosingClass() != null && !Modifier.isStatic(clazz.getModifiers())) {
                reasons.add("because it is not static");
            }
            if (!Modifier.isPublic(clazz.getModifiers())) {
                reasons.add("possibly because it is not public");
            } else {
                try {
                    clazz.getConstructor(new Class[0]);
                } catch (NoSuchMethodException e2) {
                    reasons.add("because it has no accessible default constructor");
                }
            }
        }
        boolean and = false;
        Iterator<String> it = reasons.iterator();
        while (it.hasNext()) {
            String reason = it.next();
            if (and) {
                buf.append(" and");
            } else {
                and = true;
            }
            buf.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(reason);
        }
        return new IllegalArgumentException(buf.toString(), e);
    }

    public static boolean isArray(Type type) {
        return (type instanceof GenericArrayType) || ((type instanceof Class) && ((Class) type).isArray());
    }

    public static Type getArrayComponentType(Type array) {
        if (array instanceof GenericArrayType) {
            return ((GenericArrayType) array).getGenericComponentType();
        }
        return ((Class) array).getComponentType();
    }

    public static Class<?> getRawClass(ParameterizedType parameterType) {
        return (Class) parameterType.getRawType();
    }

    public static Type getBound(WildcardType wildcardType) {
        Type[] lowerBounds = wildcardType.getLowerBounds();
        if (lowerBounds.length != 0) {
            return lowerBounds[0];
        }
        return wildcardType.getUpperBounds()[0];
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [java.lang.reflect.TypeVariable, java.lang.reflect.TypeVariable<?>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Type resolveTypeVariable(java.util.List<java.lang.reflect.Type> r10, java.lang.reflect.TypeVariable<?> r11) {
        /*
            java.lang.reflect.GenericDeclaration r1 = r11.getGenericDeclaration()
            boolean r9 = r1 instanceof java.lang.Class
            if (r9 == 0) goto L_0x004c
            r4 = r1
            java.lang.Class r4 = (java.lang.Class) r4
            int r0 = r10.size()
            r3 = 0
        L_0x0010:
            if (r3 != 0) goto L_0x0021
            int r0 = r0 + -1
            if (r0 < 0) goto L_0x0021
            java.lang.Object r9 = r10.get(r0)
            java.lang.reflect.Type r9 = (java.lang.reflect.Type) r9
            java.lang.reflect.ParameterizedType r3 = getSuperParameterizedType(r9, r4)
            goto L_0x0010
        L_0x0021:
            if (r3 == 0) goto L_0x004c
            java.lang.reflect.TypeVariable[] r8 = r1.getTypeParameters()
            r2 = 0
        L_0x0028:
            int r9 = r8.length
            if (r2 >= r9) goto L_0x0033
            r7 = r8[r2]
            boolean r9 = r7.equals(r11)
            if (r9 == 0) goto L_0x0047
        L_0x0033:
            java.lang.reflect.Type[] r9 = r3.getActualTypeArguments()
            r6 = r9[r2]
            boolean r9 = r6 instanceof java.lang.reflect.TypeVariable
            if (r9 == 0) goto L_0x004a
            r9 = r6
            java.lang.reflect.TypeVariable r9 = (java.lang.reflect.TypeVariable) r9
            java.lang.reflect.Type r5 = resolveTypeVariable(r10, r9)
            if (r5 == 0) goto L_0x004a
        L_0x0046:
            return r5
        L_0x0047:
            int r2 = r2 + 1
            goto L_0x0028
        L_0x004a:
            r5 = r6
            goto L_0x0046
        L_0x004c:
            r5 = 0
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.util.Types.resolveTypeVariable(java.util.List, java.lang.reflect.TypeVariable):java.lang.reflect.Type");
    }

    public static Class<?> getRawArrayComponentType(List<Type> context, Type componentType) {
        boolean z;
        if (componentType instanceof TypeVariable) {
            componentType = resolveTypeVariable(context, (TypeVariable) componentType);
        }
        if (componentType instanceof GenericArrayType) {
            return Array.newInstance(getRawArrayComponentType(context, getArrayComponentType(componentType)), 0).getClass();
        }
        if (componentType instanceof Class) {
            return (Class) componentType;
        }
        if (componentType instanceof ParameterizedType) {
            return getRawClass((ParameterizedType) componentType);
        }
        if (componentType == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "wildcard type is not supported: %s", componentType);
        return Object.class;
    }

    public static Type getIterableParameter(Type iterableType) {
        return getActualParameterAtPosition(iterableType, Iterable.class, 0);
    }

    public static Type getMapValueParameter(Type mapType) {
        return getActualParameterAtPosition(mapType, Map.class, 1);
    }

    private static Type getActualParameterAtPosition(Type type, Class<?> superClass, int position) {
        ParameterizedType parameterizedType = getSuperParameterizedType(type, superClass);
        if (parameterizedType == null) {
            return null;
        }
        Type valueType = parameterizedType.getActualTypeArguments()[position];
        if (valueType instanceof TypeVariable) {
            Type resolve = resolveTypeVariable(Arrays.asList(new Type[]{type}), (TypeVariable) valueType);
            if (resolve != null) {
                return resolve;
            }
        }
        return valueType;
    }

    public static <T> Iterable<T> iterableOf(final Object value) {
        if (value instanceof Iterable) {
            return (Iterable) value;
        }
        Class<?> valueClass = value.getClass();
        Preconditions.checkArgument(valueClass.isArray(), "not an array or Iterable: %s", valueClass);
        if (!valueClass.getComponentType().isPrimitive()) {
            return Arrays.asList((Object[]) value);
        }
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int index = 0;
                    final int length = Array.getLength(value);

                    public boolean hasNext() {
                        return this.index < this.length;
                    }

                    public T next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        Object obj = value;
                        int i = this.index;
                        this.index = i + 1;
                        return Array.get(obj, i);
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public static Object toArray(Collection<?> collection, Class<?> componentType) {
        if (!componentType.isPrimitive()) {
            return collection.toArray((Object[]) Array.newInstance(componentType, collection.size()));
        }
        Object array = Array.newInstance(componentType, collection.size());
        int index = 0;
        for (Object value : collection) {
            Array.set(array, index, value);
            index++;
        }
        return array;
    }

    private Types() {
    }
}
