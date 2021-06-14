package com.br.zupacademy.proposta.api.cadastroavisoviagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.zupacademy.proposta.api.criarcartao.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String destino;

	@NotNull
	private LocalDate dataTermino;

	@NotBlank
	private String ipCliente;

	@NotBlank
	private String userAgent;

	@NotNull
	@ManyToOne
	private Cartao cartao;

	private LocalDateTime instante;

	@Deprecated
	public AvisoViagem() {
	}

	public AvisoViagem(@NotBlank String destino, @NotNull LocalDate dataTermino, @NotBlank String ipCliente,
			@NotBlank String userAgent, @NotNull Cartao cartao) {
		this.destino = destino;
		this.dataTermino = dataTermino;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
		this.instante = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "AvisoViagem [id=" + id + ", destino=" + destino + ", dataTermino=" + dataTermino + ", ipCliente="
				+ ipCliente + ", userAgent=" + userAgent + ", cartao=" + cartao + ", instante=" + instante + "]";
	}

}
