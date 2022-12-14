package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class AnnotatedClass extends Annotated {
    private static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final Class<?> _class;
    protected AnnotationMap _classAnnotations;
    protected List<AnnotatedConstructor> _constructors;
    protected List<AnnotatedMethod> _creatorMethods;
    protected boolean _creatorsResolved = false;
    protected AnnotatedConstructor _defaultConstructor;
    protected List<AnnotatedField> _fields;
    protected AnnotatedMethodMap _memberMethods;
    protected final ClassIntrospector.MixInResolver _mixInResolver;
    protected final Class<?> _primaryMixIn;
    protected final List<Class<?>> _superTypes;

    private AnnotatedClass(Class<?> cls, List<Class<?>> list, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver, AnnotationMap annotationMap) {
        this._class = cls;
        this._superTypes = list;
        this._annotationIntrospector = annotationIntrospector;
        this._mixInResolver = mixInResolver;
        this._primaryMixIn = this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(this._class);
        this._classAnnotations = annotationMap;
    }

    public AnnotatedClass withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedClass(this._class, this._superTypes, this._annotationIntrospector, this._mixInResolver, annotationMap);
    }

    public static AnnotatedClass construct(Class<?> cls, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver) {
        return new AnnotatedClass(cls, ClassUtil.findSuperTypes(cls, (Class<?>) null), annotationIntrospector, mixInResolver, (AnnotationMap) null);
    }

    public static AnnotatedClass constructWithoutSuperTypes(Class<?> cls, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver) {
        return new AnnotatedClass(cls, Collections.emptyList(), annotationIntrospector, mixInResolver, (AnnotationMap) null);
    }

    public Class<?> getAnnotated() {
        return this._class;
    }

    public int getModifiers() {
        return this._class.getModifiers();
    }

    public String getName() {
        return this._class.getName();
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations.get(cls);
    }

    public Type getGenericType() {
        return this._class;
    }

    public Class<?> getRawType() {
        return this._class;
    }

    /* access modifiers changed from: protected */
    public AnnotationMap getAllAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations;
    }

    public Annotations getAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations;
    }

    public boolean hasAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations.size() > 0;
    }

    public AnnotatedConstructor getDefaultConstructor() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._defaultConstructor;
    }

    public List<AnnotatedConstructor> getConstructors() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._constructors;
    }

    public List<AnnotatedMethod> getStaticMethods() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._creatorMethods;
    }

    public Iterable<AnnotatedMethod> memberMethods() {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods;
    }

    public int getMemberMethodCount() {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods.size();
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods.find(str, clsArr);
    }

    public int getFieldCount() {
        if (this._fields == null) {
            resolveFields();
        }
        return this._fields.size();
    }

    public Iterable<AnnotatedField> fields() {
        if (this._fields == null) {
            resolveFields();
        }
        return this._fields;
    }

    private void resolveClassAnnotations() {
        this._classAnnotations = new AnnotationMap();
        if (this._annotationIntrospector != null) {
            if (this._primaryMixIn != null) {
                _addClassMixIns(this._classAnnotations, this._class, this._primaryMixIn);
            }
            _addAnnotationsIfNotPresent(this._classAnnotations, this._class.getDeclaredAnnotations());
            for (Class next : this._superTypes) {
                _addClassMixIns(this._classAnnotations, next);
                _addAnnotationsIfNotPresent(this._classAnnotations, next.getDeclaredAnnotations());
            }
            _addClassMixIns(this._classAnnotations, Object.class);
        }
    }

    private void resolveCreators() {
        Constructor[] declaredConstructors = this._class.getDeclaredConstructors();
        ArrayList arrayList = null;
        for (Constructor constructor : declaredConstructors) {
            if (constructor.getParameterTypes().length == 0) {
                this._defaultConstructor = _constructConstructor(constructor, true);
            } else {
                if (arrayList == null) {
                    arrayList = new ArrayList(Math.max(10, declaredConstructors.length));
                }
                arrayList.add(_constructConstructor(constructor, false));
            }
        }
        if (arrayList == null) {
            this._constructors = Collections.emptyList();
        } else {
            this._constructors = arrayList;
        }
        if (this._primaryMixIn != null && (this._defaultConstructor != null || !this._constructors.isEmpty())) {
            _addConstructorMixIns(this._primaryMixIn);
        }
        if (this._annotationIntrospector != null) {
            if (this._defaultConstructor != null && this._annotationIntrospector.hasIgnoreMarker(this._defaultConstructor)) {
                this._defaultConstructor = null;
            }
            if (this._constructors != null) {
                int size = this._constructors.size();
                while (true) {
                    int i = size - 1;
                    if (i < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker(this._constructors.get(i))) {
                        this._constructors.remove(i);
                        size = i;
                    } else {
                        size = i;
                    }
                }
            }
        }
        ArrayList arrayList2 = null;
        for (Method method : this._class.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers())) {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList(8);
                }
                arrayList2.add(_constructCreatorMethod(method));
            }
        }
        if (arrayList2 == null) {
            this._creatorMethods = Collections.emptyList();
        } else {
            this._creatorMethods = arrayList2;
            if (this._primaryMixIn != null) {
                _addFactoryMixIns(this._primaryMixIn);
            }
            if (this._annotationIntrospector != null) {
                int size2 = this._creatorMethods.size();
                while (true) {
                    int i2 = size2 - 1;
                    if (i2 < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker(this._creatorMethods.get(i2))) {
                        this._creatorMethods.remove(i2);
                        size2 = i2;
                    } else {
                        size2 = i2;
                    }
                }
            }
        }
        this._creatorsResolved = true;
    }

    private void resolveMemberMethods() {
        Class<?> findMixInClassFor;
        this._memberMethods = new AnnotatedMethodMap();
        AnnotatedMethodMap annotatedMethodMap = new AnnotatedMethodMap();
        _addMemberMethods(this._class, this._memberMethods, this._primaryMixIn, annotatedMethodMap);
        for (Class next : this._superTypes) {
            _addMemberMethods(next, this._memberMethods, this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(next), annotatedMethodMap);
        }
        if (!(this._mixInResolver == null || (findMixInClassFor = this._mixInResolver.findMixInClassFor(Object.class)) == null)) {
            _addMethodMixIns(this._class, this._memberMethods, findMixInClassFor, annotatedMethodMap);
        }
        if (this._annotationIntrospector != null && !annotatedMethodMap.isEmpty()) {
            Iterator<AnnotatedMethod> it = annotatedMethodMap.iterator();
            while (it.hasNext()) {
                AnnotatedMethod next2 = it.next();
                try {
                    Method declaredMethod = Object.class.getDeclaredMethod(next2.getName(), next2.getRawParameterTypes());
                    if (declaredMethod != null) {
                        AnnotatedMethod _constructMethod = _constructMethod(declaredMethod);
                        _addMixOvers(next2.getAnnotated(), _constructMethod, false);
                        this._memberMethods.add(_constructMethod);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    private void resolveFields() {
        Map<String, AnnotatedField> _findFields = _findFields(this._class, (Map<String, AnnotatedField>) null);
        if (_findFields == null || _findFields.size() == 0) {
            this._fields = Collections.emptyList();
            return;
        }
        this._fields = new ArrayList(_findFields.size());
        this._fields.addAll(_findFields.values());
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls) {
        if (this._mixInResolver != null) {
            _addClassMixIns(annotationMap, cls, this._mixInResolver.findMixInClassFor(cls));
        }
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls, Class<?> cls2) {
        if (cls2 != null) {
            _addAnnotationsIfNotPresent(annotationMap, cls2.getDeclaredAnnotations());
            for (Class<?> declaredAnnotations : ClassUtil.findSuperTypes(cls2, cls)) {
                _addAnnotationsIfNotPresent(annotationMap, declaredAnnotations.getDeclaredAnnotations());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addConstructorMixIns(Class<?> cls) {
        MemberKey[] memberKeyArr;
        int size = this._constructors == null ? 0 : this._constructors.size();
        MemberKey[] memberKeyArr2 = null;
        for (Constructor constructor : cls.getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length != 0) {
                if (memberKeyArr2 == null) {
                    memberKeyArr = new MemberKey[size];
                    for (int i = 0; i < size; i++) {
                        memberKeyArr[i] = new MemberKey((Constructor<?>) this._constructors.get(i).getAnnotated());
                    }
                } else {
                    memberKeyArr = memberKeyArr2;
                }
                MemberKey memberKey = new MemberKey((Constructor<?>) constructor);
                int i2 = 0;
                while (true) {
                    if (i2 < size) {
                        if (memberKey.equals(memberKeyArr[i2])) {
                            _addMixOvers((Constructor<?>) constructor, this._constructors.get(i2), true);
                            memberKeyArr2 = memberKeyArr;
                            break;
                        }
                        i2++;
                    } else {
                        memberKeyArr2 = memberKeyArr;
                        break;
                    }
                }
            } else if (this._defaultConstructor != null) {
                _addMixOvers((Constructor<?>) constructor, this._defaultConstructor, false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addFactoryMixIns(Class<?> cls) {
        MemberKey[] memberKeyArr;
        MemberKey[] memberKeyArr2 = null;
        int size = this._creatorMethods.size();
        for (Method method : cls.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length != 0) {
                if (memberKeyArr2 == null) {
                    memberKeyArr = new MemberKey[size];
                    for (int i = 0; i < size; i++) {
                        memberKeyArr[i] = new MemberKey(this._creatorMethods.get(i).getAnnotated());
                    }
                } else {
                    memberKeyArr = memberKeyArr2;
                }
                MemberKey memberKey = new MemberKey(method);
                int i2 = 0;
                while (true) {
                    if (i2 < size) {
                        if (memberKey.equals(memberKeyArr[i2])) {
                            _addMixOvers(method, this._creatorMethods.get(i2), true);
                            memberKeyArr2 = memberKeyArr;
                            break;
                        }
                        i2++;
                    } else {
                        memberKeyArr2 = memberKeyArr;
                        break;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMemberMethods(Class<?> cls, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        if (cls2 != null) {
            _addMethodMixIns(cls, annotatedMethodMap, cls2, annotatedMethodMap2);
        }
        if (cls != null) {
            for (Method method : cls.getDeclaredMethods()) {
                if (_isIncludableMemberMethod(method)) {
                    AnnotatedMethod find = annotatedMethodMap.find(method);
                    if (find == null) {
                        AnnotatedMethod _constructMethod = _constructMethod(method);
                        annotatedMethodMap.add(_constructMethod);
                        AnnotatedMethod remove = annotatedMethodMap2.remove(method);
                        if (remove != null) {
                            _addMixOvers(remove.getAnnotated(), _constructMethod, false);
                        }
                    } else {
                        _addMixUnders(method, find);
                        if (find.getDeclaringClass().isInterface() && !method.getDeclaringClass().isInterface()) {
                            annotatedMethodMap.add(find.withMethod(method));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMethodMixIns(Class<?> cls, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        ArrayList<Class> arrayList = new ArrayList<>();
        arrayList.add(cls2);
        ClassUtil.findSuperTypes(cls2, cls, arrayList);
        for (Class declaredMethods : arrayList) {
            for (Method method : declaredMethods.getDeclaredMethods()) {
                if (_isIncludableMemberMethod(method)) {
                    AnnotatedMethod find = annotatedMethodMap.find(method);
                    if (find != null) {
                        _addMixUnders(method, find);
                    } else {
                        annotatedMethodMap2.add(_constructMethod(method));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, AnnotatedField> _findFields(Class<?> cls, Map<String, AnnotatedField> map) {
        Class<?> findMixInClassFor;
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass == null) {
            return map;
        }
        Map<String, AnnotatedField> _findFields = _findFields(superclass, map);
        Map<String, AnnotatedField> map2 = _findFields;
        for (Field field : cls.getDeclaredFields()) {
            if (_isIncludableField(field)) {
                if (map2 == null) {
                    map2 = new LinkedHashMap<>();
                }
                map2.put(field.getName(), _constructField(field));
            }
        }
        if (this._mixInResolver == null || (findMixInClassFor = this._mixInResolver.findMixInClassFor(cls)) == null) {
            return map2;
        }
        _addFieldMixIns(superclass, findMixInClassFor, map2);
        return map2;
    }

    /* access modifiers changed from: protected */
    public void _addFieldMixIns(Class<?> cls, Class<?> cls2, Map<String, AnnotatedField> map) {
        AnnotatedField annotatedField;
        ArrayList<Class> arrayList = new ArrayList<>();
        arrayList.add(cls2);
        ClassUtil.findSuperTypes(cls2, cls, arrayList);
        for (Class declaredFields : arrayList) {
            for (Field field : declaredFields.getDeclaredFields()) {
                if (_isIncludableField(field) && (annotatedField = map.get(field.getName())) != null) {
                    _addOrOverrideAnnotations(annotatedField, field.getDeclaredAnnotations());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _constructMethod(Method method) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedMethod(method, _emptyAnnotationMap(), (AnnotationMap[]) null);
        }
        return new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), (AnnotationMap[]) null);
    }

    /* access modifiers changed from: protected */
    public AnnotatedConstructor _constructConstructor(Constructor<?> constructor, boolean z) {
        AnnotationMap[] annotationMapArr;
        Annotation[][] annotationArr;
        if (this._annotationIntrospector == null) {
            return new AnnotatedConstructor(constructor, _emptyAnnotationMap(), _emptyAnnotationMaps(constructor.getParameterTypes().length));
        }
        if (z) {
            return new AnnotatedConstructor(constructor, _collectRelevantAnnotations(constructor.getDeclaredAnnotations()), (AnnotationMap[]) null);
        }
        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
        int length = constructor.getParameterTypes().length;
        if (length != parameterAnnotations.length) {
            Class<?> declaringClass = constructor.getDeclaringClass();
            if (declaringClass.isEnum() && length == parameterAnnotations.length + 2) {
                annotationArr = new Annotation[(parameterAnnotations.length + 2)][];
                System.arraycopy(parameterAnnotations, 0, annotationArr, 2, parameterAnnotations.length);
                annotationMapArr = _collectRelevantAnnotations(annotationArr);
            } else if (!declaringClass.isMemberClass() || length != parameterAnnotations.length + 1) {
                annotationArr = parameterAnnotations;
                annotationMapArr = null;
            } else {
                annotationArr = new Annotation[(parameterAnnotations.length + 1)][];
                System.arraycopy(parameterAnnotations, 0, annotationArr, 1, parameterAnnotations.length);
                annotationMapArr = _collectRelevantAnnotations(annotationArr);
            }
            if (annotationMapArr == null) {
                throw new IllegalStateException("Internal error: constructor for " + constructor.getDeclaringClass().getName() + " has mismatch: " + length + " parameters; " + annotationArr.length + " sets of annotations");
            }
        } else {
            annotationMapArr = _collectRelevantAnnotations(parameterAnnotations);
        }
        return new AnnotatedConstructor(constructor, _collectRelevantAnnotations(constructor.getDeclaredAnnotations()), annotationMapArr);
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _constructCreatorMethod(Method method) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedMethod(method, _emptyAnnotationMap(), _emptyAnnotationMaps(method.getParameterTypes().length));
        }
        return new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), _collectRelevantAnnotations(method.getParameterAnnotations()));
    }

    /* access modifiers changed from: protected */
    public AnnotatedField _constructField(Field field) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedField(field, _emptyAnnotationMap());
        }
        return new AnnotatedField(field, _collectRelevantAnnotations(field.getDeclaredAnnotations()));
    }

    private AnnotationMap _emptyAnnotationMap() {
        return new AnnotationMap();
    }

    private AnnotationMap[] _emptyAnnotationMaps(int i) {
        if (i == 0) {
            return NO_ANNOTATION_MAPS;
        }
        AnnotationMap[] annotationMapArr = new AnnotationMap[i];
        for (int i2 = 0; i2 < i; i2++) {
            annotationMapArr[i2] = _emptyAnnotationMap();
        }
        return annotationMapArr;
    }

    /* access modifiers changed from: protected */
    public boolean _isIncludableMemberMethod(Method method) {
        if (!Modifier.isStatic(method.getModifiers()) && !method.isSynthetic() && !method.isBridge() && method.getParameterTypes().length <= 2) {
            return true;
        }
        return false;
    }

    private boolean _isIncludableField(Field field) {
        if (field.isSynthetic()) {
            return false;
        }
        int modifiers = field.getModifiers();
        if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public AnnotationMap[] _collectRelevantAnnotations(Annotation[][] annotationArr) {
        int length = annotationArr.length;
        AnnotationMap[] annotationMapArr = new AnnotationMap[length];
        for (int i = 0; i < length; i++) {
            annotationMapArr[i] = _collectRelevantAnnotations(annotationArr[i]);
        }
        return annotationMapArr;
    }

    /* access modifiers changed from: protected */
    public AnnotationMap _collectRelevantAnnotations(Annotation[] annotationArr) {
        AnnotationMap annotationMap = new AnnotationMap();
        _addAnnotationsIfNotPresent(annotationMap, annotationArr);
        return annotationMap;
    }

    private void _addAnnotationsIfNotPresent(AnnotationMap annotationMap, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList<Annotation[]> linkedList = null;
            for (Annotation annotation : annotationArr) {
                if (_isAnnotationBundle(annotation)) {
                    if (linkedList == null) {
                        linkedList = new LinkedList<>();
                    }
                    linkedList.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotationMap.addIfNotPresent(annotation);
                }
            }
            if (linkedList != null) {
                for (Annotation[] _addAnnotationsIfNotPresent : linkedList) {
                    _addAnnotationsIfNotPresent(annotationMap, _addAnnotationsIfNotPresent);
                }
            }
        }
    }

    private void _addAnnotationsIfNotPresent(AnnotatedMember annotatedMember, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList<Annotation[]> linkedList = null;
            for (Annotation annotation : annotationArr) {
                if (_isAnnotationBundle(annotation)) {
                    if (linkedList == null) {
                        linkedList = new LinkedList<>();
                    }
                    linkedList.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotatedMember.addIfNotPresent(annotation);
                }
            }
            if (linkedList != null) {
                for (Annotation[] _addAnnotationsIfNotPresent : linkedList) {
                    _addAnnotationsIfNotPresent(annotatedMember, _addAnnotationsIfNotPresent);
                }
            }
        }
    }

    private void _addOrOverrideAnnotations(AnnotatedMember annotatedMember, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList<Annotation[]> linkedList = null;
            for (Annotation annotation : annotationArr) {
                if (_isAnnotationBundle(annotation)) {
                    if (linkedList == null) {
                        linkedList = new LinkedList<>();
                    }
                    linkedList.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotatedMember.addOrOverride(annotation);
                }
            }
            if (linkedList != null) {
                for (Annotation[] _addOrOverrideAnnotations : linkedList) {
                    _addOrOverrideAnnotations(annotatedMember, _addOrOverrideAnnotations);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixOvers(Constructor<?> constructor, AnnotatedConstructor annotatedConstructor, boolean z) {
        _addOrOverrideAnnotations(annotatedConstructor, constructor.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation addOrOverrideParam : parameterAnnotations[i]) {
                    annotatedConstructor.addOrOverrideParam(i, addOrOverrideParam);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixOvers(Method method, AnnotatedMethod annotatedMethod, boolean z) {
        _addOrOverrideAnnotations(annotatedMethod, method.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation addOrOverrideParam : parameterAnnotations[i]) {
                    annotatedMethod.addOrOverrideParam(i, addOrOverrideParam);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixUnders(Method method, AnnotatedMethod annotatedMethod) {
        _addAnnotationsIfNotPresent((AnnotatedMember) annotatedMethod, method.getDeclaredAnnotations());
    }

    private final boolean _isAnnotationBundle(Annotation annotation) {
        return this._annotationIntrospector != null && this._annotationIntrospector.isAnnotationBundle(annotation);
    }

    public String toString() {
        return "[AnnotedClass " + this._class.getName() + "]";
    }
}
