package com.prueba.ecommerce.ecommerce.servicesImpl;

import org.springframework.stereotype.Service;

import com.prueba.ecommerce.ecommerce.models.ProductoModel;
import com.prueba.ecommerce.ecommerce.repositories.ProductRepository;
import com.prueba.ecommerce.ecommerce.services.ProductoService;

import reactor.core.publisher.Flux;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductRepository productRepository;

    public ProductoServiceImpl(
            ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<ProductoModel> obtenerTodosLosProductos() {
        return productRepository.findAll();
    }
}
