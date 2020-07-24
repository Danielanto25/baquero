package com.ejemplo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ejemplo.dto.Email;
import com.ejemplo.repository.CorreoRepository;
import com.ejemplo.util.EmailComponent;

@RestController
@RequestMapping(path = "api/correo")
public class EmailController {

	@Autowired
	private EmailComponent emailComponent;

	@Autowired
	private CorreoRepository correos;
	// inyectamos la ruta donde vamos a guardar las imagenes

	@Value("${ruta-img}")
	private String rutaImagen;

	@PostMapping(path = "correo")
	public void correo(@RequestPart MultipartFile archivo, @RequestParam String json, @RequestPart MultipartFile imagen)
			throws IOException {

		String[] destinatarios = new String[correos.listaCorreos().size()];

		for (int i = 0; i < correos.listaCorreos().size(); i++) {

			destinatarios[i] = correos.listaCorreos().get(i);

		}

		// sacando la extencion del archivo

		String extencion = FilenameUtils.getExtension(archivo.getOriginalFilename());
		String extencion2 = FilenameUtils.getExtension(imagen.getOriginalFilename());

		// creamos un numero aleatorio para almacenar la imagen o archivo y la agrgamos
		// a la ruta que inyectamos

		int numero = (int) (Math.random() * 200000000 + 1);
		File documento = new File(rutaImagen + "/documento" + numero + "." + extencion);
		archivo.transferTo(documento);
		File picture = new File(rutaImagen + "/imagen" + numero + "." + extencion2);
		imagen.transferTo(picture);

		// aca convertimos el json en objeto para poder trabajarlo normalmente

		ObjectMapper objectMapper = new ObjectMapper();
		Email correo = objectMapper.readValue(json, Email.class);

		// aca se cambian los back slash por slash

		correo.setUrl(picture.getPath().replace('\\', '/'));

		// agregamos nuestro inputStream

		InputStream inputStream = new FileInputStream(documento);
		correo.setAdjunto(inputStream);

		// agregamos la extencion del arcivo

		correo.setExtencion(extencion);

		// Sacamos los espacios en blanco y los separamos en parrafos

		String texto = correo.getDescripcion();
		String[] textoDividido = texto.split("\n");

		String textoFinal = "";

		for (String parrafo : textoDividido) {
			textoFinal += String.format("<p>%s</p>", parrafo);
		}

		// cargamos nuestro nuevo texto

		correo.setDescripcion(textoFinal);

		correo.setDestinatario(destinatarios);

		// enviamos el correo
		emailComponent.enviar(correo);
	}

	@PostMapping(path = "correo-sin-imagen")
	public void correoSinImagen(@RequestPart MultipartFile archivo, @RequestParam String json) throws IOException {

		String[] destinatarios = new String[correos.listaCorreos().size()];

		for (int i = 0; i < correos.listaCorreos().size(); i++) {

			destinatarios[i] = correos.listaCorreos().get(i);

		}

		// sacando la extencion del archivo

		String extencion = FilenameUtils.getExtension(archivo.getOriginalFilename());

		// creamos un numero aleatorio para almacenar la imagen o archivo y la agrgamos
		// a la ruta que inyectamos

		int numero = (int) (Math.random() * 200000000 + 1);
		File documento = new File(rutaImagen + "/documento" + numero + "." + extencion);
		archivo.transferTo(documento);

		// aca convertimos el json en objeto para poder trabajarlo normalmente

		ObjectMapper objectMapper = new ObjectMapper();
		Email correo = objectMapper.readValue(json, Email.class);

		// agregamos nuestro inputStream

		InputStream inputStream = new FileInputStream(documento);
		correo.setAdjunto(inputStream);

		// agregamos la extencion del arcivo

		correo.setExtencion(extencion);

		// Sacamos los espacios en blanco y los separamos en parrafos

		String texto = correo.getDescripcion();
		String[] textoDividido = texto.split("\n");

		String textoFinal = "";

		for (String parrafo : textoDividido) {
			textoFinal += String.format("<p>%s</p>", parrafo);
		}

		// cargamos nuestro nuevo texto

		correo.setDescripcion(textoFinal);

		correo.setDestinatario(destinatarios);

		// enviamos el correo
		emailComponent.enviar(correo);

	}
	
	@PostMapping(path = "correo-sin-archivo")
	public void correoSinArchivo(@RequestPart MultipartFile imagen, @RequestParam String json) throws IOException {

		String[] destinatarios = new String[correos.listaCorreos().size()];

		for (int i = 0; i < correos.listaCorreos().size(); i++) {

			destinatarios[i] = correos.listaCorreos().get(i);

		}

		// sacando la extencion del archivo

		String extencion2 = FilenameUtils.getExtension(imagen.getOriginalFilename());

		// creamos un numero aleatorio para almacenar la imagen o archivo y la agrgamos
		// a la ruta que inyectamos

		int numero = (int) (Math.random() * 200000000 + 1);
		File picture = new File(rutaImagen + "/imagen" + numero + "." + extencion2);
		imagen.transferTo(picture);

		// aca convertimos el json en objeto para poder trabajarlo normalmente

		ObjectMapper objectMapper = new ObjectMapper();
		Email correo = objectMapper.readValue(json, Email.class);

		// aca se cambian los back slash por slash

		correo.setUrl(picture.getPath().replace('\\', '/'));

		// Sacamos los espacios en blanco y los separamos en parrafos

		String texto = correo.getDescripcion();
		String[] textoDividido = texto.split("\n");

		String textoFinal = "";

		for (String parrafo : textoDividido) {
			textoFinal += String.format("<p>%s</p>", parrafo);
		}

		// cargamos nuestro nuevo texto

		correo.setDescripcion(textoFinal);

		correo.setDestinatario(destinatarios);

		// enviamos el correo
		emailComponent.enviar(correo);
	}
	@PostMapping(path = "correo-solo-texto")
	public void correoSoloTexto(@RequestParam String json) throws IOException {

		String[] destinatarios = new String[correos.listaCorreos().size()];

		for (int i = 0; i < correos.listaCorreos().size(); i++) {

			destinatarios[i] = correos.listaCorreos().get(i);

		}

		// aca convertimos el json en objeto para poder trabajarlo normalmente

		ObjectMapper objectMapper = new ObjectMapper();
		Email correo = objectMapper.readValue(json, Email.class);


		// Sacamos los espacios en blanco y los separamos en parrafos

		String texto = correo.getDescripcion();
		String[] textoDividido = texto.split("\n");

		String textoFinal = "";

		for (String parrafo : textoDividido) {
			textoFinal += String.format("<p>%s</p>", parrafo);
		}

		// cargamos nuestro nuevo texto

		correo.setDescripcion(textoFinal);

		correo.setDestinatario(destinatarios);

		// enviamos el correo
		emailComponent.enviar(correo);
	}
}
