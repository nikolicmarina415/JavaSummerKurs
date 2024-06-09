package com.logate.summer.Services;

import com.logate.summer.Repositories.MovieRepository;
import com.logate.summer.dto.MovieDTO;
import com.logate.summer.filter.MovieFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<MovieDTO> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public MovieDTO getById(Integer id) {
        return movieRepository.getById(id);
    }

    public List<MovieDTO> getByParams(String title, String genre, Integer year) {
        return movieRepository.getByParams(title,genre, year);
    }
    public List<MovieDTO> getByReqMap(Map<String, Object> mapa) {
        String title = (String) mapa.get("title");
        String genre = (String) mapa.get("genre");
        Integer year = (Integer) mapa.get("year");
        return movieRepository.getByReqMap(mapa);
    }
    public List<MovieDTO> getByClass(MovieFilter movieFilter) {
        return movieRepository.getByClass(movieFilter);
    }
}
