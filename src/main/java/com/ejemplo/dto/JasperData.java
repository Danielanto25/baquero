package com.ejemplo.dto;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JasperData {

	private String pathJrxml;
	private Map<String, Object> dataSource;
	private HttpServletResponse response;

}
