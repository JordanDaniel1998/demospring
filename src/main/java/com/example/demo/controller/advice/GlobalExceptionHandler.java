package com.example.demo.controller.advice;

import com.example.demo.aggregates.constants.Constants;
import com.example.demo.aggregates.response.ClienteResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ClienteResponse> controllerExceptionGeneral(Exception e){
        ClienteResponse clienteResponse = new ClienteResponse(
                Constants.code_error,
                Constants.message_trx_error + " [ " + e.getMessage() + " ]",
                Optional.empty()
        );

        return new ResponseEntity<>(clienteResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ClienteResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Mapear los errores de validación
        StringBuilder errorMessages = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessages.append(error.getDefaultMessage() + " ; ");
        });

        ClienteResponse clienteResponse = new ClienteResponse(
                Constants.code_error,
                errorMessages.toString(),
                Optional.empty()
        );

        // Devolver respuesta con BAD_REQUEST (400)
        return new ResponseEntity<>(clienteResponse, HttpStatus.BAD_REQUEST);
    }
}
