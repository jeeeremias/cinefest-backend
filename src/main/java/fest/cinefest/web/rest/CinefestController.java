package fest.cinefest.web.rest;

import java.io.IOException;
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
	
	@CrossOrigin
	@RequestMapping(value = "/filmes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Filme> getFilmes(@RequestParam int pag, @RequestParam int tam) {
		return filmeService.getAll(pag, tam);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/filmes/dia", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Filme> getFilmes(@RequestParam String dia) {
		return filmeService.getByDay(dia);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/votar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Voto votar(@RequestBody Voto voto) {
		return votoService.save(voto);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/filme", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Filme getFilme(@RequestParam int id) {
		return filmeService.getOne(id);
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
	public byte[] getImagem(@RequestParam String resource) throws IOException {
		
		return imagemService.getImagem(resource);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/iniciar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Filme> iniciar() throws IOException {
		
		return filmeService.iniciar();
	}
}
