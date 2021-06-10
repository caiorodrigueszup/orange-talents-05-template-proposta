package com.br.zupacademy.proposta.api.compartilhado.validacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = Base64Validator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidadorBase64 {
String message() default "não está no formato base64";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
