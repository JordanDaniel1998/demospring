package com.example.demo.service.impl;

import com.example.demo.aggregates.constants.Constants;
import com.example.demo.aggregates.request.ClienteRequest;
import com.example.demo.aggregates.response.ClienteResponse;
import com.example.demo.entity.ClienteEntity;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteResponse addCliente(ClienteRequest clienteRequest) {
        try {

            ClienteEntity clienteEntity = convertToEntity(clienteRequest);

            ClienteResponse clienteResponse = new ClienteResponse(
                    Constants.code_successfull,
                    Constants.message_successfull,
                    Optional.of(clienteRepository.save(clienteEntity))
            );
            return clienteResponse;
        }catch (Exception e){
            return new ClienteResponse(
                    Constants.code_error,
                    Constants.message_error  + " [ " + e.getMessage() + " ]",
                    Optional.empty()
            );
        }
    }

    @Override
    public ClienteResponse updateCliente(Long id, ClienteRequest clienteRequest) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(id);
        if(!clienteEntity.isPresent()){
            ClienteResponse clienteResponse = new ClienteResponse(
                    Constants.code_error,
                    Constants.message_error,
                    Optional.empty()
            );
            return clienteResponse;
        }

        ClienteEntity cliente = clienteEntity.get();
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setEmail(clienteRequest.getEmail());

        ClienteResponse clienteResponse = new ClienteResponse(
                Constants.code_successfull,
                Constants.message_successfull,
                Optional.of(clienteRepository.save(cliente))
        );

        return clienteResponse;
    }

    @Override
    public ClienteResponse deleteCliente(Long id) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(id);
        if(!clienteEntity.isPresent()){
            ClienteResponse clienteResponse = new ClienteResponse(
                    Constants.code_error,
                    Constants.message_error,
                    Optional.empty()
            );
            return clienteResponse;
        }
        ClienteEntity cliente = clienteEntity.get();
        clienteRepository.deleteById(cliente.getId());
        ClienteResponse clienteResponse = new ClienteResponse(
                Constants.code_successfull,
                Constants.message_successfull,
                Optional.empty()
        );

        return clienteResponse;

    }

    @Override
    public ClienteResponse getClienteById(Long id) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(id);
        if(!clienteEntity.isPresent()){
            ClienteResponse clienteResponse = new ClienteResponse(
                    Constants.code_error,
                    "No existe usuairo",
                    Optional.empty()
            );
            return clienteResponse;
        }
        ClienteEntity cliente = clienteEntity.get();
        ClienteResponse clienteResponse = new ClienteResponse(
                Constants.code_successfull,
                "Usuario encontrado",
                Optional.of(cliente)
        );

        return clienteResponse;
    }

    private ClienteEntity convertToEntity(ClienteRequest clienteRequest){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNombre(clienteRequest.getNombre());
        clienteEntity.setEmail(clienteRequest.getEmail());
        return clienteEntity;
    }



}
