package com.mendoza.facturacion.almacen.service;

import com.mendoza.facturacion.almacen.entity.Categoria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoriaService {
    public List<Categoria> findAll(Pageable page);
    public List<Categoria> findByNombre(String nombre, Pageable page);
    public Categoria findById(int id);
    public Categoria save(Categoria categoria);
    public void delete(int id);
}