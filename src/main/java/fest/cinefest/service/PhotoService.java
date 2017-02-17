package fest.cinefest.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fest.cinefest.domain.PhotoRepository;

@Service
@Transactional
public class PhotoService {

	public byte[] getPhoto(String resource) throws IOException {
		InputStream imagem = getClass().getResourceAsStream("/images" + resource);
		return IOUtils.toByteArray(imagem);
	}
}
