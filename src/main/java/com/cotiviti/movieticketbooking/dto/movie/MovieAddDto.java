package com.cotiviti.movieticketbooking.dto.movie;

import com.cotiviti.movieticketbooking.model.movie.MovieGenre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class MovieAddDto {
    private String name;

    private String description;

    private String director;

    private String runTime;

    private List<Integer> genre;

    private Boolean status = true;

}
