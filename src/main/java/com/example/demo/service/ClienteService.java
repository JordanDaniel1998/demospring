package com.example.demo.service;

import com.example.demo.aggregates.request.ClienteRequest;
import com.example.demo.aggregates.response.ClienteResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public interface ClienteService {


    ClienteResponse addCliente(ClienteRequest clienteRequest);

    ClienteResponse updateCliente(Long id, ClienteRequest clienteRequest);

    ClienteResponse deleteCliente(Long id);

    ClienteResponse getClienteById(Long id);

}
