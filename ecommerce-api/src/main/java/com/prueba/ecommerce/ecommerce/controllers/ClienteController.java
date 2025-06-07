package com.prueba.ecommerce.ecommerce.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.prueba.ecommerce.ecommerce.models.ClienteModel;
import com.prueba.ecommerce.ecommerce.services.ClienteService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ClienteModel> obtenerTodosLosClientes() {
        return clienteService.obtenerTodosLosClientes();
    }
}
