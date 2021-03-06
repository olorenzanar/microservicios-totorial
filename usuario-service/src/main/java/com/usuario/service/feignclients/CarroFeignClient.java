package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.service.model.Carro;



/**
 * @author olore
 * 
 * En esta interface se crea la conexión al mmicroservicio por medio de la anotación @FeignClient que recibe 2 parametros el nombre del MS y la URL
 * Adicional debemos poner el @RequestMapping haciendo referencia al servcio en este caso es ("/carro")
 * Por último creamos un método save usando la anotación @RequestBody
 *
 */
@FeignClient (name ="carro-service",url="http://localhost:8082")
@RequestMapping("/carro")
public interface CarroFeignClient {
	
	@PostMapping()
	public Carro save(@RequestBody Carro carro);
	
	
	@GetMapping("/usuario/{usuarioId}")
	public List<Carro> getCarros (@PathVariable ("usuarioId") int usuarioId);
	

}
