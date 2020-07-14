package com.ejemplo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.model.UsuarioRole;
import com.ejemplo.service.IUsuarioRoleService;

@RestController
@RequestMapping(path = "api/usuario-role")
public class UsuarioRoleController {
	
	@Autowired
	private IUsuarioRoleService service;
	
	@GetMapping(path = "listar")
	public List<UsuarioRole> listar() {
		
		return service.listar();
	}
	
	@PostMapping(path = "insertar")
	public void insert(@RequestBody UsuarioRole usuarioRole, HttpServletRequest request) {
		
		service.insert(usuarioRole, request);
	}
	
	@PutMapping(path = "update")
	public void update(@RequestBody UsuarioRole usuarioRole, HttpServletRequest request) {
		
		service.update(usuarioRole, request);
		
	}

	@PutMapping(path = "delete")
	public void delete(@RequestBody UsuarioRole usuarioRole, HttpServletRequest request) {
		
		service.delete(usuarioRole, request);
		
	}

	
}
