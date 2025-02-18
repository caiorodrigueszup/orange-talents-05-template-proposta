package com.br.zupacademy.proposta.api.novaproposta;

import java.net.URI;
import java.util.Optional;

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

import com.br.zupacademy.proposta.api.analiseproposta.AnalisePropostaDTO;
import com.br.zupacademy.proposta.api.analiseproposta.ResultadoAnalisePropostaDTO;
import com.br.zupacademy.proposta.api.analiseproposta.ServicoAnaliseProposta;
import com.br.zupacademy.proposta.api.utils.CriptografarDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
public class NovaPropostaController {
	
	private Tracer tracer;
	
	public NovaPropostaController(Tracer tracer) {
		this.tracer = tracer;
	}

	@Autowired
	private ServicoAnaliseProposta servicoAnaliseProposta;

	@Autowired
	private PropostaRepository repository;

	@PostMapping("/propostas")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaPropostaRequest request, 
			UriComponentsBuilder uriBuilder)
			throws JsonMappingException, JsonProcessingException {
		
		Span activeSpan = tracer.activeSpan();
		
		String documentoCriptografado = CriptografarDados.criptografaTexto(request.getDocumento());
		
		Optional<Proposta> possivelProposta = repository.findByDocumento(documentoCriptografado);

		if (possivelProposta.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		
		Proposta novaProposta = request.toModel(documentoCriptografado);

		repository.save(novaProposta);
		
		activeSpan.setBaggageItem("user.email", novaProposta.getEmail());
		
		AnalisePropostaDTO analisePropostaDto = new AnalisePropostaDTO(novaProposta);

		ResultadoAnalisePropostaDTO resultadoAnalisePropostaDto = null;
		try {
			resultadoAnalisePropostaDto = servicoAnaliseProposta.realiza(analisePropostaDto);
			novaProposta.situacao(Status.ELEGIVEL);

			repository.save(novaProposta);

			URI enderecoProposta = uriBuilder.path("/propostas/{id}").build(novaProposta.getId());

			return ResponseEntity.status(HttpStatus.ACCEPTED).header(HttpHeaders.LOCATION, enderecoProposta.toString())
					.body(resultadoAnalisePropostaDto);

		} catch (FeignException.UnprocessableEntity e) {
			resultadoAnalisePropostaDto = new ObjectMapper().readValue(e.contentUTF8(),
					ResultadoAnalisePropostaDTO.class);
			novaProposta.situacao(Status.NAO_ELEGIVEL);
			repository.save(novaProposta);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(resultadoAnalisePropostaDto);
		} catch (FeignException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Serviço indisponível");
		}
	}
}
