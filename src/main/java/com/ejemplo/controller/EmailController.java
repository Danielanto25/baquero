package com.ejemplo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.dto.Email;
import com.ejemplo.util.EmailComponent;


@RestController
@RequestMapping(path = "api/email")
public class EmailController {

	@Autowired
	private EmailComponent emailComponent;
	
	
	@GetMapping(path = "prueba")
	public void email() {
		Email email = new Email();
		email.setAsunto("Prueba 1");
		email.setDescripcion("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec turpis lacinia, euismod "
				+ "eros id, fermentum odio. Phasellus id eros et neque luctus semper. Nullam erat sapien, condimentum ut viverra ac, "
				+ "blandit a dolor. Donec felis erat, pulvinar vitae porta at, aliquet vitae felis. Ut at nunc id ipsum dapibus consequat "
				+ "eu lacinia diam. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. In in sem vel augue venenatis semper quis in velit. Proin sodales efficitur mi vel lacinia. Fusce suscipit mauris at turpis rutrum efficitur. Aliquam erat volutpat. Phasellus luctus suscipit varius. Morbi efficitur tellus vitae ullamcorper dictum. Praesent in scelerisque mauris.\r\n" + 
				"\r\n" + 
				"Donec lacinia ornare nulla, in varius est iaculis tincidunt. In mattis mauris faucibus, sagittis quam in, egestas nisl. Etiam ac elementum nisi, a pulvinar sapien. Nulla facilisi. Cras commodo lorem risus, at lacinia nisl tempus sed. Nam dictum quam a diam ornare laoreet. Donec laoreet ullamcorper justo, ac tempor libero pellentesque quis. Vestibulum facilisis semper mi, eget suscipit orci imperdiet ut. Proin sed turpis lectus. Proin ornare odio tortor, semper commodo ex volutpat ut.");
		
		String destinatario[]= new String[]{"u20181167874@usco.edu.co"};
		email.setDestinatario(destinatario);
		
		emailComponent.enviar(email);
		
	}
}
