package com.ejemplo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Persona;

@Repository
public class PersonaRepository {
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insertar(Persona persona) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("nombre", persona.getNombre());
		parameter.addValue("apellido", persona.getApellido());
		parameter.addValue("identificacion", persona.getIdentificacion());
		parameter.addValue("ip", persona.getIp());
		parameter.addValue("usuario", persona.getUsuario());
		parameter.addValue("cliente", persona.getCliente());
		parameter.addValue("estado", persona.getEstado());

		String sql = "insert into persona (per_nombre, per_apellido, per_identificacion,per_ip,per_usuario, per_cliente, per_estado) "
				+ "values(:nombre, :apellido, :identificacion, :ip, :usuario, :cliente, :estado)";

		namedJdbcTemplate.update(sql, parameter);
	}

	public List<Persona> select() {

		String sql = "select * from persona";

		List<Persona> lstPersona = namedJdbcTemplate.query(sql, new RowMapper<Persona>() {

			@Override
			public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {

				Persona persona = new Persona();

				persona.setCodigo(rs.getInt("per_codigo"));
				persona.setNombre(rs.getString("per_nombre"));
				persona.setApellido(rs.getString("per_apellido"));
				persona.setIdentificacion(rs.getString("per_identificacion"));
				persona.setIp(rs.getString("per_ip"));
				persona.setUsuario(rs.getString("per_usuario"));
				persona.setCliente(rs.getString("per_cliente"));
				persona.setEstado(rs.getInt("per_estado"));

				return persona;
			}

		});

		return lstPersona;
	}

	public void update(Persona persona) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("nombre", persona.getNombre());
		parameter.addValue("apellido", persona.getApellido());
		parameter.addValue("identificacion", persona.getIdentificacion());
		parameter.addValue("ip", persona.getIp());
		parameter.addValue("usuario", persona.getUsuario());
		parameter.addValue("cliente", persona.getIdentificacion());
		parameter.addValue("estado", persona.getEstado());
		parameter.addValue("codigo", persona.getCodigo());
		
		String sql = "UPDATE persona SET per_nombre=:nombre, per_apellido=:apellido, per_identificacion=:identificacion, per_ip=:ip,"
				+ "per_usuario=:usuario, per_cliente=:cliente, per_estado=:estado WHERE per_codigo=:codigo";
		
		namedJdbcTemplate.update(sql, parameter);
	}
	
	public void delete(Persona persona) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		
		parameter.addValue("ip", persona.getIp());
		parameter.addValue("usuario", persona.getUsuario());
		parameter.addValue("cliente", persona.getIdentificacion());	
		parameter.addValue("codigo", persona.getCodigo());
		
		String sql = "update persona set per_estado=0 where per_codigo=:codigo";
		
		namedJdbcTemplate.update(sql, parameter);
		
	}
}
