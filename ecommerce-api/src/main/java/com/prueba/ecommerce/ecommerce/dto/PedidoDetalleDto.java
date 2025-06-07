package com.prueba.ecommerce.ecommerce.dto;

import java.util.List;

import com.prueba.ecommerce.ecommerce.models.ClienteModel;
import com.prueba.ecommerce.ecommerce.models.ProductoModel;

import lombok.Data;

@Data
public class PedidoDetalleDto {
    private String id;
    private ClienteModel cliente;
    private List<ProductoModel> productos;
    private Double total;
}
