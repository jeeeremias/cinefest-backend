package fest.cinefest.service;

import java.util.List;

import fest.cinefest.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fest.cinefest.domain.MovieRepository;
import fest.cinefest.domain.VoteRepository;
import fest.cinefest.model.Voto;

@Service
@Transactional
public class VotoService {

	@Autowired
	VoteRepository votoRespository;
	
	@Autowired
    MovieRepository filmeRespository;

	public Voto save(Voto voto) {
		Movie movie = filmeRespository.findOne(voto.getIdFilme());
		voto.setMovie(movie);
		return votoRespository.save(voto);
	}
	
	public List<Voto> getAll(int pag, int tam) {
		return votoRespository.findAll(new Sort(Sort.Direction.ASC, "dia"));
	}
	
	public Long countByDia(String dia) {
		return votoRespository.countByDay(dia);
	}
}
