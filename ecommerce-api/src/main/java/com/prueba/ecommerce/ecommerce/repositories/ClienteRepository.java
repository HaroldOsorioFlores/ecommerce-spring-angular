package com.prueba.ecommerce.ecommerce.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.prueba.ecommerce.ecommerce.models.ClienteModel;

@Repository
public interface ClienteRepository extends R2dbcRepository<ClienteModel, Long> {
}
