package fest.cinefest.service;

import fest.cinefest.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fest.cinefest.domain.MovieRepository;
import fest.cinefest.domain.VoteRepository;
import fest.cinefest.model.Vote;

@Service
@Transactional
public class VoteService {

	@Autowired
	VoteRepository voteRespository;
	
	@Autowired
    MovieRepository movieRespository;

	public Vote save(Vote vote) {
		Movie movie = movieRespository.findOne(vote.getMovieId());
		vote.setMovie(movie);
		return voteRespository.save(vote);
	}
	
	public Iterable<Vote> getAll(int offset, int size) {
		return voteRespository.findAll(new Sort(Sort.Direction.ASC, "day"));
	}
	
	public Long countByDia(String day) {
		return voteRespository.countByDay(day);
	}
}
