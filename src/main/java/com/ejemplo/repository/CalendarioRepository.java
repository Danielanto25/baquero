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

@Repository
public class CalendarioRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public void insert(Calendario calendario) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", calendario.getNombre());
		parameter.addValue("estado", calendario.getEstado());
		parameter.addValue("cliente", calendario.getCliente());
		parameter.addValue("usuario", calendario.getUsuario());
		parameter.addValue("ip", calendario.getIp());
		
		String sql="insert into calendario(cal_nombre,cal_estado,cal_cliente,cal_usuario,cal_ip) values (:nombre,:estado,:cliente,:usuario,:ip)";
		
		namedJdbcTemplate.update(sql, parameter);
	}
	public void update(Calendario calendario) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", calendario.getNombre());
		parameter.addValue("codigo", calendario.getCodigo());
		parameter.addValue("estado", calendario.getEstado());
		parameter.addValue("cliente", calendario.getCliente());
		parameter.addValue("usuario", calendario.getUsuario());
		parameter.addValue("ip", calendario.getIp());
		
		String sql="update  calendario set cal_nombre=:nombre, cal_estado=:estado,cal_cliente=:cliente,cal_usuario=:usuario,cal_ip=:ip where cal_codigo=:codigo";
		
		namedJdbcTemplate.update(sql, parameter);
	}
	
	public void delete(Calendario calendario) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", calendario.getCodigo());
		parameter.addValue("estado", 0);
		parameter.addValue("cliente", calendario.getCliente());
		parameter.addValue("usuario", calendario.getUsuario());
		parameter.addValue("ip", calendario.getIp());
		
		String sql="update  calendario set cal_estado=:estado,cal_cliente=:cliente,cal_usuario=:usuario,cal_ip=:ip where cal_codigo=:codigo";
		
		namedJdbcTemplate.update(sql, parameter);
	}
	
	public List<Calendario> listar() {

		String sql = "select * from calendario";

		List<Calendario> lstCalendario = namedJdbcTemplate.query(sql, new RowMapper<Calendario>() {

			@Override
			public Calendario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Calendario calendario = new Calendario();
				calendario.setCodigo(rs.getInt("cal_codigo"));
				calendario.setNombre(rs.getString("cal_nombre"));
				calendario.setEstado(rs.getInt("cal_estado"));

				return calendario;
			}

		});

		return lstCalendario;

	}
	
	
}
