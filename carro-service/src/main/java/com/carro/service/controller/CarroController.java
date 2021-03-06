package com.carro.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.service.entity.Carro;
import com.carro.service.servicio.CarroService;



@RestController
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	private CarroService carroService;

	@GetMapping
	public ResponseEntity<List<Carro>> listarCarros(){
		List<Carro> carros = carroService.getAll();
		if(carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}

	@GetMapping("/{id}")	
	public ResponseEntity<Carro> obtenerrCarro(@PathVariable ("id") int id ){
		Carro carro = carroService.getCarroById(id);
		if(carro==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(carro);
	}	


	@PostMapping	
	public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro){
		Carro nuevoCarro = carroService.save(carro);
		return ResponseEntity.ok(nuevoCarro);

	}

	/**
	 * El sigueinte metodo es para acceder a los carros por usuario
	 * la sintaxis de como debemos definir el acceso al metódo es:
	 * accedemos al metodo "/usuario/{usuarioId}"
	 * con las llaves les estamos pasando el metodo que se creo en la clase CarroService
	 */
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Carro>> listarCarrosPorUsuario(@PathVariable ("usuarioId") int id){
		List<Carro> carros = carroService.byUsuario(id);
		if(carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}

}
