package com.ejemplo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.model.EstudianteCurso;
import com.ejemplo.service.IEstudianteCursoService;
import com.ejemplo.util.TokenHelperComponent;

@RestController
@RequestMapping(path = "api/estudiante-curso")
public class EstudianteCursoController {

	@Autowired
	private IEstudianteCursoService service;
	
	@Autowired
	private TokenHelperComponent token;
	
	@GetMapping(path = "listar-cursos-estudiante/{estudiante}")
	public List<EstudianteCurso> listar(@PathVariable Integer estudiante) {

		return service.listarCursosPorEstudiante(estudiante);

	}

	@PostMapping(path = "insert")
	public void insert(@RequestBody EstudianteCurso estudiantecurso, HttpServletRequest request) {

		service.insert(estudiantecurso, request);

	}

	@PutMapping(path = "update")
	public void update(@RequestBody EstudianteCurso estudianteCurso, HttpServletRequest request) {

		service.update(estudianteCurso, request);

	}

	@PutMapping(path = "delete")
	public void delete(@RequestBody EstudianteCurso estudianteCurso, HttpServletRequest request) {

		service.delete(estudianteCurso, request);

	}

	@GetMapping(path = "select")
	public List<EstudianteCurso> listar() {

		return service.listar();
	}

	@GetMapping(path = "cursos-pdf/{codigo}")
	public void cursosPdf(HttpServletResponse response, @PathVariable Integer codigo) {

		service.cursosPdf(response, codigo);

	}
	
	@GetMapping(path = "cursos")
	public void cursos() {
		token.convertirAMap("hola");
	}

}
