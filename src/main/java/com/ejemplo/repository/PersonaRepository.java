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
	
	public void insert(Persona persona) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", persona.getNombre());
		parameter.addValue("apellido", persona.getApellido());
		parameter.addValue("estado", persona.getEstado());
		parameter.addValue("identificacion",persona.getIdentificacion());
		parameter.addValue("ip",persona.getIp() );
		parameter.addValue("usuario", persona.getUsuario());
		parameter.addValue("cliente", persona.getCliente());
		
		String sql="insert into persona (per_nombre,per_apellido,per_identificacion,per_ip,per_usuario,per_cliente,per_estado) "
				+ "values(:nombre,:apellido,:identificacion,:ip,:usuario,:cliente,:estado)";
		
		namedJdbcTemplate.update(sql, parameter);
		
		
	}
	
	public List<Persona> select(){
		
		String sql="select * from persona ";
		
		List<Persona> lstPersona= namedJdbcTemplate.query(sql, new RowMapper<Persona>() {
			
			@Override
			public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Persona per=new Persona();
				per.setApellido(rs.getString("per_apellido"));
				per.setNombre(rs.getString("per_nombre"));
				per.setIdentificacion(rs.getString("per_identificacion"));
				per.setEstado(rs.getInt("per_estado"));
				per.setIp(rs.getString("per_ip"));
				per.setUsuario(rs.getString("per_usuario"));
				per.setCodigo(rs.getInt("per_codigo"));
				per.setCliente(rs.getString("per_cliente"));

				return per;
			}
			
		});
		return lstPersona;

	}
	
	public void update(Persona persona) {
		

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", persona.getNombre());
		parameter.addValue("apellido", persona.getApellido());
		parameter.addValue("estado", persona.getEstado());
		parameter.addValue("identificacion",persona.getIdentificacion());
		parameter.addValue("ip",persona.getIp() );
		parameter.addValue("usuario", persona.getUsuario());
		parameter.addValue("cliente", persona.getCliente());
		parameter.addValue("codigo", persona.getCodigo());
		
		String sql="update persona set per_nombre=:nombre,per_apellido=:apellido,per_identificacion=:identificacion,"
				+ "per_ip=:ip,per_usuario=:usuario,per_cliente=:cliente,per_estado=:estado where per_codigo=:codigo ";
		
		namedJdbcTemplate.update(sql, parameter);
	}
	
	public void delete(Persona persona) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("estado", 0);
		parameter.addValue("codigo", persona.getCodigo());
		parameter.addValue("ip",persona.getIp() );
		parameter.addValue("usuario", persona.getUsuario());
		parameter.addValue("cliente", persona.getCliente());
		
		String sql="update persona set per_estado=:estado,per_ip=:ip,per_usuario=:usuario,per_cliente=:cliente where per_codigo=:codigo ";
		
		namedJdbcTemplate.update(sql, parameter);
		
	}
	
}
