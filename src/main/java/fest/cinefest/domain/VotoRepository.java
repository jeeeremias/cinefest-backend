package fest.cinefest.domain;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import fest.cinefest.model.Voto;

public interface VotoRepository extends Repository<Voto, Integer> {
	List<Voto> findAll(Sort sort);
	Voto findOne(Integer id);
	Long countByDia(String dia);
	Voto save(Voto voto);
	boolean exists(Integer id);
}
