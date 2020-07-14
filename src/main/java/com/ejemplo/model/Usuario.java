
package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Usuario {
	
	private Integer codigo;
	private String usuario;
	private String clave;
	private Persona persona;
	private int estado;
	private String cliente;
	private String ip;
	private String usuarioUsuario;
	 
	
}
