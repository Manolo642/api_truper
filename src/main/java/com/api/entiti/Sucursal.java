package com.api.entiti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long sucursal_id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Orden> ordenes;

}
