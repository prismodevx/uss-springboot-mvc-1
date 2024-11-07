package com.mendoza.facturacion.almacen.converter;

import com.mendoza.facturacion.almacen.dto.UsuarioDto;
import com.mendoza.facturacion.almacen.entity.Usuario;
import org.springframework.stereotype.Component;


@Component
public class UsuarioConverter extends AbstractConverter<Usuario, UsuarioDto> {
    @Override
    public UsuarioDto fromEntity(Usuario entity) {
        if(entity == null) return null;
        return UsuarioDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .activo(entity.isActivo())
                .build();
    }

    @Override
    public Usuario fromDTO(UsuarioDto dto) {
        if(dto == null) return null;
        return Usuario.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .activo(dto.isActivo())
                .build();
    }
}
