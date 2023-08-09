package com.evlpract.customvalidate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//import CollegeTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=CollegeTypeValidator.class)
public @interface CustomValidator {
	public String message() default "College is not in the list";
	
	Class<?> [] groups() default{};
	Class<? extends Payload>[] payload() default{};

}
