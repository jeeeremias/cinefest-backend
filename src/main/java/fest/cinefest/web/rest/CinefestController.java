package fest.cinefest.web.rest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import fest.cinefest.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fest.cinefest.model.Response;
import fest.cinefest.model.Usuario;
import fest.cinefest.model.Voto;
import fest.cinefest.service.FilmeService;
import fest.cinefest.service.ImagemService;
import fest.cinefest.service.UsuarioService;
import fest.cinefest.service.VotoService;

@RestController
@Scope("request")
public class CinefestController {
	
	@Autowired
	FilmeService filmeService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	ImagemService imagemService;
	
	@Autowired
	VotoService votoService;
	
	public static final MediaType MEDIA_TYPE = new MediaType("text", "csv", Charset.forName("utf-8"));
	
	@RequestMapping(value = "/filmes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Movie> getFilmes(@RequestParam int pag, @RequestParam int tam) {
		return filmeService.getAll(pag, tam);
	}
	
	@RequestMapping(value = "/filmes/dia", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Movie> getFilmes(@RequestParam String dia) {
		return filmeService.getByDay(dia);
	}
	
	@RequestMapping(value = "/votar", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Voto votar(@RequestBody Voto voto) {
		return votoService.save(voto);
	}
	
	@RequestMapping(value = "/votos")
	@ResponseBody
	public void votos(@RequestParam String dia, HttpServletResponse response) throws IOException {
		String csvFileName = "relatorio_votos_dia_" + dia + ".csv";
        response.setContentType("text/csv;charset=UTF-8");
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", csvFileName));
        System.out.println(response.getCharacterEncoding());
		response.getOutputStream().print(filmeService.votos(dia));
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/filme", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Movie getFilme(@RequestParam int id) {
		return filmeService.getOne(id);
	}
	
	@RequestMapping(value = "/cadastro", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Response cadastro(@RequestBody @Valid Usuario usuario) {
		return usuarioService.cadastro(usuario);
	}
	
	@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Response login(@RequestBody @Valid Usuario usuario) {
		return usuarioService.login(usuario);
	}
	
	@RequestMapping(value = "/imagem", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImagem(@RequestParam String resource) throws IOException {
		
		return imagemService.getImagem(resource);
	}
	
	@RequestMapping(value = "/iniciar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Movie> iniciar() throws IOException {
		
		return filmeService.iniciar();
	}
}
