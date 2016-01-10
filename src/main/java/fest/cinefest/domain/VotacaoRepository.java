package fest.cinefest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import fest.cinefest.model.Voto;

public interface VotacaoRepository extends Repository<Voto, String> {
	Page<Voto> findAll(Pageable pageable);
	Voto findOne(String cpf);
	boolean exists(String cpf);
	Voto save(Voto voto);
}
