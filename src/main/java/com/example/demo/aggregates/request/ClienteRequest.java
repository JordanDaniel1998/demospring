package com.example.demo.aggregates.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest {

    private Long id;
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotEmpty(message = "El email no puede estar vacío")
    private String email;

}
