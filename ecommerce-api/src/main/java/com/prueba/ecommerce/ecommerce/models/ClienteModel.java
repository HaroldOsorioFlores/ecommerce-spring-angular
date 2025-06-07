package com.prueba.ecommerce.ecommerce.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("clientes")
public class ClienteModel {
    @Id
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
}
