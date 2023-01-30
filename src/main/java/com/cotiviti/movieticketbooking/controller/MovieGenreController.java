package com.cotiviti.movieticketbooking.controller;

import com.cotiviti.movieticketbooking.dto.GlobalResponse;
import com.cotiviti.movieticketbooking.dto.movie.MovieGenreAddDto;
import com.cotiviti.movieticketbooking.dto.movie.MovieGenreResponseDto;
import com.cotiviti.movieticketbooking.model.movie.MovieGenre;
import com.cotiviti.movieticketbooking.service.MovieGenreService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movie-genre")
@RequiredArgsConstructor
public class MovieGenreController {

    private final ObjectMapper objectMapper;
    private final MovieGenreService movieGenreService;

    @PostMapping
    public ResponseEntity<GlobalResponse<Integer>> addMovieGenre(@RequestBody @Valid MovieGenreAddDto movieGenreAddDto) {
        return ResponseEntity.ok(new GlobalResponse<Integer>().successResponse(movieGenreService
                .create(objectMapper.convertValue(movieGenreAddDto, MovieGenre.class)).getId(), "Movie Genre added"));
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<MovieGenreResponseDto>>> getMovieGenre() {
        return ResponseEntity.ok(new GlobalResponse<List<MovieGenreResponseDto>>().successResponse(objectMapper.convertValue(movieGenreService.getActive(), new TypeReference<List<MovieGenreResponseDto>>() {
        }), "Movie Genre get"));
    }

    @PutMapping("{id}")
    public ResponseEntity<GlobalResponse<List<MovieGenreResponseDto>>> updateMovieGenre(@PathVariable Integer id, @RequestBody @Valid MovieGenreAddDto movieGenreAddDto) {
        MovieGenre movieGenre = objectMapper.convertValue(movieGenreAddDto, MovieGenre.class);
        movieGenre.setId(id);
        return ResponseEntity.ok(new GlobalResponse<Integer>().successResponse(movieGenreService
                .create(movieGenre).getId(), "Movie Genre Updated"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GlobalResponse<Integer>> deleteMovieGenre(@PathVariable Integer id) {
        movieGenreService.softDelete(id);
        return ResponseEntity.ok(new GlobalResponse<>().successResponse(null, "Movie genre delete"));
    }

}
