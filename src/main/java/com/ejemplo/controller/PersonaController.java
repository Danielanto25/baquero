package com.ejemplo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.model.Persona;
import com.ejemplo.service.IPersonaService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/persona")
public class PersonaController {
	

		@Autowired
		private  IPersonaService service;

		@PostMapping(path = "crear")
		public void crear(@RequestBody Persona persona, HttpServletRequest request) {
			service.insertar(persona, request);
			
		}

		@GetMapping(path = "listar")
		public List<Persona> listar() {
			return service.select();
		}

		@PutMapping(path = "update")
		public void update(@RequestBody Persona persona, HttpServletRequest request) {
			service.update(persona, request);
		}
		@PutMapping(path = "eliminar/{codigo}")
		public void eliminar(@PathVariable Persona persona, HttpServletRequest request) {
			service.delete(persona, request);
		}
}
