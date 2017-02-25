package com.cinefest.web.rest;

import com.cinefest.entity.User;
import com.cinefest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public boolean newUser(@RequestBody @Valid User user) {
        return userService.cadastro(user);
    }

    @RequestMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public boolean login(@RequestBody @Valid User user) {
        return userService.login(user);
    }
}
