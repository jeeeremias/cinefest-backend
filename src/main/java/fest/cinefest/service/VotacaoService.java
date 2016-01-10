package fest.cinefest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fest.cinefest.domain.VotacaoRepository;
import fest.cinefest.model.Response;
import fest.cinefest.model.Usuario;
import fest.cinefest.model.Voto;

@Service
@Transactional
public class VotacaoService {
	
	@Autowired
	VotacaoRepository votacaoRepository;
	
	public Voto getVoto(String cpf) {
		return votacaoRepository.findOne(cpf);
	}
	
	public boolean existe(String cpf) {
		return votacaoRepository.exists(cpf);
	}
	
	public Response votar(Voto voto) {
		Response response = new Response();
		if(existe(voto.getCpf())) {
			response.setSucesso(false);
			response.setMensagem("Já existe um voto para este CPF.");
		} else {
			response.setSucesso(true);
			response.setMensagem("Cadastro realizado com sucesso.");
			try {
				votacaoRepository.save(voto);
			} catch (Exception e) {
				e.printStackTrace();
				response.setSucesso(false);
				response.setMensagem(e.getLocalizedMessage());
			}
		}
		return response;
	}
}
