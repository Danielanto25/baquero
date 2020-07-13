package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EstudianteCurso {
	
	private Integer codigo;
	private Calendario calendario;
	private Curso curso;
	private float definitiva;
	
}
