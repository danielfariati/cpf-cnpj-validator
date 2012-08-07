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
	/**
	 * Indica a mensagem de erro a ser lançada
	 * @default "CNPJ inválido!"
	 */
	String message() default "CNPJ inválido!";

	/**
	 * Indica a necessidade de informar um CNPJ (not null / not empty)
	 * @default false
	 */
	boolean required() default false;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
