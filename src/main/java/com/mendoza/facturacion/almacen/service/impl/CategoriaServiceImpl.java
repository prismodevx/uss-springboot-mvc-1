package com.mendoza.facturacion.almacen.service.impl;

import com.mendoza.facturacion.almacen.entity.Categoria;
import com.mendoza.facturacion.almacen.repository.CategoriaRepository;
import com.mendoza.facturacion.almacen.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<Categoria> findAll(Pageable page) {
        try {
            List<Categoria> registros = repository.findAll(page).toList();
            return registros;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Categoria> findByNombre(String nombre, Pageable page) {
        try {
            List<Categoria> registros = repository.findByNombreContaining(nombre, page);
            return registros;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Categoria findById(int id) {
        try {
            Categoria registro = repository.findById(id).orElseThrow();
            return registro;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Categoria save(Categoria categoria) {
        try {
            if(categoria.getId() == 0) {
                Categoria nuevo = repository.save(categoria);
                return nuevo;
            }
            Categoria registro = repository.findById(categoria.getId()).orElseThrow();
            registro.setNombre(categoria.getNombre());
            registro.setDescripcion(categoria.getDescripcion());
            repository.save(registro);
            return registro;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(int id) {
        try {
            Categoria registro = repository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw e;
        }
    }
}
