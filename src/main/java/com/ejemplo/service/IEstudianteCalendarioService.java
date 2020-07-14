package com.ejemplo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ejemplo.model.EstudianteCalendario;

public interface IEstudianteCalendarioService {

	public void insert(EstudianteCalendario estudianteCalendario, HttpServletRequest request);

	public void update(EstudianteCalendario estudianteCalendario, HttpServletRequest request);

	public void delete(EstudianteCalendario estudianteCalendario, HttpServletRequest request);

	public List<EstudianteCalendario> listar();

}
