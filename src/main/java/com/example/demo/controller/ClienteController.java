package com.example.demo.controller;

import com.example.demo.aggregates.constants.Constants;
import com.example.demo.aggregates.request.ClienteRequest;
import com.example.demo.aggregates.response.ClienteResponse;
import com.example.demo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prueba-tecnica/v1")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/create")
    public ResponseEntity<ClienteResponse> createClient(@Valid @RequestBody ClienteRequest clienteRequest){
        ClienteResponse clienteResponse = clienteService.addCliente(clienteRequest);
        if(clienteResponse.getCode().equals(Constants.code_successfull)){
            return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(clienteResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClienteResponse> updateClient(@PathVariable("id") Long id, @Valid @RequestBody ClienteRequest clienteRequest){
        ClienteResponse clienteResponse = clienteService.updateCliente(id, clienteRequest);
        if(clienteResponse.getCode().equals(Constants.code_successfull)){
            return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(clienteResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClienteResponse> updateClient(@PathVariable("id") Long id){
        ClienteResponse clienteResponse = clienteService.deleteCliente(id);
        if(clienteResponse.getCode().equals(Constants.code_successfull)){
            return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(clienteResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getClientById/{id}")
    public ResponseEntity<ClienteResponse> getClientById(@PathVariable("id") Long id){
        ClienteResponse clienteResponse = clienteService.getClienteById(id);
        if(clienteResponse.getCode().equals(Constants.code_successfull)){
            return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(clienteResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
