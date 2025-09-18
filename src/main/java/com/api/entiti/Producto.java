package com.api.entiti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodcuto_id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "orden_id")
    private Orden orden;
    private String codigo;
    private String descripcion;
    private double precio;
}
