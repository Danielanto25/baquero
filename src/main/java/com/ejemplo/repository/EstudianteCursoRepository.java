package com.ejemplo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Curso;
import com.ejemplo.model.EstudianteCurso;

@Repository
public class EstudianteCursoRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	
	public void insert(EstudianteCurso estudiantecurso) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigoEstudianteCalendario", estudiantecurso.getEstudianteCalendario().getCodigo());
		parameter.addValue("curso", estudiantecurso.getCurso().getCodigo());
		
		
		String sql="insert into estudiante_curso(esc_codigo,cur_codigo)values(:codigoEstudianteCalendario,:curso)";
		
		namedJdbcTemplate.update(sql, parameter);
	}

	public List<EstudianteCurso> listar(Integer estudiante){
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", estudiante);
		
		String sql="\r\n" + 
				"select cur_nombre from  estudiante  join "
				+ "estudiante_calendario on estudiante.est_codigo=estudiante_calendario.est_codigo join "
				+ "estudiante_curso on estudiante_calendario.esc_codigo =estudiante_curso.esc_codigo join"
				+ " curso on  estudiante_curso.cur_codigo=curso.cur_codigo where estudiante.est_codigo=:codigo";
		
		
		List<EstudianteCurso> lstEstudianteCuerso = namedJdbcTemplate.query(sql,parameter, new RowMapper<EstudianteCurso>() {

			@Override
			public EstudianteCurso mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				EstudianteCurso estudianteCurso=new EstudianteCurso();
				Curso curso=new Curso();
				curso.setNombre(rs.getString("cur_nombre"));
				estudianteCurso.setCurso(curso);

				return estudianteCurso;
			}
			
		});
		
		if(lstEstudianteCuerso.size()==0) {
			throw new RuntimeException("el estudiante no existe o no tiene cursos registrados -> "+estudiante);
		}
		
		return lstEstudianteCuerso;
	}
}
