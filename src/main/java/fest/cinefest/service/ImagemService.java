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
public class ImagemService {

	@Autowired
	ImagemRepository imagemRespository;
	
	@Transactional
	public byte[] getImagem(int id) throws IOException {
		BufferedImage imagem = ImageIO.read(getClass().getResourceAsStream("/imagens/imagem" + id + ".jpg"));
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ImageIO.write(imagem, "jpg", bao);
		return bao.toByteArray();
	}
}
