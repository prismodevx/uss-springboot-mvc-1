package com.mendoza.facturacion.almacen.rest;

import com.mendoza.facturacion.almacen.converter.CategoriaConverter;
import com.mendoza.facturacion.almacen.dto.CategoriaDto;
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

    @Autowired
    private CategoriaConverter converter;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<CategoriaDto> categorias = converter.fromEntities(service.findAll(page));
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> create (@RequestBody CategoriaDto categoria) {
        Categoria entity = converter.fromDTO(categoria);
        CategoriaDto dto = converter.fromEntity(service.save(entity));
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update (@PathVariable("id") int id, @RequestBody CategoriaDto categoria) {
        Categoria entity = converter.fromDTO(categoria);
        CategoriaDto dto = converter.fromEntity(service.save(entity));
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById (@PathVariable("id") int id) {
        CategoriaDto dto = converter.fromEntity(service.findById(id));

        return ResponseEntity.ok(dto);
    }
}
