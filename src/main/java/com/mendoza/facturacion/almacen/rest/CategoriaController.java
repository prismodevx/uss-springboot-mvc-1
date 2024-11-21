package com.mendoza.facturacion.almacen.rest;

import com.mendoza.facturacion.almacen.converter.CategoriaConverter;
import com.mendoza.facturacion.almacen.dto.CategoriaDto;
import com.mendoza.facturacion.almacen.entity.Categoria;
import com.mendoza.facturacion.almacen.service.CategoriaService;
import com.mendoza.facturacion.almacen.service.PdfService;
import com.mendoza.facturacion.almacen.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.thymeleaf.context.Context;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @Autowired
    private CategoriaConverter converter;

    @Autowired
    private PdfService pdfService;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<CategoriaDto> categorias = converter.fromEntities(service.findAll());
//        return ResponseEntity.ok(categorias);
        return new WrapperResponse(true, "success", categorias).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> create (@RequestBody CategoriaDto categoria) {
        Categoria entity = converter.fromDTO(categoria);
        CategoriaDto dto = converter.fromEntity(service.save(entity));
//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update (@PathVariable("id") int id, @RequestBody CategoriaDto categoria) {
        Categoria entity = converter.fromDTO(categoria);
        CategoriaDto dto = converter.fromEntity(service.save(entity));
//        return ResponseEntity.ok(dto);
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
        CategoriaDto dto = converter.fromEntity(service.findById(id));

//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport() {
        List<CategoriaDto> categorias = converter.fromEntities(service.findAll());

        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHora = fecha.format(formato);

        // crear el contexto de Thymeleaf con los datos
        Context context = new Context();
        context.setVariable("registros", categorias);
        context.setVariable("fecha", fechaHora);

        // Llamar al servicio PdfService para generar el PDF
        byte[] pdfBytes = pdfService.createPdf("categoriaReporte", context);

        // Configurar los encabezados de la respuesta HTTP para devolver el PDF
        // import org.springframework.http.HttpHeaders;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "reporte_categorias.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}
