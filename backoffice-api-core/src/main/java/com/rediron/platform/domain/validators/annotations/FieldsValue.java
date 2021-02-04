package com.rediron.platform.domain.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.rediron.platform.domain.validators.FieldsValueValidator;

@Constraint(validatedBy = {FieldsValueValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValue {
	
	String message() default "Invalid Field value!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	 
    String field(); 
 
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
    	FieldsValue[] value();
    }
}
