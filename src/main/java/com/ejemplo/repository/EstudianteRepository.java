package com.ejemplo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Estudiante;
import com.ejemplo.model.Persona;
import com.ejemplo.model.Programa;

@Repository
public class EstudianteRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insert(Estudiante estudiante) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("persona", estudiante.getPersona().getCodigo());
		parameter.addValue("programa", estudiante.getPrograma().getCodigo());
		parameter.addValue("estado", estudiante.getEstado());
		parameter.addValue("ip", estudiante.getIp());
		parameter.addValue("usuario", estudiante.getUsuario());
		parameter.addValue("cliente", estudiante.getCliente());

		String sql = "insert into estudiante(pro_codigo,per_codigo,est_estado,est_cliente,est_usuario,est_ip) values(:programa,:persona,:estado,:cliente,:usuario,:ip)";

		namedJdbcTemplate.update(sql, parameter);
	}

	public void update(Estudiante estudiante) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", estudiante.getCodigo());
		parameter.addValue("programa", estudiante.getPrograma().getCodigo());
		parameter.addValue("ip", estudiante.getIp());
		parameter.addValue("usuario", estudiante.getUsuario());
		parameter.addValue("cliente", estudiante.getCliente());

		String sql = "update estudiante set pro_codigo=:programa,est_cliente=:cliente,est_usuario=:usuario,est_ip=:ip where est_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

	public List<Estudiante> listar() {

		String sql = "SELECT *  from persona p join estudiante e on e.per_codigo = p.per_codigo join programa pr on e.pro_codigo = pr.pro_codigo ";

		List<Estudiante> lstEstudiante = namedJdbcTemplate.query(sql, new RowMapper<Estudiante>() {

			@Override
			public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {

				Programa programa = new Programa();
				programa.setNombre(rs.getString("pro_nombre"));
				programa.setCodigo(rs.getInt("pro_codigo"));

				Persona persona = new Persona();
				persona.setNombre(rs.getString("per_nombre"));
				persona.setCodigo(rs.getInt("per_codigo"));

				Estudiante estudiante = new Estudiante();
				estudiante.setPrograma(programa);
				estudiante.setPersona(persona);
				estudiante.setCodigo(rs.getInt("est_codigo"));

				return estudiante;
			}

		});

		return lstEstudiante;

	}

	public void delete(Estudiante estudiante) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", estudiante.getCodigo());
		parameter.addValue("estado", 0);
		parameter.addValue("ip", estudiante.getIp());
		parameter.addValue("usuario", estudiante.getUsuario());
		parameter.addValue("cliente", estudiante.getCliente());

		String sql = "update estudiante set est_estado=:estado,est_cliente=:cliente,est_usuario=:usuario,est_ip=:ip where est_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

	public Estudiante listarCodigo(String usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", usuario);

		String sql = "select est_codigo from usuario u join persona p on u.per_codigo =p.per_codigo join estudiante e on p.per_codigo =e.per_codigo  where usu_usuario=:usuario ";

		List<Estudiante> lstEstudiante = namedJdbcTemplate.query(sql,parameter, new RowMapper<Estudiante>() {

			@Override
			public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estudiante estudiante = new Estudiante();
				estudiante.setCodigo(rs.getInt("est_codigo"));

				return estudiante;
			}

		});

		return lstEstudiante.get(0);

	}
}
