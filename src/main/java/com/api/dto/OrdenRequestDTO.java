package com.api.dto;

import com.api.entiti.Orden;
import com.api.entiti.Producto;
import com.api.entiti.Sucursal;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrdenRequestDTO {

    private Sucursal sucursal;
    private List<Producto> productos;
    private LocalDate fecha;
    private double total;

}
