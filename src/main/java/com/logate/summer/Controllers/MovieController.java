package com.logate.summer.Controllers;

import com.logate.summer.Services.MovieService;
import com.logate.summer.dto.MovieDTO;
import com.logate.summer.dto.UserDTO;
import com.logate.summer.filter.MovieFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    @Autowired
    MovieService movieDetailsService;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movieDTOList = movieDetailsService.getAllMovies();
        return new ResponseEntity<List<MovieDTO>>(movieDTOList, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") Integer id) {
        MovieDTO movieDTO = movieDetailsService.getById(id);
        if (movieDTO == null) {
            return new ResponseEntity<MovieDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MovieDTO>(movieDTO, HttpStatus.OK);

    }

    @GetMapping("byParam")
    public ResponseEntity<List<MovieDTO>> getByParams(@RequestParam("title") String title,
                                                      @RequestParam("genre") String genre,
                                                      @RequestParam("year") Integer year) {
        List<MovieDTO> movieDTOList = movieDetailsService.getByParams(title, genre, year);
        if (movieDTOList.isEmpty()) {
            return new ResponseEntity<List<MovieDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieDTOList, HttpStatus.OK);
    }

    @GetMapping("/byReqMap")
    public ResponseEntity<List<MovieDTO>> getByReqMap(@RequestParam Map<String, Object> mapa) {
        List<MovieDTO> movieDTOList = movieDetailsService.getByReqMap(mapa);
        if (movieDTOList.isEmpty()) {
            return new ResponseEntity<List<MovieDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<MovieDTO>>(movieDTOList, HttpStatus.OK);
    }

    @GetMapping("byClass")
    public ResponseEntity<List<MovieDTO>> getMoviesByClass(MovieFilter movieFilter) {
        List<MovieDTO> movieDTOList = movieDetailsService.getByClass(movieFilter);
        if (movieDTOList.isEmpty()) {
            return new ResponseEntity<List<MovieDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<MovieDTO>>(movieDTOList, HttpStatus.OK);

    }
}
