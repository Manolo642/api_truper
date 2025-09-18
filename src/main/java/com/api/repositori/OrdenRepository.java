package com.api.repositori;

import com.api.entiti.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden,Long> {
}
