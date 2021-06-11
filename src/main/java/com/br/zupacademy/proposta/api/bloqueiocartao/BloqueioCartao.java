package com.br.zupacademy.proposta.api.bloqueiocartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.zupacademy.proposta.api.criarcartao.Cartao;

@Entity(name = "cartoes_bloqueados")
public class BloqueioCartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@OneToOne
	private Cartao cartao;

	@NotBlank
	private String enderecoIp;

	@NotBlank
	private String userAgent;

	@NotNull
	private LocalDateTime instante;

	@Deprecated
	public BloqueioCartao() {
	}

	public BloqueioCartao(@NotNull Cartao cartao, @NotBlank String enderecoIp, @NotBlank String userAgent) {
		super();
		this.cartao = cartao;
		this.enderecoIp = enderecoIp;
		this.userAgent = userAgent;
		this.instante = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "BloqueioCartao [id=" + id + ", cartao=" + cartao + ", enderecoIp=" + enderecoIp + ", userAgent="
				+ userAgent + ", instante=" + instante + "]";
	}

}
