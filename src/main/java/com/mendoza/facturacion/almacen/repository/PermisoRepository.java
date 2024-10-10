package com.mendoza.facturacion.almacen.repository;

import com.mendoza.facturacion.almacen.entity.Permiso;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
    List<Permiso> findByNombreContaining(String nombre, Pageable pageable);
}
