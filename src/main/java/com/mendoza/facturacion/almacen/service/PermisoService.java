package com.mendoza.facturacion.almacen.service;

import com.mendoza.facturacion.almacen.entity.Permiso;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PermisoService {
    public List<Permiso> findAll(Pageable page);
    public List<Permiso> findByNombre(String nombre, Pageable page);
    public Permiso findById(int id);
    public Permiso save(Permiso permiso);
    public void delete(int id);
}
