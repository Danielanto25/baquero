package com.ejemplo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ejemplo.model.Persona;

public interface IPersonaService {

	public void insert(Persona persona,HttpServletRequest request);

	public List<Persona> select();

	public void update(Persona persona,HttpServletRequest request);

	public void delete(Persona persona,HttpServletRequest request);

}
