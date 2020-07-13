package com.ejemplo.service;

import java.util.List;

import com.ejemplo.model.EstudianteCurso;

public interface IEstudianteCursoService {

	public void insert(EstudianteCurso estudiantecurso);
	public List<EstudianteCurso> listar(Integer estudiante);
}
