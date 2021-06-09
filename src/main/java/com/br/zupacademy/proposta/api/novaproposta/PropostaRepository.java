package com.br.zupacademy.proposta.api.novaproposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

	Optional<Proposta> findByDocumento(String documento);

	@Query(value = "select * from proposta p where p.status = 'ELEGIVEL' and isnull(p.cartao_id)", nativeQuery = true)
	List<Proposta> findByStatusElegivelAndPropostaIsNull();

}
