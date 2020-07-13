package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class EstudianteCalendario {
	private Integer codigo;
	private Calendario calendario;
	private Estudiante estudiante;

}
