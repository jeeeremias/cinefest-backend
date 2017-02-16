package fest.cinefest.service;

import java.util.List;

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
public class VotoService {

	@Autowired
	VoteRepository votoRespository;
	
	@Autowired
    MovieRepository filmeRespository;

	public Vote save(Vote vote) {
		Movie movie = filmeRespository.findOne(vote.getMovieId());
		vote.setMovie(movie);
		return votoRespository.save(vote);
	}
	
	public List<Vote> getAll(int pag, int tam) {
		return votoRespository.findAll(new Sort(Sort.Direction.ASC, "dia"));
	}
	
	public Long countByDia(String dia) {
		return votoRespository.countByDay(dia);
	}
}
