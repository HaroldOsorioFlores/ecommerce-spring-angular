package com.prueba.ecommerce.ecommerce.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.prueba.ecommerce.ecommerce.models.ProductoModel;

public interface ProductRepository extends R2dbcRepository<ProductoModel, Long> {

}
