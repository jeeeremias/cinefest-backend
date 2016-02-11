package fest.cinefest.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import fest.cinefest.model.Filme;

public interface FilmeRepository extends Repository<Filme, Integer> {
	Page<Filme> findAll(Pageable pageable);
	List<Filme> findByDataExibicao(String dataExibicao, Sort sort);
	Filme findOne(Integer id);
	Filme save(Filme filme);
	boolean exists(Integer id);
}
