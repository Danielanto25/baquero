package com.ejemplo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.dto.InfoAuditoria;
import com.ejemplo.model.Programa;
import com.ejemplo.repository.ProgramaRepository;
import com.ejemplo.service.IProgramaService;
import com.ejemplo.util.InformacionAuditoriaComponent;

@Service
public class ProgramaServiceImpl implements IProgramaService{
	
	@Autowired
	private ProgramaRepository repo;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;


	@Override
	public void insert(Programa programa, HttpServletRequest request) {
		
		repo.insert(programa);
	}

	@Override
	public List<Programa> listar() {
		
		return repo.listar();
	}

	@Override
	public void update(Programa programa, HttpServletRequest request) {
		
		repo.update(programa);
	}

	@Override
	public void delete(Programa programa, HttpServletRequest request) {
		
		repo.delete(programa);
	}
	
	private void llenarDatosAuditoria(Programa programa, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		programa.setCliente(infoAuditoria.getCliente());
		programa.setIp(infoAuditoria.getIp());
		programa.setUsuario(infoAuditoria.getUsuario());

	}
	
	
}
