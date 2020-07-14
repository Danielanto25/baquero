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

import com.ejemplo.model.Estudiante;
import com.ejemplo.service.IEstudianteService;

@RestController
@RequestMapping(path = "api/estudiante")
public class EstudianteController {
	
	@Autowired
	private IEstudianteService service;
	
	@GetMapping(path = "listar")
	public List<Estudiante> listar(){
		
		return service.listar();
	}
	
	@PutMapping(path = "delete")
	public void delete(@RequestBody Estudiante estudiante, HttpServletRequest request) {
		service.delete(estudiante,request);
	}
	
	@PostMapping(path = "insert")
	public void insert(@RequestBody Estudiante estudiante, HttpServletRequest request) {
		service.insert(estudiante,request);
	}
	
	@PutMapping(path = "update")
	public void update(@RequestBody Estudiante estudiante, HttpServletRequest request) {
		service.update(estudiante,request);
	}
}
