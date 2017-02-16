package fest.cinefest.domain;

import fest.cinefest.model.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer> {
	List<Movie> findByScreeningDate(String screeningDate, Sort sort);
}
