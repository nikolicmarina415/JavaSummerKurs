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
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> getAll() {
        return movieRepository.getAll();
    }

    public MovieDTO getById(int id) {
        return movieRepository.getById(id);
    }

    public List<MovieDTO> getByYearRange(int fromYear, int toYear) {
        return movieRepository.getByYearRange(fromYear, toYear);
    }

    public List<MovieDTO> getByParamMap(Map<String, Object> params) {
        return movieRepository.getByYearAndPrice(params);
    }

    public List<MovieDTO> getByParamsClass(MovieFilter movieFilter) {
        return movieRepository.getByParamsClass(movieFilter);
    }
}