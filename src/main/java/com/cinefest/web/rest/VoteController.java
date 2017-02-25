package com.cinefest.web.rest;

import com.cinefest.entity.Vote;
import com.cinefest.pojo.vote.VoteParams;
import com.cinefest.service.MovieService;
import com.cinefest.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController("/votes")
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    MovieService movieService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Vote> getVotes(@RequestParam VoteParams params) {
        return voteService.getAll(params.getOffset(), params.getSize());
    }

    @RequestMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public Vote updateVote(@RequestBody Vote vote) {
        return voteService.save(vote);
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public Vote vote(@RequestBody Vote vote) {
        return voteService.save(vote);
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
