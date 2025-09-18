package com.api.service;

import com.api.dto.OrdenRequestDTO;
import com.api.entiti.Orden;
import com.api.entiti.Producto;
import com.api.repositori.OrdenRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService {

    private final OrdenRepository ordenRepository;
    private final SucursalService sucursalService;
    private final ProductoService productoService;

    public OrdenService(OrdenRepository ordenRepository, SucursalService sucursalService, ProductoService productoService){
        this.ordenRepository = ordenRepository;
        this.sucursalService = sucursalService;
        this.productoService = productoService;
    }


    @Transactional
    public void generaOrden(OrdenRequestDTO requestDTO) {
        List<Producto> productoList = requestDTO.getProductos();
        sucursalService.saveSucursal(requestDTO.getSucursal());

        double valorT = 0.0;
        for(Producto pro: productoList){
            valorT += pro.getPrecio();
            productoService.guardarProducto(pro);
        }

        Orden orden = new Orden();
        orden.setFecha(requestDTO.getFecha());
        orden.setTotal(valorT);

        ordenRepository.save(orden);

    }



}
