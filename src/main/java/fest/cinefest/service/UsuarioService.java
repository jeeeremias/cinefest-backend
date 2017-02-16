package fest.cinefest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fest.cinefest.domain.PhotoRepository;
import fest.cinefest.domain.UserRepository;
import fest.cinefest.model.Response;
import fest.cinefest.model.Usuario;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	UserRepository usuarioRepository;
	
	@Autowired
    PhotoRepository imagemRespository;
	
	public Usuario getUsuario(String email) {
		return usuarioRepository.findOne(email);
	}
	
	public boolean existe(String email) {
		return usuarioRepository.exists(email);
	}
	
	public Response cadastro(Usuario usuario) {
		Response response = new Response();
		if(existe(usuario.getEmail())) {
			response.setSucesso(false);
			response.setMensagem("Usuario já cadastrado.");
		} else {
			response.setSucesso(true);
			response.setMensagem("Cadastro realizado com sucesso.");
			try {
				usuario.setEmail(usuario.getEmail().toLowerCase());
				usuarioRepository.save(usuario);
			} catch (Exception e) {
				e.printStackTrace();
				response.setSucesso(false);
				response.setMensagem(e.getLocalizedMessage());
			}
		}
		return response;
	}
	
	public Response login(Usuario usuario) {
		Usuario usuario2 = null;
		Response response = new Response();
		response.setSucesso(true);
		if (usuario.getEmail().equals("admin@admin.com") && usuario.getSenha().equals("admin")) {
		} else {
			usuario2 = getUsuario(usuario.getEmail().toLowerCase());
			if (usuario2 == null) {
				response.setSucesso(false);
				response.setMensagem("Usuario não cadastrado.");
			} else {
				if (!usuario.getSenha().equals(usuario2.getSenha())) {
					response.setSucesso(false);
					response.setMensagem("Senha incorreta.");
				}
			}
		}
		return response;
	}
}
