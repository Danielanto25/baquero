package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Curso {
	
	private Integer codigo;
	private String nombre;
	private String ip;
	private String usuario;
	private String cliente;
	private int estado;
	
}
