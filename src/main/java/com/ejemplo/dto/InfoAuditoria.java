package com.ejemplo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InfoAuditoria {
	
	private String ip;
	private String usuario;
	private String cliente;
	
}
