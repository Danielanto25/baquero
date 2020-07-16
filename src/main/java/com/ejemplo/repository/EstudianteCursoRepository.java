package com.ejemplo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityNotFoundException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Curso;
import com.ejemplo.model.EstudianteCalendario;
import com.ejemplo.model.EstudianteCurso;

@Repository
public class EstudianteCursoRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insert(EstudianteCurso estudiantecurso) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigoEstudianteCalendario", estudiantecurso.getEstudianteCalendario().getCodigo());
		parameter.addValue("curso", estudiantecurso.getCurso().getCodigo());
		parameter.addValue("ip", estudiantecurso.getIp());
		parameter.addValue("usuario", estudiantecurso.getUsuario());
		parameter.addValue("cliente", estudiantecurso.getCliente());
		parameter.addValue("estado", estudiantecurso.getEstado());

		String sql = "insert into estudiante_curso(esc_codigo,cur_codigo,ecu_cliente,ecu_usuario,ecu_ip,ecu_estado)"
				+ "values(:codigoEstudianteCalendario,:curso,:cliente,:usuario,:ip,:estado)";

		namedJdbcTemplate.update(sql, parameter);
	}

	public List<EstudianteCurso> listarCursosPorEstudiante(Integer estudiante) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", estudiante);

		String sql = "\r\n" + "select cur_nombre, ecu_definitiva from estudiante  join "
				+ "estudiante_calendario on estudiante.est_codigo=estudiante_calendario.est_codigo join "
				+ "estudiante_curso on estudiante_calendario.esc_codigo =estudiante_curso.esc_codigo join"
				+ " curso on  estudiante_curso.cur_codigo=curso.cur_codigo where estudiante.est_codigo=:codigo";

		List<EstudianteCurso> lstEstudianteCuerso = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<EstudianteCurso>() {

					@Override
					public EstudianteCurso mapRow(ResultSet rs, int rowNum) throws SQLException {

						EstudianteCurso estudianteCurso = new EstudianteCurso();
						Curso curso = new Curso();
						curso.setNombre(rs.getString("cur_nombre"));
						estudianteCurso.setDefinitiva(rs.getFloat("ecu_definitiva"));
						estudianteCurso.setCurso(curso);
						
						System.out.println(estudianteCurso);

						return estudianteCurso;
					}

				});

		if (lstEstudianteCuerso.size() == 0) {
			System.out.println("Aqui llego el error");
			throw new EntityNotFoundException("el estudiante no existe o no tiene cursos registrados -> " + estudiante);
		}

		return lstEstudianteCuerso;
	}

	public void delete(EstudianteCurso estudianteCurso) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", estudianteCurso.getCodigo());
		parameter.addValue("ip", estudianteCurso.getIp());
		parameter.addValue("usuario", estudianteCurso.getUsuario());
		parameter.addValue("cliente", estudianteCurso.getCliente());

		String sql = "update estudiante_curso set estado=0,ecu_cliente=:cliente,ecu_usuario=:usuario,ecu_ip=:ip where codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

	public void update(EstudianteCurso estudianteCurso) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", estudianteCurso.getCodigo());
		parameter.addValue("curso", estudianteCurso.getCurso().getCodigo());
		parameter.addValue("definitiva", estudianteCurso.getDefinitiva());
		parameter.addValue("calendario", estudianteCurso.getEstudianteCalendario().getCodigo());
		parameter.addValue("ip", estudianteCurso.getIp());
		parameter.addValue("usuario", estudianteCurso.getUsuario());
		parameter.addValue("cliente", estudianteCurso.getCliente());
		parameter.addValue("estado", estudianteCurso.getEstado());

		String sql = "update estudiante_curso set ecu_curso=:curso,esc_codigo=:calendario,cur_codigo=:curso,ecu_definitiva=:definitiva,"
				+ "ecu_cliente=:cliente,ecu_usuario=:usuario,ecu_ip=:ip,ecu_estado=:estado where ecu_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

	public List<EstudianteCurso> listar() {

		String sql = "select * from estudiante_curso";

		List<EstudianteCurso> lstEstudianteCuerso = namedJdbcTemplate.query(sql, new RowMapper<EstudianteCurso>() {

			@Override
			public EstudianteCurso mapRow(ResultSet rs, int rowNum) throws SQLException {

				EstudianteCurso estudianteCurso = new EstudianteCurso();
				Curso curso = new Curso();
				curso.setCodigo(rs.getInt("cur_codigo"));

				EstudianteCalendario calendario = new EstudianteCalendario();
				calendario.setCodigo(rs.getInt("esc_codigo"));

				estudianteCurso.setCurso(curso);
				estudianteCurso.setCodigo(rs.getInt("ecu_codigo"));
				estudianteCurso.setDefinitiva(rs.getFloat("ecu_definitiva"));
				estudianteCurso.setEstudianteCalendario(calendario);
				estudianteCurso.setEstado(rs.getInt("ecu_estado"));

				return estudianteCurso;
			}

		});

		return lstEstudianteCuerso;

	}

	public EstudianteCurso insertarNotaEstudiante (Integer est_codigo, float definitiva, Integer cur_codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("estudiante", est_codigo);
		parameter.addValue("definitiva", definitiva);
		parameter.addValue("cur_codigo", cur_codigo);

		String sql ="update estudiante_curso set ecu_definitiva=:definitiva where ecu_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

		return null;
	}
}
