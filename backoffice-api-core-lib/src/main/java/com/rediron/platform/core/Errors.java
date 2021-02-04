package com.rediron.platform.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "error")
@PropertySource(value = "classpath:errors.yml", factory = YamlPropertySourceFactory.class)
public class Errors {
	private Map<String, ErrorInfo> errors;

	@Data
	@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
	@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ErrorInfo {

		@JsonProperty(value = "status_code")
		private int status;
		private String url;
		@JsonIgnore
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
//		@Builder.Default
		private LocalDateTime timestamp ;//= LocalDateTime.now();
		@JsonProperty(value = "error_code")
		private String errorCode;
		
		@JsonProperty(value = "error_message")
		private String message;
//		private String debugMessage;
		private List<ValidationError> validationErrors;
		@JsonIgnore
		private Throwable cause;

		private void addValidationError(ValidationError validationError) {
			if (validationErrors == null) {
				validationErrors = new ArrayList<>();
			}
			validationErrors.add(validationError);
		}

		private void addValidationError(String object, String field, Object rejectedValue, String message) {
			addValidationError(new ValidationError(object, field, rejectedValue, message));
		}

		private void addValidationError(String object, String message) {
			addValidationError(new ValidationError(object, message));
		}

		private void addValidationError(FieldError fieldError) {
			this.addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(),
					fieldError.getDefaultMessage());
		}

		public void addValidationErrors(List<FieldError> fieldErrors) {
			fieldErrors.forEach(this::addValidationError);
		}

		private void addValidationError(ObjectError objectError) {
			this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
		}

		public void addValidationError(List<ObjectError> globalErrors) {
			globalErrors.forEach(this::addValidationError);
		}

		public ErrorInfo formatMessage(Object... args) {
			this.message = String.format(this.message, args);// new
			return this; // ErrorInfo.builder().message(String.format(this.message,
			// args)).build();
		}

		@Data
		@AllArgsConstructor
		class ValidationError {
			private String object;
			private String field;
			private Object rejectedValue;
			private String message;

			ValidationError(String object, String message) {
				this.object = object;
				this.message = message;
			}
		}
	}

	class LowerCaseClassNameResolver extends TypeIdResolverBase {

		@Override
		public String idFromValue(Object value) {
			return value.getClass().getSimpleName().toLowerCase();
		}

		@Override
		public String idFromValueAndType(Object value, Class<?> suggestedType) {
			return idFromValue(value);
		}

		@Override
		public JsonTypeInfo.Id getMechanism() {
			return JsonTypeInfo.Id.CUSTOM;
		}
	}

}
