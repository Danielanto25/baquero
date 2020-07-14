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

import com.ejemplo.model.Curso;
import com.ejemplo.service.ICursoService;

@RestController
@RequestMapping(path = "api/curso")
public class CursoController {

	@Autowired
	private ICursoService service;
	
	
	@GetMapping(path = "listar")
	public List<Curso> listar(){
		
		return service.listar();
		
	}
	
	@PostMapping(path = "insert")
	public void insert(@RequestBody Curso curso, HttpServletRequest request) {
		
		service.insert(curso, request);
		
	}
	
	@PutMapping(path = "update")
	public void update(@RequestBody Curso curso, HttpServletRequest request) {
		
		service.update(curso, request);
		
	}
	
	@PutMapping(path = "delete")
	public void delete(@RequestBody Curso curso, HttpServletRequest request) {
		
		service.delete(curso, request);
		
	}
}
