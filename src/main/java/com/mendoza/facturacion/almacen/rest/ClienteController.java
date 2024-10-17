package com.mendoza.facturacion.almacen.rest;

import ch.qos.logback.core.net.server.Client;
import com.mendoza.facturacion.almacen.converter.CategoriaConverter;
import com.mendoza.facturacion.almacen.converter.ClienteConverter;
import com.mendoza.facturacion.almacen.dto.CategoriaDto;
import com.mendoza.facturacion.almacen.dto.ClienteDto;
import com.mendoza.facturacion.almacen.entity.Categoria;
import com.mendoza.facturacion.almacen.entity.Cliente;
import com.mendoza.facturacion.almacen.service.CategoriaService;
import com.mendoza.facturacion.almacen.service.ClienteService;
import com.mendoza.facturacion.almacen.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteConverter converter;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<ClienteDto> clientes = converter.fromEntities(service.findAll());

        return new WrapperResponse(true, "success", clientes).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> create (@RequestBody ClienteDto cliente) {
        Cliente entity = converter.fromDTO(cliente);
        ClienteDto dto = converter.fromEntity(service.save(entity));

        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update (@PathVariable("id") int id, @RequestBody ClienteDto cliente) {
        Cliente entity = converter.fromDTO(cliente);
        ClienteDto dto = converter.fromEntity(service.save(entity));

        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") int id) {
        service.delete(id);
//        return ResponseEntity.ok(null);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById (@PathVariable("id") int id) {
        ClienteDto dto = converter.fromEntity(service.findById(id));

        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }
}
