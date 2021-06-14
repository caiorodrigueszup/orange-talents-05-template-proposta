package com.br.zupacademy.proposta.api.cadastroavisoviagem;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "notifica-sistema-aviso", url = "http://localhost:8888")
public interface ServicoNotificaSistemaAviso {
	
	@RequestMapping(method = RequestMethod.POST,consumes = "application/json", 
			produces = "application/json", value = "/api/cartoes/{id}/avisos")
	String notificar(@PathVariable(name = "id") String id, @RequestBody @Valid AvisoViagemForm form);
}


