package com.mendoza.facturacion.almacen.repository;

import com.mendoza.facturacion.almacen.entity.Categoria;
import com.mendoza.facturacion.almacen.entity.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNombreContaining(String nombre, Pageable pageable);
}
