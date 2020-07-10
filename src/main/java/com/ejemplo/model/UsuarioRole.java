package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsuarioRole {

	private int codigo;
	private Usuario usuario;
	private Role role;
}
