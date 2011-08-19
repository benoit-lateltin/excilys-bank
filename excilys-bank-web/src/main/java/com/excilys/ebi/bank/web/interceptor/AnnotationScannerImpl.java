package com.excilys.ebi.bank.web.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.Cacheable;

@Component
public class AnnotationScannerImpl implements AnnotationScanner {

	@Override
	@Cacheable(cacheName = "annotationScanner")
	public <A extends Annotation> A getMethodOrTypeAnnotation(Class<A> annotationType, Method method, Class<?> type) {

		A annotation = AnnotationUtils.findAnnotation(method, annotationType);
		if (annotation == null) {
			annotation = AnnotationUtils.findAnnotation(type, annotationType);
		}

		return annotation;
	}
}
