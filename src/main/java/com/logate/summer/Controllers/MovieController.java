package com.logate.summer.Controllers;

import com.logate.summer.Services.MovieService;
import com.logate.summer.dto.MovieDTO;
import com.logate.summer.filter.MovieFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

//    public MovieController(MovieService movieService) {
//        this.movieService = movieService;
//    }

    @RequestMapping(path = "api/movies", method = RequestMethod.GET)
    public ResponseEntity<List<MovieDTO>> getAll() {
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
        //return ResponseEntity.ok(movieService.getAll());
    }

    @RequestMapping(path = "api/movies/{id}", method = RequestMethod.GET)
    public ResponseEntity<MovieDTO> getById(@PathVariable(value = "id") Integer id) {
        MovieDTO movieDTO = movieService.getById(id);
        if (movieDTO != null) {
            return new ResponseEntity<>(movieDTO,HttpStatus.OK);
//           return ResponseEntity.ok(movieDTO);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//        return ResponseEntity.notFound().build();
    }

    @RequestMapping(path = "api/movies-by-params", method = RequestMethod.GET)
    public ResponseEntity<List<MovieDTO>> getByYearRange(@RequestParam(value = "fromYear") Integer fromYear,
                                                         @RequestParam(value = "toYear") Integer toYear) {
        return new ResponseEntity<>(movieService.getByYearRange(fromYear, toYear), HttpStatus.OK);
    }

    @RequestMapping(path = "api/movies-by-params-map", method = RequestMethod.GET)
    public ResponseEntity<List<MovieDTO>> getByYearAndPrice(@RequestParam Map<String, Object> params) {
        return new ResponseEntity<>(movieService.getByParamMap(params), HttpStatus.OK);
    }

    @RequestMapping(path = "api/movies-by-params-class", method = RequestMethod.GET)
    public ResponseEntity<List<MovieDTO>> getByParamsClass(MovieFilter movieFilter) {
        return new ResponseEntity<>(movieService.getByParamsClass(movieFilter), HttpStatus.OK);
    }
}