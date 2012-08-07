package com.danielfariati.validator.bean;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danielfariati.annotation.CNPJ;

public class CNPJBeanValidator {
	private static Validator validator;

	public class Model {
		@CNPJ(required = true)
		private String cnpj;

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
	}

	@BeforeClass
	public static void setUp() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	public void testValidCNPJShouldHaveEmptyErrorList() {
		Model model = new Model();
		model.setCnpj("88790945000103");

		Set<ConstraintViolation<Model>> violations = validator.validate(model);
		assertEquals(0, violations.size());
	}

	@Test
	public void testInvalidCNPJShouldHaveOneErrorInTheErrorList() {
		Model model = new Model();
		model.setCnpj("88790945000100");

		ArrayList<ConstraintViolation<Model>> violations = new ArrayList<ConstraintViolation<Model>>(validator.validate(model));

		assertEquals(1, violations.size());
		assertEquals(violations.get(0).getMessage(), "CNPJ inválido!");
	}

}
