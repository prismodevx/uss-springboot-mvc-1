package com.mendoza.facturacion.almacen.repository;

import com.mendoza.facturacion.almacen.entity.Rol;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    List<Rol> findByNombreContaining(String nombre, Pageable pageable);
}
