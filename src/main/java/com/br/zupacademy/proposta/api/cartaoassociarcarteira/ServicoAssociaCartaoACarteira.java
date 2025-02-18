package com.br.zupacademy.proposta.api.cartaoassociarcarteira;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "api-associa-cartao-em-carteira", url = "${servico.cartoes}")
public interface ServicoAssociaCartaoACarteira {
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/carteiras", consumes = "application/json", produces = "application/json")
	public String associar(@PathVariable(value = "id") String id, @RequestBody @Valid AssociarCarteiraForm form);
}

