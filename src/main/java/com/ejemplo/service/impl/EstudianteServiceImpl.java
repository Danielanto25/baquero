package com.ejemplo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.dto.InfoAuditoria;
import com.ejemplo.model.Estudiante;
import com.ejemplo.repository.EstudianteRepository;
import com.ejemplo.service.IEstudianteService;
import com.ejemplo.util.InformacionAuditoriaComponent;
import com.ejemplo.util.TokenHelperComponent;

@Service
public class EstudianteServiceImpl implements IEstudianteService{
	
	@Autowired
	private EstudianteRepository repo;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;
	
	@Autowired
	private TokenHelperComponent tokenHelper;
	
	
	
	@Override
	public List<Estudiante> listar() {
		
		return repo.listar();
	}


	@Override
	public void insert(Estudiante estudiante, HttpServletRequest request) {
		
		llenarDatosAuditoria(estudiante, request);
		
		repo.insert(estudiante);
	}


	@Override
	public void update(Estudiante estudiante, HttpServletRequest request) {
		
		llenarDatosAuditoria(estudiante, request);
		
		repo.update(estudiante);
	}


	@Override
	public void delete(Estudiante estudiante, HttpServletRequest request) {
		
		llenarDatosAuditoria(estudiante, request);
		
		repo.delete(estudiante);
	}

	private void llenarDatosAuditoria(Estudiante estudiante, HttpServletRequest request) {

		InfoAuditoria infoAuditoria  = informacionAuditoriaComponent.getInfoAuditoria(request);

		estudiante.setCliente(infoAuditoria.getCliente());
		estudiante.setIp(infoAuditoria.getIp());
		estudiante.setUsuario(infoAuditoria.getUsuario());

	}


	@Override
	public Estudiante listarCodigo(String token) {
		
		String usuario=tokenHelper.obtenerUsuarioDelToken(token);
		
		return repo.listarCodigo(usuario);
	}
}
