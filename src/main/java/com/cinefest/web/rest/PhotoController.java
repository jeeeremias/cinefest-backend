package com.cinefest.web.rest;

import com.cinefest.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;

//@RestController("photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @RequestMapping(value = "/photo/{source}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPhoto(@PathParam("source") String resource) throws IOException {

        return photoService.getPhoto(resource);
    }
}
