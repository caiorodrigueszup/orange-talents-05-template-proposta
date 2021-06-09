package com.br.zupacademy.proposta.api.criarcartao;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.zupacademy.proposta.api.novaproposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String numero;

	@NotBlank
	private String titular;

	@NotNull
	private BigDecimal limite;

	@NotNull
	@OneToOne
	private Proposta proposta;

	@Deprecated
	public Cartao() {
	}

	public Cartao(@NotBlank String numero, @NotBlank String titular, @NotNull BigDecimal limite,
			@NotNull Proposta proposta) {
		super();
		this.numero = numero;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public Proposta getProposta() {
		return proposta;
	}

}
