package com.br.zupacademy.proposta.api.novaproposta;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, Long>{
	
	List<Proposta> findByDocumento(String documento);
}
