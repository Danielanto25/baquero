package com.ejemplo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ejemplo.model.TipoTrabajo;

public interface ISubirArchivoService {

	void guardarArchivo(MultipartFile archivo, String json) throws IOException;

	List<TipoTrabajo> apiTipoTrabajo();
	
}
