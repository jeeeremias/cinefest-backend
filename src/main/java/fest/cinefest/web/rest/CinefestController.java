package fest.cinefest.web.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fest.cinefest.model.Filme;
import fest.cinefest.model.Response;
import fest.cinefest.model.Usuario;
import fest.cinefest.service.FilmeService;
import fest.cinefest.service.ImagemService;
import fest.cinefest.service.UsuarioService;

@RestController
@Scope("request")
public class CinefestController {
	
	@Autowired
	FilmeService filmeService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	ImagemService imagemService;
	
	@CrossOrigin
	@RequestMapping(value = "/filmes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Filme> getFilmes(@RequestParam int pag, @RequestParam int tam) {
		return filmeService.getAll(pag, tam);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/cadastro", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Response cadastro(@RequestBody @Valid Usuario usuario) {
		return usuarioService.cadastro(usuario);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Response login(@RequestBody @Valid Usuario usuario) {
		return usuarioService.login(usuario);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/imagem", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImagem(@RequestParam int id) throws IOException {
		
		return imagemService.getImagem(id);
	}
	
	@CrossOrigin
	@RequestMapping("/mock")
	@ResponseBody
	public void mockFilmes(@RequestParam int qtde) throws SQLException {
		filmeService.mock(qtde);
	}
}
