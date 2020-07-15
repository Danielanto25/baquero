package com.ejemplo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.dto.InfoAuditoria;
import com.ejemplo.model.Calendario;
import com.ejemplo.repository.CalendarioRepository;
import com.ejemplo.service.ICalendarioService;
import com.ejemplo.util.InformacionAuditoriaComponent;

@Service
public class CalendarioServiceImpl implements ICalendarioService{

	@Autowired
	private CalendarioRepository repo;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;
	

	@Override
	public void insert(Calendario calendario, HttpServletRequest request) {
		
		llenarDatosAuditoria(calendario, request);
		
		repo.insert(calendario);
		
	}

	@Override
	public void update(Calendario calendario, HttpServletRequest request) {
		
		llenarDatosAuditoria(calendario, request);

		repo.update(calendario);
		
	}

	@Override
	public void delete(Calendario calendario, HttpServletRequest request) {
		
		llenarDatosAuditoria(calendario, request);
		
		repo.delete(calendario);
		
	}

	@Override
	public List<Calendario> listar() {
		
		return repo.listar();
	}
	
	private void llenarDatosAuditoria(Calendario calendario, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		calendario.setCliente(infoAuditoria.getCliente());
		calendario.setIp(infoAuditoria.getIp());
		calendario.setUsuario(infoAuditoria.getUsuario());

	}
	
	
}
