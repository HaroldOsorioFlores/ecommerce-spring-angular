package com.prueba.ecommerce.ecommerce.services;

import com.prueba.ecommerce.ecommerce.dto.PedidoDetalleDto;
import com.prueba.ecommerce.ecommerce.dto.PedidoDto;
import com.prueba.ecommerce.ecommerce.models.PedidoModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PedidoService {
    Flux<PedidoModel> obtenerPedidosPorCliente(Long clienteId);

    Flux<PedidoDetalleDto> obtenerTodosLosPedidos();

    Mono<PedidoModel> crearPedido(PedidoDto pedido);

    Mono<PedidoModel> actualizarPedido(String id, PedidoModel pedido);

    Mono<PedidoModel> eliminarPedido(String id);

    Mono<PedidoModel> obtenerPedidoPorId(String id);
}
