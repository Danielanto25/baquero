package com.ejemplo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ejemplo.model.Estudiante;

public interface IEstudianteService {

	public List<Estudiante> listar();

	public void insert(Estudiante estudiante, HttpServletRequest request);

	public void update(Estudiante estudiante, HttpServletRequest request);

	public void delete(Estudiante estudiante, HttpServletRequest request);
}
