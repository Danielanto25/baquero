package com.ejemplo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ejemplo.model.UsuarioRole;

public interface IUsuarioRoleService {

	public List<UsuarioRole> listar();

	public void insert(UsuarioRole usuarioRole, HttpServletRequest request);

	public void update(UsuarioRole usuarioRole, HttpServletRequest request);

	public void delete(UsuarioRole usuarioRole, HttpServletRequest request);

}
