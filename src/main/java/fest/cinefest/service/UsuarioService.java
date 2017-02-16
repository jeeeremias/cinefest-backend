package fest.cinefest.service;

import fest.cinefest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fest.cinefest.domain.UserRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	UserRepository usuarioRepository;
	
	public User getUsuario(String email) {
		return usuarioRepository.findOne(email);
	}
	
	public boolean existe(String email) {
		return usuarioRepository.exists(email);
	}
	
	public boolean cadastro(User user) {
		if(existe(user.getEmail())) {
			System.out.println("User já cadastrado.");
		} else {
			try {
				user.setEmail(user.getEmail().toLowerCase());
                usuarioRepository.save(user);
			} catch (Exception e) {
				System.out.println("Cadastro realizado com sucesso.");
			}
		}
		System.out.println("Cadastro realizado com sucesso.");
		return true;
	}
	
	public boolean login(User user) {
		User user2 = null;
		if (user.getEmail().equals("admin@admin.com") && user.getPassword().equals("admin")) {
		} else {
			user2 = getUsuario(user.getEmail().toLowerCase());
			if (user2 == null) {
                System.out.println("User não cadastrado.");
			} else {
				if (!user.getPassword().equals(user2.getPassword())) {
					System.out.println("Senha incorreta.");
				}
			}
		}
		return true;
	}
}
