package com.ejemplo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Email {

	private String destinatario[];
	private String asunto;
	private String descripcion;
	
}
