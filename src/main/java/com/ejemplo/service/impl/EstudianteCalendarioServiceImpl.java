package com.ejemplo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.dto.InfoAuditoria;
import com.ejemplo.model.EstudianteCalendario;
import com.ejemplo.repository.EstudianteCalendarioRepository;
import com.ejemplo.service.IEstudianteCalendarioService;
import com.ejemplo.util.InformacionAuditoriaComponent;

@Service
public class EstudianteCalendarioServiceImpl implements IEstudianteCalendarioService{

	@Autowired
	private EstudianteCalendarioRepository repo;
	
	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;


	@Override
	public void insert(EstudianteCalendario estudianteCalendario, HttpServletRequest request) {
		
		llenarDatosAuditoria(estudianteCalendario, request);
		
		repo.insert(estudianteCalendario);
		
	}

	@Override
	public void update(EstudianteCalendario estudianteCalendario, HttpServletRequest request) {
		
		
		llenarDatosAuditoria(estudianteCalendario, request);
		
		repo.update(estudianteCalendario);
		
	}

	@Override
	public void delete(EstudianteCalendario estudianteCalendario, HttpServletRequest request) {
		
		llenarDatosAuditoria(estudianteCalendario, request);
		
		repo.delete(estudianteCalendario);
		
	}

	@Override
	public List<EstudianteCalendario> listar() {
		
		return repo.listar();
		
	}
	
	private void llenarDatosAuditoria(EstudianteCalendario estudianteCalendario, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		estudianteCalendario.setCliente(infoAuditoria.getCliente());
		estudianteCalendario.setIp(infoAuditoria.getIp());
		estudianteCalendario.setUsuario(infoAuditoria.getUsuario());

	}
	
	
}
