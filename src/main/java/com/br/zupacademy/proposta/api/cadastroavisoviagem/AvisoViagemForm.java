package com.br.zupacademy.proposta.api.cadastroavisoviagem;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.zupacademy.proposta.api.criarcartao.Cartao;

public class AvisoViagemForm {
	
	@NotBlank
	private String destino;
	
	@Future
	@NotNull
	private LocalDate dataTermino;

	public AvisoViagemForm(@NotBlank String destino, @Future LocalDate dataTermino) {
		this.destino = destino;
		this.dataTermino = dataTermino;
	}

	@Override
	public String toString() {
		return "AvisoViagemForm [destino=" + destino + ", dataTermino=" + dataTermino + "]";
	}
	
	public AvisoViagem toModel(Cartao cartao, String ipCliente, String userAgent) {
		return new AvisoViagem(destino, dataTermino, ipCliente, userAgent, cartao);
	}
}
