package com.ejemplo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ejemplo.model.Calendario;

public interface ICalendarioService {

	public void insert(Calendario calendario, HttpServletRequest request);

	public void update(Calendario calendario, HttpServletRequest request);

	public void delete(Calendario calendario, HttpServletRequest request);

	public List<Calendario> listar();
}
