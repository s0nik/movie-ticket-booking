package com.cotiviti.movieticketbooking.service.impl;

import com.cotiviti.movieticketbooking.dto.movie.MovieAddDto;
import com.cotiviti.movieticketbooking.generic.GenericServiceImpl;
import com.cotiviti.movieticketbooking.model.movie.Movie;
import com.cotiviti.movieticketbooking.model.movie.MovieGenre;
import com.cotiviti.movieticketbooking.repo.MovieRepository;
import com.cotiviti.movieticketbooking.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MovieServiceImpl extends GenericServiceImpl<Movie, Integer> implements MovieService {

    private final MovieRepository movieRepository;
    private final ObjectMapper objectMapper;


    public MovieServiceImpl(MovieRepository movieRepository, ObjectMapper objectMapper) {
        super(movieRepository);
        this.movieRepository = movieRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Integer create(MovieAddDto movieAddDto) {
        Movie movie = objectMapper.convertValue(movieAddDto, Movie.class);
        movie.setGenre(movieAddDto.getGenre().stream().map(item -> new MovieGenre(item)).collect(Collectors.toList()));
        movieRepository.save(movie);
        return movie.getId();
    }
}
