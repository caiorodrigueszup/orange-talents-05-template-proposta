package com.br.zupacademy.proposta.api.cartaoassociarcarteira;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.zupacademy.proposta.api.criarcartao.Cartao;
import com.br.zupacademy.proposta.api.criarcartao.CartaoRepository;

import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
public class AssociarCarteiraController {
	
	private final Tracer tracer;
	
	public AssociarCarteiraController(Tracer tracer) {
		this.tracer = tracer;
	}

	@Autowired
	private ServicoAssociaCartaoACarteira servicoAssociaCartaoACarteira;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private EntityManager manager;

	@PostMapping("/cartoes/{id}/carteiras")
	@Transactional
	public ResponseEntity<?> associarCarteira(@PathVariable String id, @RequestBody 
			@Valid AssociarCarteiraForm form, UriComponentsBuilder uriBuilder) {
		
		Span activeSpan = tracer.activeSpan();
		
		Cartao cartao = cartaoRepository.findByNumero(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado."));

		activeSpan.setTag("titular.cartao", cartao.getTitular());
		
		Carteira carteira = form.toModel(cartao);
		
		if (cartao.ehAssociado(carteira)) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Carteira já está associada ao cartão");
		}
		
		try {
			servicoAssociaCartaoACarteira.associar(cartao.getNumero(), form);
			
			manager.persist(carteira);
			
			activeSpan.log("Carteira associada com " + form.getCarteira());
			
			URI uri = uriBuilder.path("/cartoes/{numero}/carteiras/{id}").build(id, carteira.getId());
			
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível associar carteira. " );
		}
	}
}
