package com.ejemplo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.dto.InfoAuditoria;
import com.ejemplo.model.EstudianteCurso;
import com.ejemplo.model.Role;
import com.ejemplo.repository.RoleRepository;
import com.ejemplo.service.IRoleService;
import com.ejemplo.util.InformacionAuditoriaComponent;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private RoleRepository repo;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Override
	public void delete(Role role, HttpServletRequest request) {
		
		llenarDatosAuditoria(role, request);
		
		repo.delete(role);
	}

	@Override
	public void update(Role role, HttpServletRequest request) {
		
		llenarDatosAuditoria(role, request);
		
		repo.update(role);
		
	}

	@Override
	public void insert(Role role, HttpServletRequest request) {
		
		llenarDatosAuditoria(role, request);
		
		repo.insert(role);
		
	}

	@Override
	public List<Role> listar() {

		return repo.listar();
	}
	
	private void llenarDatosAuditoria(Role role, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		role.setCliente(infoAuditoria.getCliente());
		role.setIp(infoAuditoria.getIp());
		role.setUsuario(infoAuditoria.getUsuario());

	}
}
