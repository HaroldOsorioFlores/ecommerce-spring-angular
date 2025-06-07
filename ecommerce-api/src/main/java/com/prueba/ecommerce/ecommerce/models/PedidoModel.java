package com.prueba.ecommerce.ecommerce.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("pedidos")
public class PedidoModel {
    @Id
    private String id;
    private Long clienteId;
    private List<Long> productosId;
    private Double total;
}
