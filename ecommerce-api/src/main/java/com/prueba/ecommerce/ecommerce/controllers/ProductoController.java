package com.prueba.ecommerce.ecommerce.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.prueba.ecommerce.ecommerce.models.ProductoModel;
import com.prueba.ecommerce.ecommerce.services.ProductoService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductoModel> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }
}
