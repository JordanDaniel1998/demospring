package com.example.demo.aggregates.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class ClienteResponse {

    private Integer code;
    private String message;
    private Optional ventas;

}
