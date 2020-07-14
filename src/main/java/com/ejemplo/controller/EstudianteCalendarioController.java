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

import com.ejemplo.model.EstudianteCalendario;
import com.ejemplo.service.IEstudianteCalendarioService;

@RestController
@RequestMapping(path = "api/estudiante-calendario")
public class EstudianteCalendarioController {

	@Autowired
	private IEstudianteCalendarioService service;

	@PostMapping(path = "insert")
	public void insert(@RequestBody EstudianteCalendario estudianteCalendario, HttpServletRequest request) {
		
		service.insert(estudianteCalendario,request);

	}

	@PutMapping(path = "update")
	public void update(@RequestBody EstudianteCalendario estudianteCalendario, HttpServletRequest request) {
		
		service.update(estudianteCalendario,request);

	}
	
	@PutMapping(path = "delete")
	public void delete(@RequestBody EstudianteCalendario estudianteCalendario, HttpServletRequest request) {
		
		service.delete(estudianteCalendario,request);
		
	}
	
	@GetMapping(path = "listar")
	public List<EstudianteCalendario> listar(){
		return service.listar();
	}

}
