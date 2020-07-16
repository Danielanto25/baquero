package com.ejemplo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejemplo.model.EstudianteCurso;

public interface IEstudianteCursoService {

	public void insert(EstudianteCurso estudiantecurso, HttpServletRequest request);

	public List<EstudianteCurso> listarCursosPorEstudiante(Integer estudiante);

	public void update(EstudianteCurso estudianteCurso, HttpServletRequest request);

	public void delete(EstudianteCurso estudianteCurso, HttpServletRequest request);

	public List<EstudianteCurso> listar();

	public void cursosPdf(HttpServletResponse response,Integer codigo);
	
	public void notasPdf(HttpServletResponse response,Integer codigo);
}
