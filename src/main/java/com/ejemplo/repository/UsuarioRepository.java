package com.ejemplo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Usuario;

@Repository
public class UsuarioRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public Usuario buscarUsuarioClaveEstadoPorUsuario(String usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", usuario);
		
		String sql="select usu_usuario,usu_clave,usu_estado from usuario where usu_usuario=:usuario";
		
		List<Usuario> lstUsuario = namedJdbcTemplate.query(sql, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Usuario user=new Usuario();
				user.setClave(rs.getString("usu_clave"));
				user.setEstado(rs.getInt("usu_estado"));
				user.setUsuario(rs.getString("usu_usuario"));
				return user;
			}
			
		});
		
		if(lstUsuario.size()==0) {
			throw new RuntimeException("el usuario no existe -> "+usuario);
		}
		
		return lstUsuario.get(0);
	}
}
