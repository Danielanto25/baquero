package com.ejemplo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.dto.InfoAuditoria;
import com.ejemplo.model.Usuario;
import com.ejemplo.repository.UsuarioRepository;
import com.ejemplo.service.IUsuarioService;
import com.ejemplo.util.InformacionAuditoriaComponent;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Override
	public void insert(Usuario usuario, HttpServletRequest request) {

		llenarDatosAuditoria(usuario, request);

		repo.insert(usuario);
	}

	@Override
	public List<Usuario> listar() {

		return repo.listar();
	}

	@Override
	public void update(Usuario usuario, HttpServletRequest request) {

		llenarDatosAuditoria(usuario, request);

		repo.update(usuario);

	}

	@Override
	public void delete(Usuario usuario, HttpServletRequest request) {

		llenarDatosAuditoria(usuario, request);

		repo.delete(usuario);

	}

	private void llenarDatosAuditoria(Usuario user, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		user.setCliente(infoAuditoria.getCliente());
		user.setIp(infoAuditoria.getIp());
		user.setUsuarioUsuario(infoAuditoria.getUsuario());

	}

	@Override
	public List<String> listarRole(String usuario) {
		
		return repo.buscarRolePorUsuario(usuario);
	}

}
