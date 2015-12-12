package fest.cinefest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fest.cinefest.domain.FilmeRepository;
import fest.cinefest.domain.ImagemRepository;
import fest.cinefest.mock.FilmesMock;
import fest.cinefest.model.Filme;
import fest.cinefest.model.Imagem;

@Service
public class FilmeService {
	
	@Autowired
	FilmeRepository filmeRespository;
	
	@Autowired
	ImagemRepository imagemRespository;
	
	public List<Filme> getAll(int pag, int tam) {
		return filmeRespository.findAll(new PageRequest(pag, tam)).getContent();
	}
	
	public void mock(int qtde) {
		Filme filme;
		Imagem imagem;
		for (int i = 0, d = 0; i < qtde; i ++, d ++) {
			if (d == 3) {
				d = 0;
			}
			filme = new Filme(FilmesMock.nomes[d], FilmesMock.descricoes[d], FilmesMock.autores[d], "14:47");
			filme.setImagens(new ArrayList<Imagem>());
			for (int e = 0; e <= 3; e ++) {
				imagem = new Imagem();
				if (e == 0) {
					imagem.setCapa(true);
				}
				imagem.setResource(String.valueOf(FilmesMock.imagens[d][e]));
				imagem.setFilme(filme);
				filme.addImagem(imagem);
			}
			filme = filmeRespository.save(filme);
		}
	}
}
