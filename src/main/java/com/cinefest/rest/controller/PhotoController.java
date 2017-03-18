package com.cinefest.rest.controller;

import com.cinefest.rest.service.PhotoRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.io.IOException;

//@RestController("photo")
public class PhotoController {

    @Autowired
    PhotoRestService photoRestService;

    @RequestMapping(value = "/photo/{source}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPhoto(@PathParam("source") String resource) throws IOException {

        return photoRestService.getPhoto(resource);
    }
}
