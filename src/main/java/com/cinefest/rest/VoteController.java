package com.cinefest.rest;

import com.cinefest.entity.VoteEntity;
import com.cinefest.pojo.params.QueryParams;
import com.cinefest.service.MovieService;
import com.cinefest.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@RestController("/votes")
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    MovieService movieService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<VoteEntity> getVotes(@RequestParam QueryParams params) {
        return voteService.getAll(params.getPage(), params.getSize());
    }

    @RequestMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public VoteEntity updateVote(@RequestBody VoteEntity voteEntity) {
        return voteService.save(voteEntity);
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public VoteEntity vote(@RequestBody VoteEntity voteEntity) {
        return voteService.save(voteEntity);
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
