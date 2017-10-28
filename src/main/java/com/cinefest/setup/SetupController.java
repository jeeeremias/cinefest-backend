package com.cinefest.setup;

import com.cinefest.movie.MovieService;
import com.cinefest.movie.enumeration.MovieType;
import com.cinefest.pojo.MovieVO;
import com.cinefest.pojo.PhotoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.cinefest.rest.PathEndpoints.SETUP;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class SetupController {

  private MovieService movieService;

  public static void main(String[] args) {
    String fileName = "images/1_1.jpg";
    File file = new File(SetupController.class.getClassLoader().getResource(fileName).getFile());
    try {
      BufferedImage image = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Autowired
  public SetupController(MovieService movieService) {
    this.movieService = movieService;
  }

  @RequestMapping(method = GET, value = SETUP, produces = APPLICATION_JSON_VALUE)
  public String getMovies() {
    String fileName = "filmes.csv";
    File file = new File(getClass().getClassLoader().getResource(fileName).getFile());

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

        String imgPath;
        PhotoVO photo;
        List<PhotoVO> photos = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
          imgPath = "images/" + movie[0] + "_" + i + ".jpg";
          URL url = getClass().getClassLoader().getResource(imgPath);
          if (url == null) {
            break;
          }
          photo = new PhotoVO();
          photo.setSource("movies/" + imgPath);
          if (i == 1) {
            photo.setMain(true);
          }
          photos.add(photo);
        }
        vo.setPhotos(photos);
        movieService.create(vo);
      }
      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "Ok";
  }
}
