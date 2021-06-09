package com.br.zupacademy.proposta.api.detalheproposta;

import com.br.zupacademy.proposta.api.novaproposta.Proposta;
import com.br.zupacademy.proposta.api.novaproposta.Status;

public class PropostaResponse {
	private String nome;
	private Status status;

	public PropostaResponse(Proposta proposta) {
		this.nome = proposta.getNome();
		this.status = proposta.getStatus();
	}

	public String getNome() {
		return nome;
	}

	public Status getStatus() {
		return status;
	}

}
