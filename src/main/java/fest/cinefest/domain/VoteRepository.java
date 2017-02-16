package fest.cinefest.domain;

import fest.cinefest.model.Voto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VoteRepository extends PagingAndSortingRepository<Voto, Integer> {
	Long countByDay(String day);
}
