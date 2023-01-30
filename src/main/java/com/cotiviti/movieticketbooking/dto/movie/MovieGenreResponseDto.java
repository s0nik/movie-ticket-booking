package com.cotiviti.movieticketbooking.dto.movie;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MovieGenreResponseDto {

    private Integer id;

    private String name;

    private String description;

}
