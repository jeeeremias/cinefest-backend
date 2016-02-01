package fest.cinefest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import fest.cinefest.model.Usuario;

public interface UsuarioRepository extends Repository<Usuario, String> {
	Page<Usuario> findAll(Pageable pageable);
	Usuario findOne(String email);
	boolean exists(String email);
	Usuario save(Usuario usuario);
}
