package com.cinefest.web.rest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.cinefest.entity.Movie;
import com.cinefest.entity.User;
import com.cinefest.entity.Vote;
import com.cinefest.pojo.movie.MovieDTO;
import com.cinefest.pojo.movie.MovieParams;
import com.cinefest.service.MovieService;
import com.cinefest.service.UserService;
import com.cinefest.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RestController("movies")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	VoteService voteService;
	
	public static final MediaType MEDIA_TYPE = new MediaType("text", "csv", Charset.forName("utf-8"));
	
	@RequestMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Movie> getMovies(@RequestParam MovieParams params) {
		return movieService.getAll(params.getOffset(), params.getSize());
	}

	@RequestMapping(value = "/movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	@ResponseBody
	public List<Movie> uptadeMovie(@PathParam("id") long id, @RequestBody MovieDTO movie) {
		throw new NotImplementedException();
	}
	
	@RequestMapping(value = "/vote", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	@ResponseBody
	public Vote vote(@RequestBody Vote vote) {
		return voteService.save(vote);
	}
	
	@RequestMapping(value = "/votes")
	@ResponseBody
	public void votes(@RequestParam String dia, HttpServletResponse response) throws IOException {
		String csvFileName = "relatorio_votos_dia_" + dia + ".csv";
        response.setContentType("text/csv;charset=UTF-8");
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", csvFileName));
        System.out.println(response.getCharacterEncoding());
		response.getOutputStream().print(movieService.votos(dia));
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/movie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Movie getMovie(@PathParam("id") int id) {
		return movieService.getOne(id);
	}
	
	@RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public boolean register(@RequestBody @Valid User user) {
		return userService.cadastro(user);
	}
	
	@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public boolean login(@RequestBody @Valid User user) {
		return userService.login(user);
	}
	
	@RequestMapping(value = "/init", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Movie> init() throws IOException {
		
		return movieService.iniciar();
	}
}
