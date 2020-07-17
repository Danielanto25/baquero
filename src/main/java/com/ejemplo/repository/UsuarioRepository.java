package com.ejemplo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Persona;
import com.ejemplo.model.Usuario;

@Repository
public class UsuarioRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Usuario buscarUsuarioClaveEstadoPorUsuario(String usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", usuario);

		String sql = "select usu_usuario,usu_clave,usu_estado from usuario where usu_usuario=:usuario";

		List<Usuario> lstUsuario = namedJdbcTemplate.query(sql, parameter, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario user = new Usuario();
				user.setClave(rs.getString("usu_clave"));
				user.setEstado(rs.getInt("usu_estado"));
				user.setUsuario(rs.getString("usu_usuario"));
				return user;
			}

		});

		if (lstUsuario.size() == 0) {
			throw new RuntimeException("el usuario no existe -> " + usuario);
		}

		return lstUsuario.get(0);
	}

	public List<String> buscarRolePorUsuario(String usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", usuario);

		String sql = "select r.role_nombre from usuario u inner join usuario_role ur on u.usu_codigo  = ur.usu_codigo inner "
				+ "join \"role\" r on ur.rol_codigo  = r.rol_codigo  where u.usu_usuario  =:usuario";

		List<String> lstRoles = namedJdbcTemplate.query(sql, parameter, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				return rs.getString("role_nombre");
			}

		});

		if (lstRoles.size() == 0) {
			throw new RuntimeException("Usuario sin permisos -> " + usuario);
		}

		return lstRoles;
	}

	public List<Usuario> listar() {

		String sql = "select * from usuario join persona p on usuario .per_codigo= p.per_codigo ";

		List<Usuario> lstUsuario = namedJdbcTemplate.query(sql, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Persona persona = new Persona();
				persona.setNombre(rs.getString("per_nombre"));
				persona.setCodigo(rs.getInt("per_codigo"));

				Usuario user = new Usuario();
				user.setPersona(persona);
				user.setEstado(rs.getInt("usu_estado"));
				user.setUsuario(rs.getString("usu_usuario"));
				user.setClave(rs.getString("usu_clave"));
				user.setCodigo(rs.getInt("usu_codigo"));

				return user;
			}

		});

		return lstUsuario;
	}

	public void insert(Usuario usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("clave", encoder.encode(usuario.getClave()));
		parameter.addValue("nombre", usuario.getUsuario());
		parameter.addValue("ip", usuario.getIp());
		parameter.addValue("usuario", usuario.getUsuarioUsuario());
		parameter.addValue("cliente", usuario.getCliente());
		parameter.addValue("estado", usuario.getEstado());
		parameter.addValue("persona", usuario.getPersona().getCodigo());

		String sql = "insert into usuario(usu_usuario,usu_cliente,usu_usu_usuario,usu_ip,usu_estado,usu_clave,per_codigo)"
				+ "values(:nombre,:cliente,:usuario,:ip,:estado,:clave,:persona)";

		namedJdbcTemplate.update(sql, parameter);

	}

	public void update(Usuario usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("clave", encoder.encode(usuario.getClave()));
		parameter.addValue("nombre", usuario.getUsuario());
		parameter.addValue("ip", usuario.getIp());
		parameter.addValue("usuario", usuario.getUsuarioUsuario());
		parameter.addValue("cliente", usuario.getCliente());
		parameter.addValue("estado", usuario.getEstado());
		parameter.addValue("persona", usuario.getPersona().getCodigo());
		parameter.addValue("codigo", usuario.getCodigo());

		String sql = "update usuario set usu_usuario=:nombre,usu_cliente=:cliente,usu_usu_usuario=:usuario,"
				+ "usu_ip=:ip,usu_estado=:estado,usu_clave=:clave,per_codigo=:persona where usu_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

	public void delete(Usuario usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("ip", usuario.getIp());
		parameter.addValue("usuario", usuario.getUsuarioUsuario());
		parameter.addValue("cliente", usuario.getCliente());
		parameter.addValue("estado", 0);
		parameter.addValue("codigo", usuario.getCodigo());

		String sql = "update usuario set usu_cliente=:cliente,usu_usu_usuario=:usuario,"
				+ "usu_ip=:ip,usu_estado=:estado where usu_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}
	

}
