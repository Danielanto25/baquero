package com.ejemplo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.EstudianteCurso;

@Repository
public class EstudianteCursoRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;


	
	public EstudianteCurso insertarNotaEstudiante (Integer est_codigo, float definitiva, Integer cur_codigo) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("estudiante", est_codigo);
		parameter.addValue("definitiva", definitiva);
		parameter.addValue("cur_codigo", cur_codigo);
		
		String sql ="update estudiante_curso set ecu_definitiva=:definitiva where ecu_codigo=:codigo";
		
		namedJdbcTemplate.update(sql, parameter);
		
		return null;
	}
}
