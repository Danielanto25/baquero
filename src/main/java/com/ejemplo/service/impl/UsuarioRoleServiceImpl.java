package com.ejemplo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.dto.InfoAuditoria;
import com.ejemplo.model.UsuarioRole;
import com.ejemplo.repository.UsuarioRoleRepository;
import com.ejemplo.service.IUsuarioRoleService;
import com.ejemplo.util.InformacionAuditoriaComponent;

@Service
public class UsuarioRoleServiceImpl implements IUsuarioRoleService{

	@Autowired
	private UsuarioRoleRepository repo;
	
	
	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	
	

	@Override
	public List<UsuarioRole> listar() {
		
		return repo.listar();
	}


	@Override
	public void insert(UsuarioRole usuarioRole, HttpServletRequest request) {
		
		llenarDatosAuditoria(usuarioRole, request);

		repo.insert(usuarioRole);
		
	}


	@Override
	public void update(UsuarioRole usuarioRole, HttpServletRequest request) {
		
		llenarDatosAuditoria(usuarioRole, request);
		
		repo.update(usuarioRole);
		
	}


	@Override
	public void delete(UsuarioRole usuarioRole, HttpServletRequest request) {
		
		llenarDatosAuditoria(usuarioRole, request);
		
		repo.delete(usuarioRole);
		
	}
	
	private void llenarDatosAuditoria(UsuarioRole usuarioRole, HttpServletRequest request) {

		InfoAuditoria  infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		usuarioRole.setCliente(infoAuditoria.getCliente());
		usuarioRole.setIp(infoAuditoria.getIp());
		usuarioRole.setUsuario(infoAuditoria.getUsuario());

	}

}
