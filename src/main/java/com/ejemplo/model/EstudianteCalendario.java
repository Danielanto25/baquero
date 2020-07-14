package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class EstudianteCalendario {
	private Integer codigo;
	private Calendario calendario;
	private Estudiante estudiante;
	private int estado;
	private String cliente;
	private String usuario;
	private String ip;

}
