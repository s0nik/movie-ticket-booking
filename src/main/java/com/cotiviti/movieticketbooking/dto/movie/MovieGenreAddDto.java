package com.cotiviti.movieticketbooking.dto.movie;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MovieGenreAddDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;

    private Boolean status = true;

}
