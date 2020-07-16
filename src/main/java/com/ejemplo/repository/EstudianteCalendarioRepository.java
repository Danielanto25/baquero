package com.ejemplo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Calendario;
import com.ejemplo.model.Estudiante;
import com.ejemplo.model.EstudianteCalendario;
import com.ejemplo.model.Persona;

@Repository
public class EstudianteCalendarioRepository {
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insert(EstudianteCalendario estudianteCalendario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("estado", estudianteCalendario.getEstado());
		parameter.addValue("estudiante", estudianteCalendario.getEstudiante().getCodigo());
		parameter.addValue("calendario", estudianteCalendario.getCalendario().getCodigo());
		parameter.addValue("cliente", estudianteCalendario.getCliente());
		parameter.addValue("usuario", estudianteCalendario.getUsuario());
		parameter.addValue("ip", estudianteCalendario.getIp());

		String sql = "insert into estudiante_calendario(esc_estado,cal_codigo,est_codigo,esc_cliente,esc_usuario,esc_ip) values (:estado,:calendario,:estudiante,:cliente,:usuario,:ip)";

		namedJdbcTemplate.update(sql, parameter);
	}

	public void update(EstudianteCalendario estudianteCalendario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("estado", estudianteCalendario.getEstado());
		parameter.addValue("estudiante", estudianteCalendario.getEstudiante().getCodigo());
		parameter.addValue("calendario", estudianteCalendario.getCalendario().getCodigo());
		parameter.addValue("codigo", estudianteCalendario.getCodigo());
		parameter.addValue("cliente", estudianteCalendario.getCliente());
		parameter.addValue("usuario", estudianteCalendario.getUsuario());
		parameter.addValue("ip", estudianteCalendario.getIp());

		String sql = "update estudiante_calendario set esc_estado=:estado,cal_codigo=:calendario,est_codigo=:estudiante,"
				+ "esc_cliente=:cliente,esc_usuario=:usuario,esc_ip=:ip where esc_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}
	public void delete(EstudianteCalendario estudianteCalendario) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("estado", 0);
		parameter.addValue("codigo", estudianteCalendario.getCodigo());
		parameter.addValue("cliente", estudianteCalendario.getCliente());
		parameter.addValue("usuario", estudianteCalendario.getUsuario());
		parameter.addValue("ip", estudianteCalendario.getIp());
		
		
		String sql = "update estudiante_calendario set esc_estado=:estado,esc_cliente=:cliente,esc_usuario=:usuario,esc_ip=:ip where esc_codigo=:codigo";
		
		namedJdbcTemplate.update(sql, parameter);

	}
	
	public List<EstudianteCalendario> listar() {

		String sql = "select * FROM  estudiante_calendario ec  join calendario c on ec.cal_codigo = c.cal_codigo join "
				+ "estudiante e on ec.est_codigo =e.est_codigo join persona p on e.per_codigo = p.per_codigo";

		List<EstudianteCalendario> lstEstudianteCalendario = namedJdbcTemplate.query(sql, new RowMapper<EstudianteCalendario>() {

			@Override
			public EstudianteCalendario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Calendario calendario= new Calendario();
				calendario.setNombre(rs.getString("cal_nombre"));
				calendario.setCodigo(rs.getInt("cal_codigo"));
				
				Persona persona=new Persona();
				persona.setNombre(rs.getString("per_nombre"));
				persona.setCodigo(rs.getInt("per_codigo"));
				
				Estudiante estudiante=new Estudiante();
				estudiante.setPersona(persona);
				
				EstudianteCalendario estudianteCalendario = new EstudianteCalendario();
				estudianteCalendario.setCodigo(rs.getInt("esc_codigo"));
				estudianteCalendario.setEstado(rs.getInt("esc_estado"));
				estudianteCalendario.setCalendario(calendario);
				estudianteCalendario.setEstudiante(estudiante);

				return estudianteCalendario;
			}

		});

		return lstEstudianteCalendario;

	}
}
