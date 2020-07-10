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

		System.out.println("Aqui viene el usuario desde el servicio -> " + username);
		
		Usuario usuario =usuarioRepository.buscarUsuarioClaveEstadoPorUsuario(username);
		System.out.println(usuario);
		
		boolean estado = true;
		
		List<GrantedAuthority> lstRole = new ArrayList<>();
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ESTUDIANTE");
		
		lstRole.add(authority);
		
		String clave = "$2a$10$TeQe.doJWrSuUIGDDClxAeVtbRlqlQ5xExaByisnog.AKIhMD6mmO";
		
		return new User(username, clave, estado, true, true, true, lstRole);
	}

}
