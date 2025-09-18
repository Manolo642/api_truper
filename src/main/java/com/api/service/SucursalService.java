package com.api.service;

import com.api.entiti.Sucursal;
import com.api.repositori.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository){
        this.sucursalRepository = sucursalRepository;
    }

    public Optional<Sucursal> getSucursalById(Long idSucursal){
        return sucursalRepository.findById(idSucursal);
    }

    public void saveSucursal(Sucursal sucursal){
        sucursalRepository.save(sucursal);
    }



}
