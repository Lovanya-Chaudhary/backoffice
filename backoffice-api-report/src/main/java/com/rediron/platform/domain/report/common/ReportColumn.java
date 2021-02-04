package com.rediron.platform.domain.report.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Inherited
public @interface ReportColumn {

	String displayName() default "";

	int columnOrder() default 0;

	double width() default 0;
}
