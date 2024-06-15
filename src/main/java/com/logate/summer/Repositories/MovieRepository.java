
package com.logate.summer.Repositories;

import com.logate.summer.dto.MovieDTO;
import com.logate.summer.filter.MovieFilter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    public List<MovieDTO> getAll() {
        MovieDTO movie1 = new MovieDTO(
                1,
                "The Shawshank Redemption",
                1994,
                7.99,
                "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion."
        );
        MovieDTO movie2 = new MovieDTO();
        movie2.setId(2);
        movie2.setTitle("The Godfather");
        movie2.setYear(1972);
        movie2.setTicket(10.49);
        movie2.setDescription("Don Vito Corleone, head of a mafia family, decides to hand over his empire to his youngest son Michael. " +
                "However, his decision unintentionally puts the lives of his loved ones in grave danger.");

        return List.of(movie1, movie2);
    }

    public MovieDTO getById(int id) {

        for (MovieDTO movieDTO : getAll()) {
            if (movieDTO.getId().equals(id)) return movieDTO;
        }
        return null;
    }

    public List<MovieDTO> getByYearRange(int fromYear, int toYear) {
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (MovieDTO movieDTO : getAll()) {
            if (movieDTO.getYear() >= fromYear && movieDTO.getYear() < toYear) movieDTOList.add(movieDTO);
        }
        return movieDTOList;
    }

    public List<MovieDTO> getByYearAndPrice(Map<String, Object> params) {

        List<MovieDTO> movieDTOList = new ArrayList<>();

        Integer fromYear = Integer.parseInt((String) params.get("fromYear"));
        Integer toYear = Integer.parseInt((String) params.get("toYear"));
        String withTitle = (String) params.get("withTitle");

        for (MovieDTO movieDTO : getAll()) {
            if (movieDTO.getYear() >= fromYear && movieDTO.getYear() < toYear && movieDTO.getTitle().contains(withTitle))
                movieDTOList.add(movieDTO);
        }

        return movieDTOList;
    }

    public List<MovieDTO> getByParamsClass(MovieFilter movieFilter) {

        List<MovieDTO> movieDTOList = new ArrayList<>();

        Integer fromYear = movieFilter.getFromYear();
        Integer toYear = movieFilter.getToYear();
        String withTitle = movieFilter.getWithTitle();

        for (MovieDTO movieDTO : getAll()) {
            if (movieDTO.getYear() >= fromYear && movieDTO.getYear() < toYear && movieDTO.getTitle().contains(withTitle))
                movieDTOList.add(movieDTO);
        }

        return movieDTOList;
    }
}
