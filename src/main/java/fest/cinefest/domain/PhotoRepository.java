package fest.cinefest.domain;

import fest.cinefest.model.Imagem;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Imagem, Integer> {
}
