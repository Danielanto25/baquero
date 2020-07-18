package com.ejemplo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.dto.Email;
import com.ejemplo.model.Correo;
import com.ejemplo.repository.CorreoRepository;
import com.ejemplo.util.EmailComponent;

@RestController
@RequestMapping(path = "api/correo")
public class EmailController {

	@Autowired
	private EmailComponent emailComponent;

	@Autowired
	private CorreoRepository correos;

	@PostMapping(path = "correo")
	public void correo(@RequestBody Correo correo) {

		String texto = correo.getCuerpo();
		String[] textoDividido = texto.split("\n");

		String textoFinal = "";

		for (String parrafo : textoDividido) {
			textoFinal += String.format("<p>%s</p>", parrafo);
		}
		
		String[] destinatarios = new String[correos.listaCorreos().size()];
		
		for(int i=0;i<correos.listaCorreos().size();i++) {
			
			destinatarios[i]=correos.listaCorreos().get(i);
			
		}
		
		Email email = new Email();
		email.setAsunto(correo.getAsunto());
		email.setDescripcion(textoFinal);

		email.setDestinatario(destinatarios);

		emailComponent.enviar(email);
	}

}
