package com.prueba.ecommerce.ecommerce.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoDto {
    private Long clienteId;
    private List<Long> productosId;
}
