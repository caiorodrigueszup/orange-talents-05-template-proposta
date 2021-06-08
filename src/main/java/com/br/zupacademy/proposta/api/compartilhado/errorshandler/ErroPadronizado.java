package com.br.zupacademy.proposta.api.compartilhado.errorshandler;

import java.util.Collection;

public class ErroPadronizado {

	private Collection<String> mensagens;

	public ErroPadronizado(Collection<String> mensagens) {
		this.mensagens = mensagens;
	}

	public Collection<String> getMensagens() {
		return mensagens;
	}
}
