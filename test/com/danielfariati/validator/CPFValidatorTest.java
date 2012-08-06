package com.danielfariati.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.danielfariati.annotation.CPF;

public class CPFValidatorTest {

	private ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class);
	private CPFValidator validatorRequired;
	private CPFValidator validatorNotRequired;

	@SuppressWarnings("unused")
	private static class ObjectWithRequiredCPF {
		@CPF(required = true)
		private String cpf;
	}

	@SuppressWarnings("unused")
	private static class ObjectWithNotRequiredCPF {
		@CPF(required = false)
		private String cpf;
	}

	@Before
	public void createValidator() throws Exception {
		CPF cpfRequiredAnnotation = ObjectWithRequiredCPF.class.getDeclaredField("cpf").getAnnotation(CPF.class);
		validatorRequired = new CPFValidator();
		validatorRequired.initialize(cpfRequiredAnnotation);

		CPF cpfNotRequiredAnnotation = ObjectWithNotRequiredCPF.class.getDeclaredField("cpf").getAnnotation(CPF.class);
		validatorNotRequired = new CPFValidator();
		validatorNotRequired.initialize(cpfNotRequiredAnnotation);
	}

	@Test
	public void shouldValidateValidCPF() {
		boolean valid = validatorRequired.isValid("11111111111", context);
		assertTrue(valid);
	}

	@Test
	public void shouldNotValidateInvalidCPF() {
		boolean valid = validatorRequired.isValid("12345678912", context);
		assertFalse(valid);
	}

	@Test
	public void shouldNotValidateCPFWithAllDigitsEqualZero() {
		boolean valid = validatorRequired.isValid("00000000000", context);
		assertFalse(valid);
	}

	@Test
	public void shouldNotValidateNullIfCPFRequired() {
		boolean valid = validatorRequired.isValid(null, context);
		assertFalse(valid);
	}

	@Test
	public void shouldValidateNullIfCPFNotRequired() {
		boolean valid = validatorNotRequired.isValid(null, context);
		assertTrue(valid);
	}

}
