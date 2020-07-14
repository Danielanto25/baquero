package com.ejemplo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.dto.InfoAuditoria;
import com.ejemplo.model.EstudianteCurso;
import com.ejemplo.repository.EstudianteCursoRepository;
import com.ejemplo.service.IEstudianteCursoService;
import com.ejemplo.util.InformacionAuditoriaComponent;

@Service
public class EstudianteCursoServiceImpl implements IEstudianteCursoService{

	@Autowired
	private EstudianteCursoRepository estudianteCursoRepository=new EstudianteCursoRepository();
	

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	
	@Override
	public void insert(EstudianteCurso estudiantecurso, HttpServletRequest request) {
		
		llenarDatosAuditoria(estudiantecurso, request);
		
		estudianteCursoRepository.insert(estudiantecurso);
		
	}

	@Override
	public List<EstudianteCurso> listarCursosPorEstudiante(Integer estudiante) {
		
		return estudianteCursoRepository.listarCursosPorEstudiante(estudiante);
	}

	@Override
	public void update(EstudianteCurso estudianteCurso, HttpServletRequest request) {
	
		llenarDatosAuditoria(estudianteCurso, request);
		estudianteCursoRepository.update(estudianteCurso);
		
	}

	@Override
	public void delete(EstudianteCurso estudianteCurso, HttpServletRequest request) {
		
		llenarDatosAuditoria(estudianteCurso, request);
		
		estudianteCursoRepository.delete(estudianteCurso);
	}

	@Override
	public List<EstudianteCurso> listar() {
		
		return estudianteCursoRepository.listar();
	}
	private void llenarDatosAuditoria(EstudianteCurso estudianteCurso, HttpServletRequest request) {

		InfoAuditoria  infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		estudianteCurso.setCliente(infoAuditoria.getCliente());
		estudianteCurso.setIp(infoAuditoria.getIp());
		estudianteCurso.setUsuario(infoAuditoria.getUsuario());

	}


}
