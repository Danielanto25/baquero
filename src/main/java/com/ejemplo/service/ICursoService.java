package com.ejemplo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ejemplo.model.Curso;

public interface ICursoService {

	public List<Curso> listar();

	public void insert(Curso curso, HttpServletRequest request);

	public void update(Curso curso, HttpServletRequest request);

	public void delete(Curso curso, HttpServletRequest request);

}
