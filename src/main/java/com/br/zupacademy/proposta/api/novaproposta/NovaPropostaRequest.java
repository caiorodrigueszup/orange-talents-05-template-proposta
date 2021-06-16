package com.br.zupacademy.proposta.api.novaproposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.br.zupacademy.proposta.api.compartilhado.validacoes.ValidarCPFeCNPJ;
import com.sun.istack.NotNull;

public class NovaPropostaRequest {
	
	@NotBlank
	@ValidarCPFeCNPJ
	private String documento;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String endereco;
	
	@NotNull
	@Positive
	private BigDecimal salario;

	public NovaPropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public String getDocumento() {
		return documento;
	}

	@Override
	public String toString() {
		return "NovaPropostaRequest [documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
				+ endereco + ", salario=" + salario + "]";
	}
	
	public Proposta toModel(String documentoCriptografado) {
		return new Proposta(documentoCriptografado, email, nome, endereco, salario);
	}
}
