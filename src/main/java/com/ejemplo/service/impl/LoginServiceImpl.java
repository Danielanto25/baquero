package com.ejemplo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ejemplo.model.Usuario;
import com.ejemplo.repository.UsuarioRepository;

@Service
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.buscarUsuarioClaveEstadoPorUsuario(username);

		boolean estado = false;
		
		if (usuario.getEstado() == 1) {
			estado = true;
		}

		List<GrantedAuthority> lstRole = buscarRolePorUsuario(username);

		return new User(username, usuario.getClave(), estado, true, true, true, lstRole);

	}

	private List<GrantedAuthority> buscarRolePorUsuario(String usuario) {

		List<String> lstStr = usuarioRepository.buscarRolePorUsuario(usuario);
		List<GrantedAuthority> lstRole = new ArrayList<>();

		for (String role : lstStr) {

			lstRole.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));

		}

		return lstRole;
	};
}
