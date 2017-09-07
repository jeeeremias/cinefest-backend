package com.cinefest.rest.controller;

import com.cinefest.entity.VoteEntity;
import com.cinefest.rest.params.SearchCriteria;
import com.cinefest.rest.facade.VoteRestFacade;
import com.cinefest.movie.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@RestController("/votes")
public class VoteController {

  @Autowired
  VoteRestFacade voteRestFacade;

  @Autowired
  MovieServiceImpl movieServiceImpl;

  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  @ResponseBody
  public Iterable<VoteEntity> getVotes(@RequestParam SearchCriteria params) {
    return voteRestFacade.getAll(params);
  }

  @RequestMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  @ResponseBody
  public VoteEntity updateVote(@RequestBody VoteEntity voteEntity) {
    return voteRestFacade.save(voteEntity);
  }

  @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  @ResponseBody
  public VoteEntity vote(@RequestBody VoteEntity voteEntity) {
    return voteRestFacade.save(voteEntity);
  }

  @RequestMapping(value = "/file", method = RequestMethod.GET)
  @ResponseBody
  public void votes(@RequestParam String dia, HttpServletResponse response) throws IOException {
    String csvFileName = "relatorio_votos_dia_" + dia + ".csv";
    response.setContentType("text/csv;charset=UTF-8");
    response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", csvFileName));
    System.out.println(response.getCharacterEncoding());
    response.getOutputStream().print(movieServiceImpl.votos(dia));
    response.getOutputStream().flush();
    response.getOutputStream().close();
  }
}
