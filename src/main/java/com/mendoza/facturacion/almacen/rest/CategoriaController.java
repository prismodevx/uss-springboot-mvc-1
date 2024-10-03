package com.mendoza.facturacion.almacen.rest;

import com.mendoza.facturacion.almacen.entity.Categoria;
import com.mendoza.facturacion.almacen.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Categoria> categorias = service.findAll(page);
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<Categoria> create (@RequestBody Categoria categoria) {
        Categoria registro = service.save(categoria);
        return ResponseEntity.ok(registro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update (@PathVariable("id") Integer id, @RequestBody Categoria categoria) {
        Categoria registro = service.save(categoria);
        return ResponseEntity.ok(registro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> delete (@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById (@PathVariable("id") Integer id) {
        Categoria registro = service.findById(id);
        return ResponseEntity.ok(registro);
    }
}
