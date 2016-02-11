package fest.cinefest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fest.cinefest.domain.VotoRepository;
import fest.cinefest.model.Voto;

@Service
@Transactional
public class VotoService {

	@Autowired
	VotoRepository votoRespository;

	public Voto save(Voto voto) {
		return votoRespository.save(voto);
	}
	
	public List<Voto> getAll(int pag, int tam) {
		return votoRespository.findAll(new Sort(Sort.Direction.ASC, "dia"));
	}
}
