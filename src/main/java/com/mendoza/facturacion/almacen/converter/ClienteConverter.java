package com.mendoza.facturacion.almacen.converter;

import com.mendoza.facturacion.almacen.dto.CategoriaDto;
import com.mendoza.facturacion.almacen.dto.ClienteDto;
import com.mendoza.facturacion.almacen.entity.Categoria;
import com.mendoza.facturacion.almacen.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter extends AbstractConverter<Cliente, ClienteDto> {
    @Override
    public ClienteDto fromEntity(Cliente entity) {
        if (entity == null) return null;

        return ClienteDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .tipoDocumento(entity.getTipoDocumento())
                .numeroDocumento(entity.getNumeroDocumento())
                .telefono(entity.getTelefono())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public Cliente fromDTO(ClienteDto dto) {
        if (dto == null) return null;

        return Cliente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .tipoDocumento(dto.getTipoDocumento())
                .numeroDocumento(dto.getNumeroDocumento())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .build();
    }
}
