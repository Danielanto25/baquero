package com.ejemplo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Correo {

	private String correos[];
	private String asunto;
	private String cuerpo;
}
