package com.danielfariati.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.danielfariati.validator.CNPJValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CNPJValidator.class)
public @interface CNPJ {
	String message() default "CNPJ inválido!";

	boolean required();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
