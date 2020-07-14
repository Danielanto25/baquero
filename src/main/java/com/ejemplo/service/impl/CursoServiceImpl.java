package com.ejemplo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.dto.InfoAuditoria;
import com.ejemplo.model.Curso;
import com.ejemplo.repository.CursoRepository;
import com.ejemplo.service.ICursoService;
import com.ejemplo.util.InformacionAuditoriaComponent;

@Service
public class CursoServiceImpl implements ICursoService{

	@Autowired
	CursoRepository repository= new CursoRepository();
	
	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;
	
	
	@Override
	public List<Curso> listar() {
		
		return repository.listar();
	}

	@Override
	public void insert(Curso curso, HttpServletRequest request) {
		
		llenarDatosAuditoria(curso, request);
		
		repository.insert(curso);
		
	}

	@Override
	public void update(Curso curso, HttpServletRequest request) {

		llenarDatosAuditoria(curso, request);
		
		repository.update(curso);
		
	}

	@Override
	public void delete(Curso curso, HttpServletRequest request) {
		
		llenarDatosAuditoria(curso, request);
		
		repository.delete(curso);
		
		
	}

	private void llenarDatosAuditoria(Curso curso, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		curso.setCliente(infoAuditoria.getCliente());
		curso.setIp(infoAuditoria.getIp());
		curso.setUsuario(infoAuditoria.getUsuario());

	}
	
}
