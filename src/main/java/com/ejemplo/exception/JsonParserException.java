package com.ejemplo.exception;

public class JsonParserException extends RuntimeException{


	private static final long serialVersionUID = -428992007181845615L;

	public JsonParserException(String mensaje) {
		super(mensaje);
		
	}
}
