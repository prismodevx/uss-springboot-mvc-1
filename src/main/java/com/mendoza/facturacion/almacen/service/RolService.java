package com.mendoza.facturacion.almacen.service;

import com.mendoza.facturacion.almacen.entity.Rol;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RolService {
    public List<Rol> findAll(Pageable page);
    public List<Rol> findByNombre(String nombre, Pageable page);
    public Rol findById(int id);
    public Rol save(Rol rol);
    public void delete(int id);
}
