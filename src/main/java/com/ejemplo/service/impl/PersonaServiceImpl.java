package com.ejemplo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.dto.InfoAuditoria;
import com.ejemplo.model.Persona;
import com.ejemplo.repository.PersonaRepository;
import com.ejemplo.service.IPersonaService;
import com.ejemplo.util.InformacionAuditoriaComponent;

@Service
public class PersonaServiceImpl implements IPersonaService{
	
	@Autowired
	private PersonaRepository repoPer;
	
	@Autowired
	private InformacionAuditoriaComponent informacionAuditoria;


	@Override
	public List<Persona> select() {
		
		return repoPer.select();
	}

	@Override
	public void update(Persona persona, HttpServletRequest request) {
		
		llenarDatosAuditoria(persona, request);
		repoPer.update(persona);
		
	}


	@Override
	public void insertar(Persona persona,HttpServletRequest request) {
		
		llenarDatosAuditoria(persona, request);
		repoPer.insertar(persona);
		
	}
	

	
	private void llenarDatosAuditoria(Persona persona,HttpServletRequest request) {
		
		InfoAuditoria infoAuditoria = informacionAuditoria.getInfoAuditoria(request);
		persona.setIp(infoAuditoria.getIp());
		persona.setCliente(infoAuditoria.getCliente());
		persona.setUsuario(infoAuditoria.getUsuario());
		
	}

	@Override
	public void delete(Persona persona, HttpServletRequest request) {
		
		llenarDatosAuditoria(persona, request);
		repoPer.delete(persona);
		
	}
	
}
