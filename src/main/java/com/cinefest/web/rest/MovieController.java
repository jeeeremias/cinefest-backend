package com.cinefest.web.rest;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.movie.MovieDTO;
import com.cinefest.pojo.movie.MovieParams;
import com.cinefest.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;

import static com.cinefest.web.rest.MovieController.ENTITY_NAME;

@RestController
class MovieController {

	static final String ENTITY_NAME = "/movies";
	static final String ID_PARAM = "/{id}";

    @Autowired
	MovieService movieService;

	@RequestMapping(method = RequestMethod.GET, value = ENTITY_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<MovieEntity> getMovies(@RequestParam(required = false) MovieParams params) {
		return movieService.getAll(params);
	}

	@RequestMapping(method = RequestMethod.GET, value = ENTITY_NAME + ID_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MovieEntity getMovie(@PathParam("id") int id) {
		return movieService.getOne(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = ENTITY_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void newMovie(@RequestBody MovieDTO movie, HttpServletResponse response, UriComponentsBuilder b) {
		MovieEntity movieEntity = movieService.newMovie(movie);
        UriComponents uriComponents = b.path(ENTITY_NAME + ID_PARAM).buildAndExpand(movieEntity.getId());
		response.addHeader("Location", uriComponents.toUriString());
	}

	@RequestMapping(method = RequestMethod.PUT, value = ENTITY_NAME + ID_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MovieEntity uptadeMovie(@PathParam("id") long id, @RequestBody MovieDTO movie) {
		return movieService.save(null);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = ENTITY_NAME + ID_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MovieEntity deleteMovie(@PathParam("id") long id, @RequestBody MovieDTO movie) {
		throw new NotImplementedException();
	}

}
