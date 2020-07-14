package com.ejemplo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ejemplo.model.Usuario;

public interface IUsuarioService {

	public void insert(Usuario usuario, HttpServletRequest request);

	public List<Usuario> listar();

	public void update(Usuario usuario, HttpServletRequest request);

	public void delete(Usuario usuario, HttpServletRequest request);
}
