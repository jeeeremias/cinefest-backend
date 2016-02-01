package fest.cinefest.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		BufferedImage imagem = ImageIO.read(getClass().getResourceAsStream("/images" + resource));
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ImageIO.write(imagem, "jpg", bao);
		return bao.toByteArray();
	}
}
