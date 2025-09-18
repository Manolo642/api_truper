package com.api.controller;

import com.api.entiti.Sucursal;
import com.api.service.SucursalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/sucursal")
public class SucursalController {


    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService){
        this.sucursalService = sucursalService;
    }

    @GetMapping("getSucursalById/{idSucursal}")
    public ResponseEntity<Sucursal> getSucursalById(@PathVariable Long idSucursal){
        try {
            Optional<Sucursal> sucursal = sucursalService.getSucursalById(idSucursal);
            if(sucursal.isPresent()){
                Sucursal suc = sucursal.get();
                return ResponseEntity.ok(suc);
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            log.error("Error al obtener la sucursal{} " , e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
