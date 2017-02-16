package fest.cinefest.domain;

import fest.cinefest.model.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<Usuario, String> {
}
