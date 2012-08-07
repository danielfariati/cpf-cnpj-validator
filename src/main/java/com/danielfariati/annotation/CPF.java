package com.danielfariati.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.danielfariati.validator.CPFValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPFValidator.class)
public @interface CPF {
	/**
	 * Indica a mensagem de erro a ser lançada
	 * @default "CPF inválido!"
	 */
	String message() default "CPF inválido!";

	/**
	 * Indica a necessidade de informar um CPF (not null)
	 * @default false
	 */
	boolean required() default false;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
