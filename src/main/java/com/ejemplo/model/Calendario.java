package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Calendario {
	private Integer codigo;
	private String nombre;
	private int estado;
	private String cliente;
	private String usuario;
	private String ip;
}
