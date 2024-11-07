package com.mendoza.facturacion.almacen.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    private int id;
    private String email;
    private String password;
    private boolean activo;
}
