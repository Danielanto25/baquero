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
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Override
	public void insert(Persona persona, HttpServletRequest request) {

		llenarDatosAuditoria(persona, request);

		personaRepository.insert(persona);

	}

	@Override
	public List<Persona> select() {

		return personaRepository.select();
	}

	@Override
	public void update(Persona persona, HttpServletRequest request) {

		llenarDatosAuditoria(persona, request);

		personaRepository.update(persona);

	}

	@Override
	public void delete(Persona persona, HttpServletRequest request) {

		llenarDatosAuditoria(persona, request);

		personaRepository.delete(persona);

	}

	private void llenarDatosAuditoria(Persona persona, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		persona.setCliente(infoAuditoria.getCliente());
		persona.setIp(infoAuditoria.getIp());
		persona.setUsuario(infoAuditoria.getUsuario());

	}

}
