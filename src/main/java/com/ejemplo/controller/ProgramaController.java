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

import com.ejemplo.model.Programa;
import com.ejemplo.service.IProgramaService;

@RestController
@RequestMapping(path = "api/programa")
public class ProgramaController {

	@Autowired
	private IProgramaService service;
	
	@PostMapping(path = "insert")
	public void insert(@RequestBody Programa programa, HttpServletRequest request) {
		
		service.insert(programa,request);
		
	}
	
	@GetMapping(path = "listar")
	public List<Programa> listar(){
		
		return service.listar();
				
	}
	
	@PutMapping(path = "update")
	public void update(@RequestBody Programa programa, HttpServletRequest request) {
		
		service.update(programa,request);
		
	}
	
	@PutMapping(path = "delete")
	public void delete(@RequestBody Programa programa, HttpServletRequest request) {
		
		service.delete(programa,request);
		
	}
	
}
