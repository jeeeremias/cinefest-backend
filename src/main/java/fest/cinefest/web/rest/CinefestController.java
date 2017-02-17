package fest.cinefest.web.rest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import fest.cinefest.model.Movie;
import fest.cinefest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fest.cinefest.model.Vote;
import fest.cinefest.service.MovieService;
import fest.cinefest.service.PhotoService;
import fest.cinefest.service.UserService;
import fest.cinefest.service.VoteService;

@RestController
public class CinefestController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PhotoService photoService;
	
	@Autowired
	VoteService voteService;
	
	public static final MediaType MEDIA_TYPE = new MediaType("text", "csv", Charset.forName("utf-8"));
	
	@RequestMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Movie> getMovies(@RequestParam int offset, @RequestParam int size) {
		return movieService.getAll(offset, size);
	}
	
	@RequestMapping(value = "/movies/day/{day}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Movie> getMovies(@PathParam("day") String day) {
		return movieService.getByDay(day);
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
	
	@RequestMapping(value = "/photo/{source}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(@PathParam("source") String resource) throws IOException {
		
		return photoService.getPhoto(resource);
	}
	
	@RequestMapping(value = "/init", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Movie> init() throws IOException {
		
		return movieService.iniciar();
	}
}
