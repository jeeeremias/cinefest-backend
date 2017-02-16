package fest.cinefest.domain;

import fest.cinefest.model.Vote;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VoteRepository extends PagingAndSortingRepository<Vote, Integer> {
	Long countByDay(String day);
}
