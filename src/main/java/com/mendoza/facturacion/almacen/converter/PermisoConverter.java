package com.mendoza.facturacion.almacen.converter;

import com.mendoza.facturacion.almacen.dto.PermisoDto;
import com.mendoza.facturacion.almacen.entity.Permiso;
import org.springframework.stereotype.Component;

@Component
public class PermisoConverter extends AbstractConverter<Permiso, PermisoDto> {
    @Override
    public PermisoDto fromEntity(Permiso entity) {
        if (entity == null) return null;

        return PermisoDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }

    @Override
    public Permiso fromDTO(PermisoDto dto) {
        if (dto == null) return null;

        return Permiso.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .build();
    }
}
