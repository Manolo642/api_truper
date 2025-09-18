package com.api.service;

import com.api.entiti.Producto;
import com.api.repositori.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;


    public void guardarProducto(Producto producto){
        productoRepository.save(producto);
    }

}
