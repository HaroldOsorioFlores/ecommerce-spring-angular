package com.prueba.ecommerce.ecommerce.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("productos")
public class ProductoModel {
    @Id
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
}
