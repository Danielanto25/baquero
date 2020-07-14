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

import com.ejemplo.model.Role;
import com.ejemplo.service.IRoleService;

@RestController
@RequestMapping(path = "api/role")
public class RoleController {
	
	@Autowired
	private IRoleService service;
	
	@PostMapping(path = "insert")
	public void insert(@RequestBody Role role, HttpServletRequest request) {
		
		service.insert(role, request);
		
	}
	
	@GetMapping(path = "listar")
	public List<Role> listar(){
		
		return service.listar();
		
	}
	
	@PutMapping(path = "update")
	public void update (@RequestBody Role role, HttpServletRequest request) {
		
		service.update(role, request);
		
	}
	
	@PutMapping(path = "delete")
	public void delete(@RequestBody Role role, HttpServletRequest request) {
		
		service.delete(role, request);
		
	}
	
	
}
