package com.ejemplo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ejemplo.model.Persona;

public interface IPersonaService {
		
		void insertar(Persona persona, HttpServletRequest request);

		List<Persona> select();

		void update(Persona persona, HttpServletRequest request);

		void delete(Persona persona, HttpServletRequest request);
		
	}
