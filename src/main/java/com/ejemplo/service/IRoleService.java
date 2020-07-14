package com.ejemplo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ejemplo.model.Role;

public interface IRoleService {
	
	public void delete(Role role, HttpServletRequest request);

	public void update(Role role, HttpServletRequest request);

	public void insert(Role role, HttpServletRequest request);

	public List<Role> listar();
}
