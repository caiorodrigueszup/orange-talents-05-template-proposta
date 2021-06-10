package com.br.zupacademy.proposta.api.novabiometria;

import javax.validation.constraints.NotBlank;

import com.br.zupacademy.proposta.api.compartilhado.validacoes.ValidadorBase64;
import com.br.zupacademy.proposta.api.criarcartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class NovaBiometriaRequest {
	
	@NotBlank
	@ValidadorBase64
	private String fingerprint;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public NovaBiometriaRequest(@NotBlank String fingerprint) {
		this.fingerprint = fingerprint;
	}

	@Override
	public String toString() {
		return "NovaBiometriaRequest [fingerprint=" + fingerprint + "]";
	}
	
	public Biometria toModel(Cartao cartao) {
		return new Biometria(fingerprint, cartao);
	}
	
}
