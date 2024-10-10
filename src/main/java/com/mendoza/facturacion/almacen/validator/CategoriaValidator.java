package com.mendoza.facturacion.almacen.validator;

import com.mendoza.facturacion.almacen.entity.Categoria;
import com.mendoza.facturacion.almacen.exception.ValidateException;

public class CategoriaValidator {
    public static void save(Categoria registro) {
        if(registro.getNombre() == null || registro.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre es requerido");
        }
        if(registro.getNombre().length() > 70) {
            throw new ValidateException("El nombre no debe exceder los 70 caracteres");
        }
    }
}
