package com.prueba.ecommerce.ecommerce.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.prueba.ecommerce.ecommerce.models.PedidoModel;

import reactor.core.publisher.Flux;

@Repository
public interface PedidoRepository extends ReactiveMongoRepository<PedidoModel, String> {
    Flux<PedidoModel> findByClienteId(Long clienteId);
}