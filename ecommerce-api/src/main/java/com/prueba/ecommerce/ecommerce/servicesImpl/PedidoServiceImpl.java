package com.prueba.ecommerce.ecommerce.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.ecommerce.ecommerce.dto.PedidoDetalleDto;
import com.prueba.ecommerce.ecommerce.dto.PedidoDto;
import com.prueba.ecommerce.ecommerce.models.ClienteModel;
import com.prueba.ecommerce.ecommerce.models.PedidoModel;
import com.prueba.ecommerce.ecommerce.models.ProductoModel;
import com.prueba.ecommerce.ecommerce.repositories.ClienteRepository;
import com.prueba.ecommerce.ecommerce.repositories.PedidoRepository;
import com.prueba.ecommerce.ecommerce.repositories.ProductRepository;
import com.prueba.ecommerce.ecommerce.services.PedidoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ProductRepository productRepository;
    private final ClienteRepository clienteRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,
            ProductRepository productRepository,
            ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productRepository = productRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Mono<PedidoModel> actualizarPedido(String id, PedidoModel pedidoActualizado) {
        return pedidoRepository.findById(id)
                .flatMap(pedidoExistente -> {
                    pedidoExistente.setClienteId(pedidoActualizado.getClienteId());
                    pedidoExistente.setProductosId(pedidoActualizado.getProductosId());

                    return Flux.fromIterable(pedidoActualizado.getProductosId())
                            .flatMap(productId -> productRepository.findById(productId))
                            .map(ProductoModel::getPrecio)
                            .reduce(0.0, Double::sum)
                            .flatMap(nuevoTotal -> {
                                pedidoExistente.setTotal(nuevoTotal);
                                return pedidoRepository.save(pedidoExistente);
                            });
                });
    }

    @Override
    public Mono<PedidoModel> crearPedido(PedidoDto pedido) {
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setProductosId(pedido.getProductosId());
        pedidoModel.setClienteId(pedido.getClienteId());

        Flux<Long> productIdsFlux = Flux.fromIterable(pedido.getProductosId());

        Flux<ProductoModel> productosFlux = productIdsFlux.flatMap(productId -> productRepository.findById(productId));

        Flux<Double> preciosFlux = productosFlux.map(producto -> producto.getPrecio());

        Mono<Double> totalMono = preciosFlux.reduce(0.0, (subtotal, precio) -> subtotal + precio);

        return totalMono.flatMap(total -> {
            pedidoModel.setTotal(total);
            return pedidoRepository.save(pedidoModel);
        });
    }

    @Override
    public Mono<PedidoModel> eliminarPedido(String id) {
        return pedidoRepository.findById(id)
                .flatMap(pedido -> pedidoRepository.delete(pedido)
                        .then(Mono.just(pedido)));
    }

    @Override
    public Mono<PedidoModel> obtenerPedidoPorId(String id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Flux<PedidoModel> obtenerPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    @Override
    public Flux<PedidoDetalleDto> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll()
                .flatMap(pedido -> {
                    PedidoDetalleDto detalleDto = new PedidoDetalleDto();
                    detalleDto.setId(pedido.getId());
                    detalleDto.setTotal(pedido.getTotal());

                    Mono<ClienteModel> clienteMono = clienteRepository
                            .findById(pedido.getClienteId());

                    Mono<List<ProductoModel>> productosMono = Flux.fromIterable(pedido.getProductosId())
                            .flatMap(productId -> productRepository.findById(productId))
                            .collectList();

                    return Mono.zip(clienteMono, productosMono)
                            .map(tuple -> {
                                detalleDto.setCliente(tuple.getT1());
                                detalleDto.setProductos(tuple.getT2());
                                return detalleDto;
                            });
                });
    }
}
