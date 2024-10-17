package com.mendoza.facturacion.almacen.validator;

import com.mendoza.facturacion.almacen.entity.Cliente;
import com.mendoza.facturacion.almacen.exception.ValidateException;

public class ClienteValidator {
    public static void save(Cliente registro) {
        if(registro.getNombre() == null || registro.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre es requerido");
        }
        if(registro.getNombre().length() > 70) {
            throw new ValidateException("El nombre no debe exceder los 70 caracteres");
        }
        if(registro.getTipoDocumento() == null || registro.getTipoDocumento().trim().isEmpty()) {
            throw new ValidateException("El tipo documento es requerido");
        }
        if(registro.getTelefono() == null || registro.getTelefono().trim().isEmpty()) {
            throw new ValidateException("El telefono es requerido");
        }
        if(registro.getTelefono().length() > 15) {
            throw new ValidateException("El telefono no debe exceder los 15 caracteres");
        }
        if(registro.getEmail() == null || registro.getEmail().trim().isEmpty()) {
            throw new ValidateException("El email es requerido");
        }
        if(registro.getEmail().length() > 70) {
            throw new ValidateException("El email no debe exceder los 70 caracteres");
        }
    }
}
