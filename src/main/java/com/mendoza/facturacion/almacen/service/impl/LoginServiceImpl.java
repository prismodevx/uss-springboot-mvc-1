package com.mendoza.facturacion.almacen.service.impl;

import com.mendoza.facturacion.almacen.entity.Usuario;
import com.mendoza.facturacion.almacen.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = service.findByEmail(username);
        if (usuario == null) throw new UsernameNotFoundException("Usuario no encontrado con el email");

        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNombre()))
                .collect(Collectors.toList());

        return User.withUsername(usuario.getEmail())
                .password(usuario.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!usuario.isActivo())
                .build();
    }
}
