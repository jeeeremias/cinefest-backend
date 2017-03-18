package com.cinefest.rest.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhotoRestService {

	public byte[] getPhoto(String resource) throws IOException {
		InputStream imagem = getClass().getResourceAsStream("/images" + resource);
		return IOUtils.toByteArray(imagem);
	}
}
