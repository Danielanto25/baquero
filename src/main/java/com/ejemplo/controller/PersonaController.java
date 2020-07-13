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

import com.ejemplo.model.Persona;
import com.ejemplo.service.IPersonaService;

@RestController
@RequestMapping(path = "api/persona")
public class PersonaController {

	@Autowired
	private IPersonaService service;

	@PostMapping(path = "insert")
	public void insert(@RequestBody Persona persona, HttpServletRequest request) {
		service.insert(persona, request);
	}

	@GetMapping(path = "select")
	public List<Persona> select() {

		return service.select();
	}

	@PutMapping(path = "update")
	public void update(@RequestBody Persona persona, HttpServletRequest request) {

		service.update(persona, request);
	}

	@PutMapping(path = "delete")
	public void delete(@RequestBody Persona persona, HttpServletRequest request) {

		service.delete(persona, request);

	}

}
