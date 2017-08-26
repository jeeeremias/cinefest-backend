package com.cinefest.rest.controller;

import com.cinefest.entity.UserEntity;
import com.cinefest.rest.facade.UserRestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

//@RestController("users")
public class UserController {

  @Autowired
  UserRestFacade userRestFacade;

  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  @ResponseBody
  public boolean newUser(@RequestBody @Valid UserEntity userEntity) {
    return userRestFacade.cadastro(userEntity);
  }

  @RequestMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  @ResponseBody
  public boolean login(@RequestBody @Valid UserEntity userEntity) {
    return userRestFacade.login(userEntity);
  }
}
