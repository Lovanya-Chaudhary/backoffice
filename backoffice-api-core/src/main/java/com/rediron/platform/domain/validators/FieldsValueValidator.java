package com.rediron.platform.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

import com.rediron.platform.domain.validators.annotations.FieldsValue;

public class FieldsValueValidator implements ConstraintValidator<FieldsValue, Object> {

	private String field;
 
    public void initialize(FieldsValue constraintAnnotation) {
        this.field = constraintAnnotation.field();
     }
 
    public boolean isValid(Object value, ConstraintValidatorContext context) {
    	
    	Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
    	
        if (fieldValue == null || ((String)fieldValue).length() == 0) {
        	return false;
        } else {
            return true;
        }
    }
}
