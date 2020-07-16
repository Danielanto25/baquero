package com.ejemplo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.dto.InfoAuditoria;
import com.ejemplo.model.EstudianteCurso;
import com.ejemplo.repository.EstudianteCursoRepository;
import com.ejemplo.service.IEstudianteCursoService;
import com.ejemplo.util.InformacionAuditoriaComponent;
import com.ejemplo.util.JasperReportComponent;
import com.ejemplo.dto.JasperData;


@Service
public class EstudianteCursoServiceImpl implements IEstudianteCursoService{

	@Autowired
	private EstudianteCursoRepository estudianteCursoRepository=new EstudianteCursoRepository();
	

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Autowired
	private JasperReportComponent jasperComponent;
	
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
	
	
	@Override
	public void cursosPdf(HttpServletResponse response,Integer codigo) {

		// List<TotalExperiencia> lstTotalExp = tiempoIndividual();
		List<EstudianteCurso> lstCursos = listarCursosPorEstudiante(codigo);

		JasperData jasper = new JasperData();

		Map<String, Object> dataSource = new HashMap<>();

		dataSource.put("materias", lstCursos);

		jasper.setPathJrxml("/static/reporte/pdf/reporte2.jrxml");
		jasper.setResponse(response);
		jasper.setDataSource(dataSource);

		try {
			jasperComponent.exportToPdf(jasper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void llenarDatosAuditoria(EstudianteCurso estudianteCurso, HttpServletRequest request) {

		InfoAuditoria  infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		estudianteCurso.setCliente(infoAuditoria.getCliente());
		estudianteCurso.setIp(infoAuditoria.getIp());
		estudianteCurso.setUsuario(infoAuditoria.getUsuario());

	}

	@Override
	public void notasPdf(HttpServletResponse response, Integer codigo) {
		
		List<EstudianteCurso> lstCursos = listarCursosPorEstudiante(codigo);

		JasperData jasper = new JasperData();

		Map<String, Object> dataSource = new HashMap<>();

		dataSource.put("estudiante", lstCursos);

		jasper.setPathJrxml("/static/reportes/pdf/notas.jrxml");
		jasper.setResponse(response);
		jasper.setDataSource(dataSource);

		try {
			jasperComponent.exportToPdf(jasper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
