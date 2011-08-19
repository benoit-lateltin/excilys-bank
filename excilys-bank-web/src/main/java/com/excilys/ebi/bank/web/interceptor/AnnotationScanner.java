package com.excilys.ebi.bank.web.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public interface AnnotationScanner {

	<A extends Annotation> A getMethodOrTypeAnnotation(Class<A> annotationType, Method method, Class<?> type);
}
