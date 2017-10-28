package com.cinefest.setup;

import com.cinefest.movie.MovieService;
import com.cinefest.movie.enumeration.MovieType;
import com.cinefest.pojo.MovieVO;
import com.cinefest.rest.PathEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import static com.cinefest.rest.PathEndpoints.SETUP;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class SetupController {

  private MovieService movieService;

  @Autowired
  public SetupController(MovieService movieService) {
    this.movieService = movieService;
  }

  @RequestMapping(method = GET, value = SETUP, produces = APPLICATION_JSON_VALUE)
  public String getMovies() {
    String fileName = "filmes.csv";
    File file = new File(SetupController.class.getClassLoader().getResource(fileName).getFile());

    try (Scanner scanner = new Scanner(file)) {
      String line;
      String[] movie;
      while (scanner.hasNextLine()) {
        line = scanner.nextLine();
        movie = line.split("\\|");
        MovieVO vo = new MovieVO();
        vo.setType(MovieType.fromDesc(movie[1]));
        vo.setName(movie[2]);
        vo.setCity(movie[3]);
        vo.setState(movie[4]);
        vo.setIncomeDate(LocalDate.ofYearDay(Integer.valueOf(movie[5]), 1));
        vo.setGenre(movie[6]);
        vo.setRuntime(Duration.ofSeconds(Long.valueOf(movie[7])));
        vo.setScreeningDateTime(LocalDateTime.parse(movie[8] + "T" + movie[9]));
        vo.setDirector(movie[10]);
        vo.setSynopsis(movie[11]);
        vo.setDirectorBiography(movie[12]);
        vo.setDirectorEmail(movie[13]);
        movieService.create(vo);
      }
      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "Ok";
  }
}
