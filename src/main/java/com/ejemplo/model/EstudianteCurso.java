package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstudianteCurso {

	private Integer codigo;
	private EstudianteCalendario estudianteCalendario;
	private Curso curso;
	private float definitiva;
	private String cliente;
	private String ip;
	private String usuario;
	private int estado;
}
