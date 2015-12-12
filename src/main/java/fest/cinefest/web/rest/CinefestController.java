package fest.cinefest.web.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fest.cinefest.model.Filme;
import fest.cinefest.service.FilmeService;
import fest.cinefest.service.ImagemService;

@RestController
@Scope("request")
public class CinefestController {
	
	@Autowired
	FilmeService filmeService;
	
	@Autowired
	ImagemService imagemService;
	
	@RequestMapping(value = "/filmes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Filme> getFilmes(@RequestParam int pag, @RequestParam int tam) throws SQLException {
		return filmeService.getAll(pag, tam);
	}
	
	@RequestMapping(value = "/imagem", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImagem(@RequestParam int id) throws IOException {
		
		return imagemService.getImagem(id);
	}
	
	@RequestMapping("/mock")
	@ResponseBody
	public void mockFilmes(@RequestParam int qtde) throws SQLException {
		filmeService.mock(qtde);
	}
	
//	@RequestMapping("/filme")
//	@ResponseBody
//	public Filme getFilme(@RequestParam int id) throws SQLException {
//		
//		return filmesService.getFilme(id);
//	}
//	
//	@RequestMapping(value = "/imagem", produces = MediaType.IMAGE_JPEG_VALUE)
//	@ResponseBody
//	public byte[] getImagem(@RequestParam int id) throws IOException {
//		
//		return filmesService.getImagem(id);
//	}
//	
//	@RequestMapping("/mock-database")
//	@ResponseBody
//	public void mockDatabase(@RequestParam int qtde) throws SQLException {
//		
//		filmesService.mockDataBase(qtde);
//	}
//	
//	@RequestMapping("/clean-database")
//	@ResponseBody
//	public void cleanDatabase() throws SQLException {
//		
//		filmesService.cleanDatabase();
//	}
}
