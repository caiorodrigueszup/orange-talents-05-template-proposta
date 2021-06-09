package com.br.zupacademy.proposta.api.detalheproposta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.br.zupacademy.proposta.api.novaproposta.Proposta;
import com.br.zupacademy.proposta.api.novaproposta.PropostaRepository;

@RestController
public class DetalhePropostaController {

	@Autowired
	private PropostaRepository propostaRepository;

	@GetMapping("/propostas/{id}")
	public ResponseEntity<?> detalhe(@PathVariable Long id) {
		Optional<Proposta> possivelProposta = propostaRepository.findById(id);

		if (possivelProposta.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada");
		}

		return ResponseEntity.ok(new PropostaResponse(possivelProposta.get()));
	}
}
