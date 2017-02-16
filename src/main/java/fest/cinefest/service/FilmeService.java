package fest.cinefest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import fest.cinefest.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fest.cinefest.domain.MovieRepository;
import fest.cinefest.domain.PhotoRepository;
import fest.cinefest.domain.VoteRepository;
import fest.cinefest.model.Imagem;

@Service
@Transactional
public class FilmeService {

	@Autowired
	MovieRepository filmeRespository;
	
	@Autowired
	VoteRepository votoRespository;

	@Autowired
	PhotoRepository imagemRespository;

	public List<Movie> getAll(int pag, int tam) {
		return filmeRespository.findAll(new PageRequest(pag, tam, new Sort(Sort.Direction.ASC, "nome"))).getContent();
	}
	
	public List<Movie> getByDay(String dataExibicao) {
		List<Movie> movies = filmeRespository.findByScreeningDate(dataExibicao, new Sort(Sort.Direction.ASC, "nome"));
		if (dataExibicao.equals("15/02") || dataExibicao.equals("16/02") || dataExibicao.equals("17/02") || dataExibicao.equals("18/02") || dataExibicao.equals("19/02")) {
			if(movies != null) {
				movies.addAll(filmeRespository.findByScreeningDate("15 a 19/02", new Sort(Sort.Direction.ASC, "nome")));
			} else {
				movies = filmeRespository.findByScreeningDate("15 a 19/02", new Sort(Sort.Direction.ASC, "nome"));
			}
		}
		return movies;
	}

	public Movie getOne(Integer id) {
		return filmeRespository.findOne(id);
	}

	public boolean existe(Integer id) {
		return filmeRespository.exists(id);
	}
	
	public String votos(String dia) {
		List<Movie> movies = getByDay(dia);
		StringBuilder sb = new StringBuilder("Codigo, Movie, Votos, (%)\n");
		float total = votoRespository.countByDay(dia);
		
		for (Movie movie : movies) {
			sb.append(movie.getId() + ",");
			sb.append(movie.getNome() + ",");
			sb.append(movie.getVotes().size() + ",");
			sb.append(((100.0 * movie.getVotes().size()) / total) + ",");
			sb.append("\n");
		}
		sb.append(",,,\n,,Total Votos," + total + "\n");
		return sb.toString();
	}

	public List<Movie> iniciar() throws IOException {
		String status = "";
		InputStream is = getClass().getResourceAsStream("/filmes.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
		String line;
		List<Movie> movies = new ArrayList<Movie>();
		List<Imagem> imagens;
		Movie movie;
		String shortDesc;
		while ((line = br.readLine()) != null) {
			String[] filmeString = line.split(Pattern.quote("|"));
			if (filmeString[11].length() >= 150) {
				shortDesc = filmeString[11].substring(0, 147) + "...";
			} else {
				shortDesc = filmeString[11];
			}
			
			movie = new Movie(Integer.parseInt(filmeString[0]), filmeString[1], filmeString[2],
					filmeString[3], filmeString[4], Integer.parseInt(filmeString[5]), filmeString[6], filmeString[7], filmeString[8],
					filmeString[9], filmeString[10], shortDesc, filmeString[11], filmeString[12], filmeString[13]);
			imagens = new ArrayList<Imagem>();
			imagens.add(new Imagem("/" + filmeString[0] + "_1.jpg", true, movie));
			imagens.add(new Imagem("/" + filmeString[0] + "_2.jpg", false, movie));
			imagens.add(new Imagem("/" + filmeString[0] + "_3.jpg", false, movie));
			movie.setPhotos(imagens);
			movies.add(movie);
			status = status.concat("parse movie: " + filmeString[0] + " ;");
		}
		for (Movie filmee : movies) {
			filmeRespository.save(filmee);
			status = status.concat("Gravou movie: " + filmee.getId() + " ;");
		}
		return movies;
	}
}
