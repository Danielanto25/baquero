package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Estudiante {

	private Integer codigo;
	private Programa programa;
	private Persona persona;

}
