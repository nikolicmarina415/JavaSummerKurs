package com.logate.summer.Repositories;


import com.logate.summer.dto.MovieDTO;
import com.logate.summer.dto.UserDTO;
import com.logate.summer.filter.MovieFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    public List<MovieDTO> getAllMovies() {
        MovieDTO movie1 = new MovieDTO();
        movie1.setId(1);
        movie1.setTitle("Focus");
        movie1.setYear(2015);
        movie1.setGenre("Comedy, crime and drama");
        movie1.setTicket(6.10);
        movie1.setDescription("The story follows Nicky Spurgeon, a seasoned con artist played by Will Smith," +
                              "who takes an aspiring con artist named Jess Barrett, played by Margot Robbie, under his wing.");

        MovieDTO movie2 = new MovieDTO();
        movie2.setId(2);
        movie2.setTitle("The Devil's Advocate");
        movie2.setYear(1997);
        movie2.setGenre("Thriller");
        movie2.setTicket(8.0);
        movie2.setDescription("The story follows Kevin Lomax, a talented young attorney played by Keanu Reeves," +
                              " who is recruited by a powerful law firm in New York City");


        List<MovieDTO> MovieDTOList =  new ArrayList<>();
        MovieDTOList.add(movie1);
        MovieDTOList.add(movie2);

        return MovieDTOList;
    }

    public MovieDTO getById(Integer id) {
        List<MovieDTO> movieDTOList = getAllMovies();
        for(MovieDTO movieDTO : movieDTOList) {
            if(movieDTO.getId().equals(id)) {
                return movieDTO;
            }
        }
        return null;
    }

    public List<MovieDTO> getByParams(String title, String genre, Integer year) {
        List<MovieDTO> movieDTOList = getAllMovies();
        List<MovieDTO> moviesByParams = new ArrayList<>();

        for (MovieDTO movieDTO : movieDTOList) {
            if ((title == null || movieDTO.getTitle().contains(title)) &&
                    (genre == null || movieDTO.getGenre().contains(genre)) &&
                    (year == null || movieDTO.getYear().equals(year))) {
                moviesByParams.add(movieDTO);
            }
        }

        return moviesByParams;
    }

    public List<MovieDTO> getByReqMap(Map<String, Object> mapa) {
        String title = (String) mapa.get("title");
        String genre = (String) mapa.get("genre");
        Integer year = (Integer) mapa.get("year");
        return getByParams(title, genre, year);
    }

    public List<MovieDTO> getByClass(MovieFilter movieFilter) {
        return getByParams(movieFilter.getTitle(), movieFilter.getGenre(), movieFilter.getYear());
    }

}
