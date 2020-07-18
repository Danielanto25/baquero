package com.ejemplo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CorreoRepository {
	

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public List<String> listaCorreos(){
	
		String sql="select cor_correo from correo";

		List<String> lstCorreo = namedJdbcTemplate.query(sql,new RowMapper<String>() {


					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						String correo=rs.getString("cor_correo");
						
						return correo;
					}

				});

		return lstCorreo;
	
	}
}
