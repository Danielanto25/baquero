package com.ejemplo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ejemplo.model.TipoTrabajo;

@FeignClient(url="${url-api-externa}/api/tipo-trabajo",name="tipo-trabajo")
public interface ITipoTrabajoClient {
	
	@GetMapping(path = "listar")
	List<TipoTrabajo> apiTipoTrabajo();
	
}
