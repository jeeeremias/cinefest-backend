package com.cinefest.service;

import com.cinefest.entity.MovieEntity;
import com.cinefest.entity.PhotoEntity;
import com.cinefest.util.enumeration.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class MockService {

    @Autowired
    MovieService movieService;

    public List<MovieEntity> iniciar() throws IOException {
        String status = "";
        InputStream is = getClass().getResourceAsStream("/filmes.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String line;
        List<MovieEntity> movieEntities = new ArrayList<MovieEntity>();
        List<PhotoEntity> imagens;
        MovieEntity movieEntity;
        String shortDesc;
        while ((line = br.readLine()) != null) {
            String[] filmeString = line.split(Pattern.quote("|"));
            if (filmeString[11].length() >= 150) {
                shortDesc = filmeString[11].substring(0, 147) + "...";
            } else {
                shortDesc = filmeString[11];
            }

            movieEntity = new MovieEntity(Long.valueOf(filmeString[0]), MovieType.fromDesc(filmeString[1]), filmeString[2],
                    filmeString[3], filmeString[4], LocalDate.now(), filmeString[6], filmeString[7], LocalDateTime.now(),
                    filmeString[10], shortDesc, filmeString[11], filmeString[12], filmeString[13]);
            imagens = new ArrayList<PhotoEntity>();
            imagens.add(new PhotoEntity("/" + filmeString[0] + "_1.jpg", true, movieEntity));
            imagens.add(new PhotoEntity("/" + filmeString[0] + "_2.jpg", false, movieEntity));
            imagens.add(new PhotoEntity("/" + filmeString[0] + "_3.jpg", false, movieEntity));
            movieEntity.setPhotos(imagens);
            movieEntities.add(movieEntity);
            status = status.concat("parse movieEntity: " + filmeString[0] + " ;");
        }
        for (MovieEntity filmee : movieEntities) {
            movieService.save(filmee);
            status = status.concat("Gravou movieEntity: " + filmee.getId() + " ;");
        }
        return movieEntities;
    }
}
