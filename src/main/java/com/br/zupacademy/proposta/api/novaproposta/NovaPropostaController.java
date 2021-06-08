package com.br.zupacademy.proposta.api.novaproposta;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.zupacademy.proposta.api.analiseproposta.AnalisePropostaRequest;
import com.br.zupacademy.proposta.api.analiseproposta.PropostaAnaliseClient;
import com.br.zupacademy.proposta.api.analiseproposta.SolicitacaoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;

@RestController
public class NovaPropostaController {

	@Autowired
	private PropostaAnaliseClient propostaAnaliseClient;
	
	@Autowired
	private PropostaRepository repository;
	
	@PostMapping("/propostas")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder uriBuilder) throws JsonMappingException, JsonProcessingException {
		List<Proposta> possivelProposta = repository.findByDocumento(request.getDocumento());
		
		if (possivelProposta.size() > 0) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		Proposta novaProposta = request.toModel();
		
		repository.save(novaProposta);	
		
		AnalisePropostaRequest analisePropostaRequest = new AnalisePropostaRequest(novaProposta);
		
		SolicitacaoResponse resultadoAnalise = null;
		try {
			resultadoAnalise = propostaAnaliseClient.solicita(analisePropostaRequest);
			novaProposta.setStatus(Status.ELEGIVEL);
			
			repository.save(novaProposta);
			
			URI enderecoProposta = uriBuilder.path("/propostas/{id}").build(novaProposta.getId());
			
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.header(HttpHeaders.LOCATION, enderecoProposta.toString()).body(resultadoAnalise);
		} catch (FeignException.UnprocessableEntity e) {
			resultadoAnalise = new ObjectMapper().readValue(e.contentUTF8(), SolicitacaoResponse.class);
			novaProposta.setStatus(Status.NAO_ELEGIVEL);
			repository.save(novaProposta);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(resultadoAnalise);
		} catch (FeignException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Serviço indisponível");
		}
	}
}
