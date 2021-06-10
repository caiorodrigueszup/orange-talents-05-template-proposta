package com.br.zupacademy.proposta.api.compartilhado.validacoes;

import java.util.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<ValidadorBase64, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		try {
			Base64.getDecoder().decode(value.getBytes());
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
