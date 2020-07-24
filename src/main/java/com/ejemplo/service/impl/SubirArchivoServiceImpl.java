package com.ejemplo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ejemplo.client.ITipoTrabajoClient;
import com.ejemplo.dto.Email;
import com.ejemplo.model.TipoTrabajo;
import com.ejemplo.service.ISubirArchivoService;
import com.ejemplo.util.EmailComponent;

@Service
public class SubirArchivoServiceImpl implements ISubirArchivoService{

	@Value("${ruta-img}")
	private String rutaImagen;
	
	@Autowired
	private ITipoTrabajoClient client;
	
	@Autowired
	private EmailComponent emailComponent;
	
	@Override
	public void guardarArchivo(MultipartFile archivo,String json) throws IOException {
		
		String extencion=FilenameUtils.getExtension(archivo.getOriginalFilename());
		 
		int numero=(int)(Math.random()*200000000+1);
		File imagen =new File(rutaImagen+"/imagen"+numero+"."+extencion);
		archivo.transferTo(imagen);
				
		ObjectMapper objectMapper=new ObjectMapper();
		Email email = objectMapper.readValue(json, Email.class);
		System.out.println(email);
		
		email.setUrl(imagen.getPath().replace('\\' , '/'));
		
		InputStream inputStream=new FileInputStream(imagen);
		email.setAdjunto(inputStream);
		
		
		email.setExtencion(extencion);

		System.out.println(email);
		
		emailComponent.enviar(email);
		
	}

	@Override
	public List<TipoTrabajo> apiTipoTrabajo() {
		
		return client.apiTipoTrabajo();
	}

}
