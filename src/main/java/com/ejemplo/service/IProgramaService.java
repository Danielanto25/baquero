package com.ejemplo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ejemplo.model.Programa;

public interface IProgramaService {

	public void insert(Programa programa, HttpServletRequest request);

	public List<Programa> listar();

	public void update(Programa programa, HttpServletRequest request);

	public void delete(Programa programa, HttpServletRequest request);
}
