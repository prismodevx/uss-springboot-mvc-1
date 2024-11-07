package com.mendoza.facturacion.almacen.rest;

import com.mendoza.facturacion.almacen.converter.UsuarioConverter;
import com.mendoza.facturacion.almacen.dto.CategoriaDto;
import com.mendoza.facturacion.almacen.dto.UsuarioDto;
import com.mendoza.facturacion.almacen.entity.Usuario;
import com.mendoza.facturacion.almacen.service.UsuarioService;
import com.mendoza.facturacion.almacen.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioConverter converter;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        List<UsuarioDto> registros = converter.fromEntities(service.findAll());
        return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById (@PathVariable("id") int id) {
        UsuarioDto dto = converter.fromEntity(service.findById(id));

        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> create(@RequestBody Usuario usuario) {
        UsuarioDto registro = converter.fromEntity(service.save(usuario));
        return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        UsuarioDto registro = converter.fromEntity(service.save(usuario));
        return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<UsuarioDto> deactivate(@PathVariable("id") int id) {
        UsuarioDto registro = converter.fromEntity(service.deactivate(id));
        return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<UsuarioDto> activate(@PathVariable("id") int id) {
        UsuarioDto registro = converter.fromEntity(service.activate(id));
        return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
    }
}
