package com.excilys.ebi.bank.service.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.excilys.ebi.bank.model.IConstants;
import com.excilys.ebi.bank.service.AnnotationScanner;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;

@Component
public class AnnotationScannerImpl implements AnnotationScanner {

	@Override
	@Cacheable(cacheName = IConstants.Cache.ANNOTATION_SCANNER_CACHE, keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"))
	public <A extends Annotation> A getMethodOrTypeAnnotation(Class<A> annotationType, Method method, Class<?> type) {

		A annotation = AnnotationUtils.findAnnotation(method, annotationType);
		if (annotation == null) {
			annotation = AnnotationUtils.findAnnotation(type, annotationType);
		}

		return annotation;
	}
}
