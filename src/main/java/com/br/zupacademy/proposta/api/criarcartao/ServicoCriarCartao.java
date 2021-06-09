package com.br.zupacademy.proposta.api.criarcartao;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.zupacademy.proposta.api.analiseproposta.AnalisePropostaDTO;

@FeignClient(value = "criarcartao", url = "http://localhost:8888/api/cartoes")
public interface ServicoCriarCartao {
	
	@RequestMapping(method = RequestMethod.POST, value = "/" ,consumes = "application/json")
	CartaoResponseDTO pegarDadosCartao(@RequestBody @Valid AnalisePropostaDTO request);

}
