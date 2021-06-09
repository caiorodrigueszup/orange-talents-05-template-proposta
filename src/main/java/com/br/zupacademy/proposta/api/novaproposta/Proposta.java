package com.br.zupacademy.proposta.api.novaproposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.br.zupacademy.proposta.api.compartilhado.validacoes.ValidarCPFeCNPJ;
import com.br.zupacademy.proposta.api.criarcartao.Cartao;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank @ValidarCPFeCNPJ String documento;
	private @NotBlank @Email String email;
	private @NotBlank String nome;
	private @NotBlank String endereco;
	private @Positive BigDecimal salario;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;

	@Deprecated
	public Proposta() {
	};

	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public void situacao(Status status) {
		this.status = status;
	}
	
	public void adquire(Cartao cartao) {
		this.cartao = cartao;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
				+ endereco + ", salario=" + salario + "]";
	}

}
