package com.danielfariati.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.danielfariati.annotation.CNPJ;

public class CNPJValidatorTest {

	private ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class);
	private CNPJValidator validatorRequired;
	private CNPJValidator validatorNotRequired;

	@SuppressWarnings("unused")
	private static class ObjectWithRequiredCNPJ {
		@CNPJ(required = true)
		private String cnpj;
	}

	@SuppressWarnings("unused")
	private static class ObjectWithNotRequiredCNPJ {
		@CNPJ(required = false)
		private String cnpj;
	}

	@Before
	public void createValidator() throws Exception {
		CNPJ cnpjRequiredAnnotation = ObjectWithRequiredCNPJ.class.getDeclaredField("cnpj").getAnnotation(CNPJ.class);
		validatorRequired = new CNPJValidator();
		validatorRequired.initialize(cnpjRequiredAnnotation);

		CNPJ cnpjNotRequiredAnnotation = ObjectWithNotRequiredCNPJ.class.getDeclaredField("cnpj").getAnnotation(CNPJ.class);
		validatorNotRequired = new CNPJValidator();
		validatorNotRequired.initialize(cnpjNotRequiredAnnotation);
	}

	@Test
	public void shouldValidateValidCNPJ() {
		boolean valid = validatorRequired.isValid("22287622000185", context);
		assertTrue(valid);
	}

	@Test
	public void shouldNotValidateInvalidCNPJ() {
		boolean valid = validatorRequired.isValid("22287622000180", context);
		assertFalse(valid);
	}

	@Test
	public void shouldNotValidateCNPJWithAllDigitsEqualZero() {
		boolean valid = validatorRequired.isValid("00000000000000", context);
		assertFalse(valid);
	}

	@Test
	public void shouldNotValidateNullIfCNPJRequired() {
		boolean valid = validatorRequired.isValid(null, context);
		assertFalse(valid);
	}

	@Test
	public void shouldValidateNullIfCNPJNotRequired() {
		boolean valid = validatorNotRequired.isValid(null, context);
		assertTrue(valid);
	}

}
