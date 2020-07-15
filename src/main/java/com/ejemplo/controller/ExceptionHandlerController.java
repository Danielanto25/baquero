package com.ejemplo.controller;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.ejemplo.dto.MensajeError;
import com.ejemplo.exception.JsonParserException;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {

	/*@ExceptionHandler(Exception.class)
	public final ResponseEntity<MensajeError> handleAllException(Exception ex, WebRequest request) {

		return this.contruirMensajeError(ex, request, "Error interno.", HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<MensajeError> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {

		return this.contruirMensajeError(ex, request, "Recurso no encontrado", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(JsonParserException.class)
	public final ResponseEntity<MensajeError> handleJsonParserException(JsonParserException ex, WebRequest request) {

		return this.contruirMensajeError(ex, request, "Recurso no encontrado", HttpStatus.NOT_FOUND);
	}



	private ResponseEntity<MensajeError> contruirMensajeError(Exception ex, WebRequest request, String strMensaje,
			HttpStatus status) {

		ex.printStackTrace();

		MensajeError mensaje = new MensajeError();
		mensaje.setTimestamp(LocalDate.now().toString());
		mensaje.setMessage(strMensaje);
		mensaje.setError(ex.getMessage());
		mensaje.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());
		mensaje.setStatus(status.value());

		return new ResponseEntity<>(mensaje, status);
	}
}
