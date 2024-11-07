package com.mendoza.facturacion.almacen.validator;

import com.mendoza.facturacion.almacen.entity.Usuario;
import com.mendoza.facturacion.almacen.exception.ValidateException;

public class UsuarioValidator {
    public static void save(Usuario registro) {
        if(registro.getEmail() == null || registro.getEmail().trim().isEmpty()) {
            throw new ValidateException("El email es obligatorio");
        }
        if(registro.getEmail().length() > 70) {
            throw new ValidateException("El email no debe exceder los 70 caracteres");
        }
        if(registro.getPassword() == null || registro.getPassword().trim().isEmpty()) {
            throw new ValidateException("El password es requerido");
        }
    }
}
