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
		
		List<Usuario> lstUsuario = namedJdbcTemplate.query(sql,parameter, new RowMapper<Usuario>() {

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
	
	public List<String> buscarRolePorUsuario(String usuario){

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", usuario);
		
		String sql="select r.role_nombre from usuario u inner join usuario_role ur on u.usu_codigo  = ur.usu_codigo inner "
				+ "join \"role\" r on ur.rol_codigo  = r.rol_codigo  where u.usu_usuario  =:usuario";
		
		List<String> lstRoles = namedJdbcTemplate.query(sql,parameter, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return rs.getString("role_nombre");	
			}
			
		});
		
		if(lstRoles.size()==0) {
			throw new RuntimeException("Usuario sin permisos -> "+usuario);
		}
		
		return  lstRoles;
	}
	

}
