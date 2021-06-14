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
	private LocalDate validoAte;

	public AvisoViagemForm(@NotBlank String destino, @NotNull @Future LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	@Override
	public String toString() {
		return "AvisoViagemForm [destino=" + destino + ", dataTermino=" + validoAte + "]";
	}

	public AvisoViagem toModel(Cartao cartao, String ipCliente, String userAgent) {
		return new AvisoViagem(destino, validoAte, ipCliente, userAgent, cartao);
	}
}
