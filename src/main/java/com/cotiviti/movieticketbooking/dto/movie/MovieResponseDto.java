package com.cotiviti.movieticketbooking.dto.movie;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class MovieResponseDto {

    private Integer id;

    private String name;

    private String description;

    private String director;

    private String runTime;

    private Boolean status;

    private List<Genre> genre;

    static class Genre {
        private Integer id;
        private String name;
    }

}
