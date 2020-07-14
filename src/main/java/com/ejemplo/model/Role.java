package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Role {
	
	private int codigo;
	private String nombre;
	private String cliente;
	private String ip;
	private String usuario;
	private int estado;
}
