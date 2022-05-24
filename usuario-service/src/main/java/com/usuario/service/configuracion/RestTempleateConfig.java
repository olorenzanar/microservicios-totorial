package com.usuario.service.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * Esta clase tiene como función comunicar los microservicios usando
 * Rest Template
 *  Vamos a comunicar los 3 microservicios
 */

@Configuration
public class RestTempleateConfig {

	/**
	 * Se crea un bean en el configurador de spring
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate()  ;
	}
	
		
}
