package com.cinefest.rest.facade;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;

@Service
@Transactional
public class PhotoRestFacade {

  public byte[] getPhoto(String resource) throws IOException {
    InputStream imagem = getClass().getResourceAsStream("/images" + resource);
    return IOUtils.toByteArray(imagem);
  }
}
