package com.cotiviti.movieticketbooking.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {

    @NotEmpty
    private String username;

    @NotEmpty
    @NotEmpty
    private String password;

}
