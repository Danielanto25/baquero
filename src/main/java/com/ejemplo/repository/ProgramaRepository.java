package com.ejemplo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Programa;

@Repository
public class ProgramaRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public void insert(Programa programa) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", programa.getNombre());
		parameter.addValue("estado", programa.getEstado());
		parameter.addValue("ip", programa.getIp());
		parameter.addValue("usuario", programa.getUsuario());
		parameter.addValue("cliente", programa.getCliente());
		
		String sql="insert into programa(pro_nombre,pro_estado,pro_cliente,pro_usuario,pro_ip) values (:nombre,:estado,:cliente,:usuario,:ip)";
		
		namedJdbcTemplate.update(sql, parameter);
	}
	
	public List<Programa> listar() {

		String sql = "select * from programa";

		List<Programa> lstPrograma = namedJdbcTemplate.query(sql, new RowMapper<Programa>() {

			@Override
			public Programa mapRow(ResultSet rs, int rowNum) throws SQLException {

				Programa programa = new Programa();
				programa.setCodigo(rs.getInt("pro_codigo"));
				programa.setNombre(rs.getString("pro_nombre"));
				programa.setEstado(rs.getInt("pro_estado"));

				return programa;
			}

		});

		return lstPrograma;

	}
	
	public void update(Programa programa) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", programa.getNombre());
		parameter.addValue("codigo", programa.getCodigo());
		parameter.addValue("estado", programa.getEstado());
		parameter.addValue("ip", programa.getIp());
		parameter.addValue("usuario", programa.getUsuario());
		parameter.addValue("cliente", programa.getCliente());
		
		String sql="update programa set pro_nombre=:nombre, pro_estado=:estado,pro_cliente=:cliente,pro_usuario=:usuario,pro_ip=:ip where pro_codigo=:codigo";
		
		namedJdbcTemplate.update(sql, parameter);
	}
	
	public void delete(Programa programa) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", programa.getCodigo());
		parameter.addValue("estado", 0);
		parameter.addValue("ip", programa.getIp());
		parameter.addValue("usuario", programa.getUsuario());
		parameter.addValue("cliente", programa.getCliente());
		
		String sql="update programa set pro_nombre=:nombre, pro_estado=:estado,pro_cliente=:cliente,pro_usuario=:usuario,pro_ip=:ip where pro_codigo=:codigo";
		
		namedJdbcTemplate.update(sql, parameter);
	}
	
	
}
