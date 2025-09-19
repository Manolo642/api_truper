package com.api.service;

import com.api.dto.OrdenRequestDTO;
import com.api.dto.ProductoDTO;
import com.api.entiti.Orden;
import com.api.entiti.Producto;
import com.api.entiti.Sucursal;
import com.api.repositori.OrdenRepository;
import com.api.repositori.ProductoRepository;
import com.api.repositori.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenService {

    private final OrdenRepository ordenRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    public OrdenService(OrdenRepository ordenRepository, SucursalRepository sucursalRepository, ProductoRepository productoRepository){
        this.ordenRepository = ordenRepository;
        this.sucursalRepository = sucursalRepository;
        this.productoRepository = productoRepository;
    }


    @Transactional
    public void generaOrden(OrdenRequestDTO requestDTO) {

        if (requestDTO.getSucursalName() == null || requestDTO.getSucursalName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sucursal es obligatorio.");
        }

        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(requestDTO.getSucursalName());

        sucursal = sucursalRepository.save(sucursal);

        // Creamos la orden
        Orden orden = new Orden();
        orden.setFecha(LocalDate.now());
        orden.setSucursal(sucursal);

        // Productos

        List<ProductoDTO> productoDTOS = requestDTO.getProductosDto();
        if (productoDTOS == null || productoDTOS.isEmpty()) {
            throw new IllegalArgumentException("La orden debe tener al menos un producto.");
        }

        List<Producto> productos = new ArrayList<>();
        for(ProductoDTO item : requestDTO.getProductosDto()){
            Producto producto = new Producto();
            producto.setCodigo(item.getCodigo());
            producto.setDescripcion(item.getDescripcion());
            producto.setPrecio(item.getPrecio());
            producto.setOrden(orden);
            productos.add(producto);
        }
        /*
        List<Producto> productos = productoDTOS.stream().map(
                item -> {
                    Producto producto = new Producto();
                    producto.setCodigo(item.getCodigo());
                    producto.setDescripcion(item.getDescripcion());
                    producto.setPrecio(item.getPrecio());
                    producto.setOrden(orden);
                    return  producto;
                }
        ).toList();
        */
        double total = productos.stream().mapToDouble(Producto::getPrecio).sum();

        orden.setProductos(productos);
        orden.setTotal(total);
        ordenRepository.save(orden);

    }



}
