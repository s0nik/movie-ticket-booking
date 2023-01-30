package com.cotiviti.movieticketbooking.service.impl;

import com.cotiviti.movieticketbooking.generic.GenericServiceImpl;
import com.cotiviti.movieticketbooking.model.movie.MovieGenre;
import com.cotiviti.movieticketbooking.repo.MovieGenreRepository;
import com.cotiviti.movieticketbooking.service.MovieGenreService;
import org.springframework.stereotype.Service;

@Service
public class MovieGenreServiceImpl extends GenericServiceImpl<MovieGenre, Integer> implements MovieGenreService {

    private final MovieGenreRepository movieGenreRepository;

    public MovieGenreServiceImpl(MovieGenreRepository movieGenreRepository) {
        super(movieGenreRepository);
        this.movieGenreRepository = movieGenreRepository;
    }
}
