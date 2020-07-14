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

import com.ejemplo.model.Calendario;
import com.ejemplo.service.ICalendarioService;

@RestController
@RequestMapping(path = "api/calendario")
public class CalendarioController {

	@Autowired
	private ICalendarioService service;
	
	@PostMapping(path = "insert")
	public void insert(@RequestBody Calendario calendario, HttpServletRequest request) {
		
		service.insert(calendario,request);
		
	}
	
	@GetMapping(path = "listar")
	public List<Calendario> listar(){
		
		return service.listar();
		
		
	}
	
	@PutMapping(path = "update")
	public void update(Calendario calendario, HttpServletRequest request) {
		
		service.update(calendario,request);
		
	}
	
	@PutMapping(path = "delete")
	public void delete(Calendario calendario, HttpServletRequest request) {
		
		service.delete(calendario,request);
		
	}
	
}
