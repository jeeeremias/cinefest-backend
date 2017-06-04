package com.cinefest.rest.controller;

import com.cinefest.entity.MovieEntity;
import com.cinefest.pojo.dto.MovieDTO;
import com.cinefest.rest.facade.MovieRestFacade;
import com.cinefest.service.MovieService;
import com.cinefest.util.converter.MovieConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.Map;

import static com.cinefest.util.EndpointsUrl.MOVIES;
import static com.cinefest.util.EndpointsUrl.MOVIE_BY_ID;

@RestController
class MovieController {

    @Autowired
	MovieRestFacade movieRestFacade;

    @Autowired
	MovieService movieService;

	@RequestMapping(method = RequestMethod.GET, value = MOVIES, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<MovieDTO> getMovies(@RequestParam(required = false) Map<String, String> params) {
		return movieRestFacade.getAll(params);
	}

	@RequestMapping(method = RequestMethod.GET, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MovieDTO getMovie(@PathParam("id") long id) {
		return movieRestFacade.getOne(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = MOVIES, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void newMovie(@RequestBody MovieDTO movie, HttpServletResponse response, UriComponentsBuilder b) {
		MovieEntity movieEntity = movieService.newMovie(MovieConverter.dtoToVO(movie));
        UriComponents uriComponents = b.path(MOVIE_BY_ID).buildAndExpand(movieEntity.getId());
		response.addHeader("Location", uriComponents.toUriString());
	}

	@RequestMapping(method = RequestMethod.PUT, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MovieEntity uptadeMovie(@PathParam("id") long id, @RequestBody MovieDTO movie) {
		return movieService.save(null);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = MOVIE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MovieEntity deleteMovie(@PathParam("id") long id, @RequestBody MovieDTO movie) {
		throw new NotImplementedException();
	}

}
