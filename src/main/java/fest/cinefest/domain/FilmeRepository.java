package fest.cinefest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import fest.cinefest.model.Filme;

public interface FilmeRepository extends Repository<Filme, Integer> {
	Page<Filme> findAll(Pageable pageable);
	Filme save(Filme filme);
}
