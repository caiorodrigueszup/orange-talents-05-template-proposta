package com.br.zupacademy.proposta.api.cartaoassociarcarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.br.zupacademy.proposta.api.criarcartao.Cartao;

public class AssociarCarteiraForm {

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String carteira;

	public AssociarCarteiraForm(@Email @NotBlank String email, @NotBlank String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

	@Override
	public String toString() {
		return "AssociarCarteiraForm [email=" + email + ", carteira=" + carteira + "]";
	}

	public Carteira toModel(Cartao cartao) {
		return new Carteira(carteira, email, cartao);
	}
}
