package com.danielfariati.validator.bean;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;

import com.danielfariati.annotation.CPF;

public class CPFBeanValidator {
	private static Validator validator;

	public class Model {
		@CPF(required = true)
		private String cpf;

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
	}

	@BeforeClass
	public static void setUp() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	public void testValidCPFShouldHaveEmptyErrorList() {
		Model model = new Model();
		model.setCpf("11111111111");

		Set<ConstraintViolation<Model>> violations = validator.validate(model);
		assertEquals(0, violations.size());
	}

	@Test
	public void testInvalidCPFShouldHaveOneErrorInTheErrorList() {
		Model model = new Model();
		model.setCpf("00000000000");

		ArrayList<ConstraintViolation<Model>> violations = new ArrayList<ConstraintViolation<Model>>(validator.validate(model));

		assertEquals(1, violations.size());
		assertEquals(violations.get(0).getMessage(), "CPF inválido!");
	}

}
