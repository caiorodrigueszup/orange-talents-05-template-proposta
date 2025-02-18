package com.br.zupacademy.proposta.api.criarcartao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.zupacademy.proposta.api.cartaoassociarcarteira.Carteira;
import com.br.zupacademy.proposta.api.novabiometria.Biometria;
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

	@OneToMany(mappedBy = "cartao")
	private List<Biometria> biometrias;
	
	@Enumerated(EnumType.STRING)
	private StatusCartao status;
	
	@OneToMany(mappedBy = "cartao")
	private List<Carteira> carteiras;

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
		this.biometrias = new ArrayList<Biometria>();
		this.status = StatusCartao.NAO_BLOQUEADO;
		this.carteiras = new ArrayList<Carteira>();
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

	public List<Biometria> getBiometrias() {
		return biometrias;
	}

	public void inserirNova(Biometria biometria) {
		this.biometrias.add(biometria);
	}
	
	public boolean estaBloqueado() {
		if (status.equals(StatusCartao.BLOQUEADO)) {
			return true;
		}
		
		return false;
	}
	
	public void bloquear() {
		this.status = StatusCartao.BLOQUEADO;
	}

	public boolean ehAssociado(Carteira carteira) {
		return carteiras.contains(carteira);
	}

	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numero=" + numero + ", titular=" + titular + ", limite=" + limite + "]";
	}

}
