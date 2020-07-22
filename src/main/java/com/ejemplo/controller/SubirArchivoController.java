package com.ejemplo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ejemplo.model.TipoTrabajo;
import com.ejemplo.service.ISubirArchivoService;

@RestController
@RequestMapping(path = "api/subir-archivo")
public class SubirArchivoController {
	
	@Autowired
	private ISubirArchivoService service;
	
	@PostMapping(path = "subir")
	public void subirArchivo(@RequestPart MultipartFile archivo,@RequestParam String json) throws IOException {
		
		service.guardarArchivo(archivo,json);
		
	}
	
	@GetMapping(path = "listar")
	public List<TipoTrabajo> listarTrabajo(){
		
		return service.apiTipoTrabajo();
		
	}
}
