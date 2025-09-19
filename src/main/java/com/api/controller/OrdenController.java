package com.api.controller;

import com.api.dto.OrdenRequestDTO;
import com.api.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orden")
public class OrdenController {

    @Autowired
    OrdenService ordenService;

    @PostMapping("/guardarOrden")
    public ResponseEntity<?> saveOrden(@RequestBody OrdenRequestDTO requestDTO){
        try {
            ordenService.generaOrden(requestDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
