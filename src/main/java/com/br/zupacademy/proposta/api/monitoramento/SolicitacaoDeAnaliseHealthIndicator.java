package com.br.zupacademy.proposta.api.monitoramento;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.br.zupacademy.proposta.api.analiseproposta.ServicoAnaliseProposta;

import feign.FeignException;

@Component(value = "servico_solicitacao_de_analise")
public class SolicitacaoDeAnaliseHealthIndicator implements HealthIndicator {

	@Autowired
	private ServicoAnaliseProposta propostaAnaliseClient;

	@Override
	public Health health() {
		Map<String, String> details = new HashMap<>();
		details.put("servico", "API analise proposta");
		details.put("URL", "http://localhost:9999/api/solicitacao");

		try {
			propostaAnaliseClient.verificaStatus();
			return Health.up().withDetails(details).build();
		} catch (FeignException e) {
			if (e.status() != -1) {
				HttpStatus status = HttpStatus.valueOf(e.status());
				if (status.is4xxClientError() || status.is2xxSuccessful()) {
					return Health.up().withDetails(details).build();
				}
			}

			details.put("motivo", e.getMessage());
			return Health.down().withDetails(details).build();
		} catch (Exception e) {
			details.put("motivo", e.getMessage());
			return Health.down().withDetails(details).build();
		}
	}

}
