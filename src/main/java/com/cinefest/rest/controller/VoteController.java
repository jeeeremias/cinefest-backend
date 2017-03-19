package com.cinefest.rest.controller;

import com.cinefest.entity.VoteEntity;
import com.cinefest.pojo.params.QueryParams;
import com.cinefest.rest.service.VoteRestService;
import com.cinefest.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@RestController("/votes")
public class VoteController {

    @Autowired
    VoteRestService voteRestService;

    @Autowired
    MovieService movieService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<VoteEntity> getVotes(@RequestParam QueryParams params) {
        return voteRestService.getAll(params);
    }

    @RequestMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public VoteEntity updateVote(@RequestBody VoteEntity voteEntity) {
        return voteRestService.save(voteEntity);
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public VoteEntity vote(@RequestBody VoteEntity voteEntity) {
        return voteRestService.save(voteEntity);
    }

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    @ResponseBody
    public void votes(@RequestParam String dia, HttpServletResponse response) throws IOException {
        String csvFileName = "relatorio_votos_dia_" + dia + ".csv";
        response.setContentType("text/csv;charset=UTF-8");
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", csvFileName));
        System.out.println(response.getCharacterEncoding());
        response.getOutputStream().print(movieService.votos(dia));
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
