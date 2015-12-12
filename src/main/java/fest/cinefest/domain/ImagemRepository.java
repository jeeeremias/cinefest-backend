package fest.cinefest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import fest.cinefest.model.Imagem;

public interface ImagemRepository extends Repository<Imagem, Integer> {
	Page<Imagem> findAll(Pageable pageable);
	Imagem findOne(Integer id);
	Imagem save(Imagem filme);
}
