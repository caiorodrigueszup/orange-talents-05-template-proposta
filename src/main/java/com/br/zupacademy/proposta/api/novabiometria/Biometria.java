package com.br.zupacademy.proposta.api.novabiometria;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.zupacademy.proposta.api.criarcartao.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String fingerprint;

	@NotNull
	@ManyToOne
	private Cartao cartao;

	@NotNull
	private LocalDate instante;

	@Deprecated
	public Biometria() {
	}

	public Biometria(@NotBlank String fingerprint, Cartao cartao) {
		this.fingerprint = fingerprint;
		this.cartao = cartao;
		this.instante = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Biometria [id=" + id + ", fingerprint=" + fingerprint + ", cartao=" + cartao + ", instante=" + instante
				+ "]";
	}

}
