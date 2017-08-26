package com.cinefest.rest.controller;

import com.cinefest.entity.MovieEntity;
import com.cinefest.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MockController {

  @Autowired
  MockService mockService;

  @RequestMapping(value = "/init", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  @ResponseBody
  public List<MovieEntity> init() throws IOException {

    return mockService.iniciar();
  }
}
