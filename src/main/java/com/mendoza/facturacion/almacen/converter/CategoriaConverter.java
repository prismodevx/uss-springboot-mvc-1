package com.mendoza.facturacion.almacen.converter;

import com.mendoza.facturacion.almacen.dto.CategoriaDto;
import com.mendoza.facturacion.almacen.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaConverter extends AbstractConverter<Categoria, CategoriaDto> {
    @Override
    public CategoriaDto fromEntity(Categoria entity) {
        if (entity == null) return null;

        return CategoriaDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }

    @Override
    public Categoria fromDTO(CategoriaDto dto) {
        if (dto == null) return null;

        return Categoria.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }
}
