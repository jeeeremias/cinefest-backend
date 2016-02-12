package fest.cinefest.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fest.cinefest.domain.ImagemRepository;

@Service
@Transactional
public class ImagemService {

	@Autowired
	ImagemRepository imagemRespository;
	
	public byte[] getImagem(String resource) throws IOException {
		InputStream imagem = getClass().getResourceAsStream("/images" + resource);
		return IOUtils.toByteArray(imagem);
	}
}
