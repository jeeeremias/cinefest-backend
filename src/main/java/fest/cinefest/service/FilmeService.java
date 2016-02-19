package fest.cinefest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
import fest.cinefest.domain.VotoRepository;
import fest.cinefest.model.Filme;
import fest.cinefest.model.Imagem;

@Service
@Transactional
public class FilmeService {

	@Autowired
	FilmeRepository filmeRespository;
	
	@Autowired
	VotoRepository votoRespository;

	@Autowired
	ImagemRepository imagemRespository;

	public List<Filme> getAll(int pag, int tam) {
		return filmeRespository.findAll(new PageRequest(pag, tam, new Sort(Sort.Direction.ASC, "nome"))).getContent();
	}
	
	public List<Filme> getByDay(String dataExibicao) {
		List<Filme> filmes = filmeRespository.findByDataExibicao(dataExibicao, new Sort(Sort.Direction.ASC, "nome"));
		if (dataExibicao.equals("15/02") || dataExibicao.equals("16/02") || dataExibicao.equals("17/02") || dataExibicao.equals("18/02") || dataExibicao.equals("19/02")) {
			if(filmes != null) {
				filmes.addAll(filmeRespository.findByDataExibicao("15 a 19/02", new Sort(Sort.Direction.ASC, "nome")));
			} else {
				filmes = filmeRespository.findByDataExibicao("15 a 19/02", new Sort(Sort.Direction.ASC, "nome"));
			}
		}
		return filmes;
	}

	public Filme getOne(Integer id) {
		return filmeRespository.findOne(id);
	}

	public boolean existe(Integer id) {
		return filmeRespository.exists(id);
	}
	
	public String votos(String dia) {
		List<Filme> filmes = getByDay(dia);
		StringBuilder sb = new StringBuilder("Codigo, Filme, Votos, (%)\n");
		float total = votoRespository.countByDia(dia);
		
		for (Filme filme : filmes) {
			sb.append(filme.getIdFilme() + ",");
			sb.append(filme.getNome() + ",");
			sb.append(filme.getVotos().size() + ",");
			sb.append(((100.0 * filme.getVotos().size()) / total) + ",");
			sb.append("\n");
		}
		sb.append(",,,\n,,Total Votos," + total + "\n");
		return sb.toString();
	}

	public List<Filme> iniciar() throws IOException {
		String status = "";
		InputStream is = getClass().getResourceAsStream("/filmes.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
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
		return filmes;
	}
}
