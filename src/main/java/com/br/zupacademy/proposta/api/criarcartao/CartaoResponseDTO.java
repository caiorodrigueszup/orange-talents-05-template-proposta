package com.br.zupacademy.proposta.api.criarcartao;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.br.zupacademy.proposta.api.novaproposta.Proposta;

public class CartaoResponseDTO {
	@NotBlank
	private String id;
	@NotBlank
	private String titular;
	@NotNull
	private BigDecimal limite;
	@NotBlank
	private String idProposta;

	public CartaoResponseDTO(@NotBlank String id, @NotBlank String titular, @NotNull BigDecimal limite,
			@NotBlank String idProposta) {
		this.id = id;
		this.titular = titular;
		this.limite = limite;
		this.idProposta = idProposta;
	}

	public String getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public Cartao toModel(@NotNull @Valid Proposta proposta) {
		if (proposta.getId() != Long.parseLong(idProposta)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta não correspondente.");
		}

		return new Cartao(id, titular, limite, proposta);
	}

}
