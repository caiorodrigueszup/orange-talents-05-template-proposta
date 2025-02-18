package com.br.zupacademy.proposta.api.compartilhado.validacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ExistsIdValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsId {
	String message() default "Este registro não existe.";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

	Class<?> domainClass();
	
	String fieldName();
}
