package com.cotiviti.movieticketbooking.service;

import com.cotiviti.movieticketbooking.dto.movie.MovieAddDto;
import com.cotiviti.movieticketbooking.generic.GenericService;
import com.cotiviti.movieticketbooking.model.movie.Movie;

public interface MovieService extends GenericService<Movie, Integer> {

    Integer create(MovieAddDto movieAddDto);

}
