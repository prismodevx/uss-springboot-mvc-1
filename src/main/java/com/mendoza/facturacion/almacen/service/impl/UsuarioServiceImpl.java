package com.mendoza.facturacion.almacen.service.impl;

import com.mendoza.facturacion.almacen.entity.Usuario;
import com.mendoza.facturacion.almacen.exception.GeneralException;
import com.mendoza.facturacion.almacen.exception.NoDataFoundException;
import com.mendoza.facturacion.almacen.exception.ValidateException;
import com.mendoza.facturacion.almacen.repository.UsuarioRepository;
import com.mendoza.facturacion.almacen.service.UsuarioService;
import com.mendoza.facturacion.almacen.util.Encoder;
import com.mendoza.facturacion.almacen.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private Encoder encoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll(Pageable page) {
        try {
            List<Usuario> registros = repository.findAll(page).toList();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        try {
            List<Usuario> registros = repository.findAll();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) {
        try {
            Usuario registro = repository.findByEmail(email);
            if (registro == null) {
                throw new NoDataFoundException("Error del servidor");
            }
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(int id) {
        try {
            Usuario registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        try {
            UsuarioValidator.save(usuario);
            if(usuario.getId() == 0) {
                usuario.setActivo(true);
                usuario.setPassword(encoder.encode(usuario.getPassword()));
                Usuario nuevo = repository.save(usuario);
                return nuevo;
            }
            Usuario registro = repository.findById(usuario.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
            registro.setEmail(usuario.getEmail());
            registro.setPassword(encoder.encode(usuario.getPassword()));
            repository.save(registro);
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public Usuario deactivate(int id) {
        try {
            Usuario registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
            registro.setActivo(false);
            repository.save(registro);
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public Usuario activate(int id) {
        try {
            Usuario registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
            registro.setActivo(true);
            repository.save(registro);
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }
}
