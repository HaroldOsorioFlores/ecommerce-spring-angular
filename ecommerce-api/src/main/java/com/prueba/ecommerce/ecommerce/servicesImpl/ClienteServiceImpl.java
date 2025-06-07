package com.prueba.ecommerce.ecommerce.servicesImpl;

import org.springframework.stereotype.Service;

import com.prueba.ecommerce.ecommerce.models.ClienteModel;
import com.prueba.ecommerce.ecommerce.repositories.ClienteRepository;
import com.prueba.ecommerce.ecommerce.services.ClienteService;

import reactor.core.publisher.Flux;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(
            ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    @Override
    public Flux<ClienteModel> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }
}
