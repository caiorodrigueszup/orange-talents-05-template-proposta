package com.br.zupacademy.proposta.api.analiseproposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.zupacademy.proposta.api.compartilhado.validacoes.ExistsId;
import com.br.zupacademy.proposta.api.compartilhado.validacoes.ValidarCPFeCNPJ;
import com.br.zupacademy.proposta.api.novaproposta.Proposta;

public class AnalisePropostaRequest {

	@NotBlank
	@ValidarCPFeCNPJ
	private String documento;

	@NotBlank
	private String nome;

	@NotNull
	@ExistsId(domainClass = Proposta.class, fieldName = "id")
	private Long idProposta;
	
	public AnalisePropostaRequest(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId();
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "AnalisePropostaRequest [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta + "]";
	}

}
