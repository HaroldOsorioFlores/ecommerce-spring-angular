package com.prueba.ecommerce.ecommerce.services;

import com.prueba.ecommerce.ecommerce.models.ProductoModel;

import reactor.core.publisher.Flux;

public interface ProductoService {
    Flux<ProductoModel> obtenerTodosLosProductos();
}
