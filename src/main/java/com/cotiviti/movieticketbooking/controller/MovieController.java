package com.cotiviti.movieticketbooking.controller;

import com.cotiviti.movieticketbooking.dto.GlobalResponse;
import com.cotiviti.movieticketbooking.dto.movie.MovieAddDto;
import com.cotiviti.movieticketbooking.model.movie.Movie;
import com.cotiviti.movieticketbooking.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<GlobalResponse<Integer>> addMovie(@RequestBody @Valid MovieAddDto movieAddDto) {
        return ResponseEntity.ok(new GlobalResponse<Integer>().successResponse(movieService.create(movieAddDto), "Movie added"));
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<Page<?>>> getMovies() {
        return ResponseEntity.ok(new GlobalResponse<>().successResponse(movieService.getActive(), "Movie get"));
    }
}
