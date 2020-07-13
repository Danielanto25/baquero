package com.ejemplo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.model.EstudianteCurso;
import com.ejemplo.service.IEstudianteCursoService;

@RestController
@RequestMapping(path = "api/estudiante-curso")
public class EstudianteCursoController {
	
	@Autowired
	private IEstudianteCursoService service;
		
		
		
		@GetMapping(path = "listar/{estudiante}")
		public List<EstudianteCurso> listar(@PathVariable Integer estudiante) {
			
			return service.listar(estudiante);
			
		}
		
		@PostMapping(path = "insert")
		public void insert( @RequestBody EstudianteCurso estudiantecurso) {
			
			service.insert(estudiantecurso);
			
		}
	
	
}
