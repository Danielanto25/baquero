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

@Repository
public class CursoRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public List<Curso> listar() {

		String sql = "select * from curso";

		List<Curso> lstCurso = namedJdbcTemplate.query(sql, new RowMapper<Curso>() {

			@Override
			public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {

				Curso curso = new Curso();
				curso.setCliente(rs.getString("cur_cliente"));
				curso.setCodigo(rs.getInt("cur_codigo"));
				curso.setEstado(rs.getInt("cur_estado"));
				curso.setIp(rs.getString("cur_ip"));
				curso.setNombre(rs.getString("cur_nombre"));
				curso.setUsuario(rs.getString("cur_usuario"));
				
				return curso;
			}

		});

		return lstCurso;

	}
	
	public void insert(Curso curso) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("cliente", curso.getCliente());
		parameter.addValue("estado", curso.getEstado());
		parameter.addValue("ip", curso.getIp());
		parameter.addValue("nombre", curso.getNombre());
		parameter.addValue("usuario", curso.getUsuario());
		
		String sql="insert into curso(cur_nombre,cur_usuario,cur_ip,cur_estado,cur_cliente) values (:nombre,:usuario,:ip,:estado,:cliente)";
		
		namedJdbcTemplate.update(sql, parameter);
	}
	
	public void update(Curso curso) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("cliente", curso.getCliente());
		parameter.addValue("estado", curso.getEstado());
		parameter.addValue("ip", curso.getIp());
		parameter.addValue("nombre", curso.getNombre());
		parameter.addValue("usuario", curso.getUsuario());
		parameter.addValue("codigo", curso.getCodigo());
		
		String sql="UPDATE curso set cur_nombre=:nombre,cur_usuario=:usuario,cur_ip=:ip,cur_estado=:estado,cur_cliente=:cliente where cur_codigo=:codigo" ;
		
		namedJdbcTemplate.update(sql, parameter);
	}
	
	public void delete(Curso curso) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", curso.getCodigo());
		parameter.addValue("usuario", curso.getUsuario());
		parameter.addValue("ip", curso.getIp());
		parameter.addValue("cliente", curso.getCliente());
		parameter.addValue("estado", 0);
		
		String sql="UPDATE curso set cur_estado=:estado, cur_cliente=:cliente, cur_ip=:ip, cur_usuario=:usuario where cur_codigo=:codigo";
		
		namedJdbcTemplate.update(sql, parameter);
		
	}

}
