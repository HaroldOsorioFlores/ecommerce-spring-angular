package com.prueba.ecommerce.ecommerce.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.prueba.ecommerce.ecommerce.dto.PedidoDetalleDto;
import com.prueba.ecommerce.ecommerce.dto.PedidoDto;
import com.prueba.ecommerce.ecommerce.models.PedidoModel;
import com.prueba.ecommerce.ecommerce.services.PedidoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PedidoModel> crearPedido(@RequestBody PedidoDto pedido) {
        return pedidoService.crearPedido(pedido);
    }

    @GetMapping
    public Flux<PedidoDetalleDto> obtenerTodosLosPedidos() {
        return pedidoService.obtenerTodosLosPedidos();
    }

    @GetMapping("/{id}")
    public Mono<PedidoModel> obtenerPedidoPorId(@PathVariable String id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public Flux<PedidoModel> obtenerPedidosPorCliente(@PathVariable Long clienteId) {
        return pedidoService.obtenerPedidosPorCliente(clienteId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> eliminarPedido(@PathVariable String id) {
        return pedidoService.eliminarPedido(id).then();
    }

    @PutMapping("/{id}")
    public Mono<PedidoModel> actualizarPedido(
            @PathVariable String id,
            @RequestBody PedidoModel pedido) {
        return pedidoService.actualizarPedido(id, pedido);
    }
}
