package com.danielfariati.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.danielfariati.annotation.CPF;

public class CPFValidator implements ConstraintValidator<CPF, String>{
	private CPF annotation;

	public void initialize(CPF annotation) {
		this.annotation = annotation;
	}

	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		if (cpf == null || cpf.isEmpty()) {
			if (annotation.required()) {
				return false;
			} else {
				return true;
			}
		}

		if (cpf.equals("00000000000") || cpf.length() != 11) {
			return false;
		}

		int i;
		int j;
		int digit;
		int coeficient;
		int sum;
		int[] foundDv = { 0, 0 };

		int dv1 = Integer.parseInt(String.valueOf(cpf.charAt(cpf.length() - 2)));
		int dv2 = Integer.parseInt(String.valueOf(cpf.charAt(cpf.length() - 1)));

		for (j = 0; j < 2; j++) {
			sum = 0;
			coeficient = 2;

			for (i = cpf.length() - 3 + j; i >= 0; i--) {
				digit = Integer.parseInt(String.valueOf(cpf.charAt(i)));
				sum += digit * coeficient;
				coeficient++;
			}

			foundDv[j] = 11 - sum % 11;

			if (foundDv[j] >= 10) {
				foundDv[j] = 0;
			}
		}

		return dv1 == foundDv[0] && dv2 == foundDv[1];
	}

};
