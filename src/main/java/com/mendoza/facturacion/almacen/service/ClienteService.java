package com.mendoza.facturacion.almacen.service;

import com.mendoza.facturacion.almacen.entity.Cliente;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {
    public List<Cliente> findAll(Pageable page);
    public List<Cliente> findAll();
    public List<Cliente> findByNombre(String nombre, Pageable page);
    public Cliente findById(int id);
    public Cliente save(Cliente cliente);
    public void delete(int id);
}
