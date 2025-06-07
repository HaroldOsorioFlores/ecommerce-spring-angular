package com.prueba.ecommerce.ecommerce.services;

import com.prueba.ecommerce.ecommerce.models.ClienteModel;

import reactor.core.publisher.Flux;

public interface ClienteService {
    Flux<ClienteModel> obtenerTodosLosClientes();
}
