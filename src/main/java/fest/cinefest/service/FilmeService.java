package fest.cinefest.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fest.cinefest.domain.FilmeRepository;
import fest.cinefest.domain.ImagemRepository;
import fest.cinefest.model.Filme;
import fest.cinefest.model.Imagem;

@Service
@Transactional
public class FilmeService {

	@Autowired
	FilmeRepository filmeRespository;

	@Autowired
	ImagemRepository imagemRespository;

	public List<Filme> getAll(int pag, int tam) {
		return filmeRespository.findAll(new PageRequest(pag, tam, new Sort(Sort.Direction.ASC, "nome"))).getContent();
	}

	public Filme getOne(Integer id) {
		return filmeRespository.findOne(id);
	}

	public boolean existe(Integer id) {
		return filmeRespository.exists(id);
	}

	public String iniciar() throws IOException {
		String status = "";
		InputStream is = getClass().getResourceAsStream("/filmes.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		List<Filme> filmes = new ArrayList<Filme>();
		List<Imagem> imagens;
		Filme filme;
		String shortDesc;
		while ((line = br.readLine()) != null) {
			String[] filmeString = line.split(Pattern.quote("|"));
			if (filmeString[11].length() >= 150) {
				shortDesc = filmeString[11].substring(0, 147) + "...";
			} else {
				shortDesc = filmeString[11];
			}
			
			filme = new Filme(Integer.parseInt(filmeString[0]), filmeString[1], filmeString[2],
					filmeString[3], filmeString[4], Integer.parseInt(filmeString[5]), filmeString[6], filmeString[7], filmeString[8],
					filmeString[9], filmeString[10], shortDesc, filmeString[11], filmeString[12], filmeString[13]);
			imagens = new ArrayList<Imagem>();
			imagens.add(new Imagem("/" + filmeString[0] + "_1.jpg", true, filme));
			imagens.add(new Imagem("/" + filmeString[0] + "_2.jpg", false, filme));
			imagens.add(new Imagem("/" + filmeString[0] + "_3.jpg", false, filme));
			filme.setImagens(imagens);
			filmes.add(filme);
			status = status.concat("parse filme: " + filmeString[0] + " ;");
		}
		for (Filme filmee : filmes) {
			filmeRespository.save(filmee);
			status = status.concat("Gravou filme: " + filmee.getIdFilme() + " ;");
		}
		return status;
	}
}
