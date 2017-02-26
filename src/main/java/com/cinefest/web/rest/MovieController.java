package com.cinefest.web.rest;

import java.nio.charset.Charset;
import java.util.List;

import javax.websocket.server.PathParam;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.movie.MovieDTO;
import com.cinefest.pojo.movie.MovieParams;
import com.cinefest.service.MovieService;
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
	
	public static final MediaType MEDIA_TYPE = new MediaType("text", "csv", Charset.forName("utf-8"));

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public List<MovieEntity> getMovies(@RequestParam MovieParams params) {
		return movieService.getAll(params.getOffset(), params.getSize());
	}

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public MovieEntity getMovie(@PathParam("id") int id) {
		return movieService.getOne(id);
	}

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	@ResponseBody
	public MovieEntity uptadeMovie(@PathParam("id") long id, @RequestBody MovieDTO movie) {
		throw new NotImplementedException();
	}

}
