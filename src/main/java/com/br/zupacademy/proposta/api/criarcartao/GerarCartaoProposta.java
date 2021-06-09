package com.br.zupacademy.proposta.api.criarcartao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.br.zupacademy.proposta.api.analiseproposta.AnalisePropostaDTO;
import com.br.zupacademy.proposta.api.novaproposta.Proposta;
import com.br.zupacademy.proposta.api.novaproposta.PropostaRepository;

import feign.FeignException;

@Component
public class GerarCartaoProposta {

	@Autowired
	private ServicoCriarCartao servicoCriarCartao;

	@Autowired
	private PropostaRepository propostaRepository;
	
	private Logger logger = LoggerFactory.getLogger(GerarCartaoProposta.class);

	@Scheduled(fixedDelayString = "${tempo.para.gerar.cartao}")
	public void pegarDadosCartao() {
		List<Proposta> propostas = propostaRepository.findByStatusElegivelAndPropostaIsNull();

		try {
			propostas.forEach(proposta -> {
				CartaoResponseDTO cartaoResponseDTO = servicoCriarCartao
						.pegarDadosCartao(new AnalisePropostaDTO(proposta));
				Cartao cartao = cartaoResponseDTO.toModel(proposta);
				proposta.adquire(cartao);
				propostaRepository.save(proposta);
			});
		} catch (FeignException e) {
			logger.info("Ocorreu um erro no serviço ao gerar cartao.");
		}
	}
}
