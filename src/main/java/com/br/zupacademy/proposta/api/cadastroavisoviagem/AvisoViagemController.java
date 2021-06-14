package com.br.zupacademy.proposta.api.cadastroavisoviagem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
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

import com.br.zupacademy.proposta.api.criarcartao.Cartao;
import com.br.zupacademy.proposta.api.criarcartao.CartaoRepository;

@RestController
public class AvisoViagemController {

	@Autowired
	private ServicoNotificaSistemaAviso servicoNotificaSistemaAviso;

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private CartaoRepository cartaoRepository;

	@PostMapping("/cartoes/{id}/avisos/viagens")
	@Transactional
	public ResponseEntity<?> avisoViagem(@PathVariable String id, @RequestBody @Valid AvisoViagemForm form,
			HttpServletRequest request) {

		Cartao cartao = cartaoRepository.findByNumero(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado."));

		String ipCliente = request.getRemoteAddr();
		String userAgent = request.getHeader("User-Agent");

		AvisoViagem avisoViagem = form.toModel(cartao, ipCliente, userAgent);
		try {
			servicoNotificaSistemaAviso.notificar(cartao.getNumero(), form);
			manager.persist(avisoViagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		return ResponseEntity.ok().build();
	}
}
