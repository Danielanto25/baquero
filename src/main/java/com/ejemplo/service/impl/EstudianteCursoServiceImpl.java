package com.ejemplo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.model.EstudianteCurso;
import com.ejemplo.repository.EstudianteCursoRepository;
import com.ejemplo.service.IEstudianteCursoService;

@Service
public class EstudianteCursoServiceImpl implements IEstudianteCursoService{

	@Autowired
	private EstudianteCursoRepository estudianteCurso=new EstudianteCursoRepository();
	
	@Override
	public void insert(EstudianteCurso estudiantecurso) {
		estudianteCurso.insert(estudiantecurso);
		
	}

	@Override
	public List<EstudianteCurso> listar(Integer estudiante) {
		
		return estudianteCurso.listar(estudiante);
	}

}
