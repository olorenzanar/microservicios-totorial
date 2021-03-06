package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.moto.service.entity.Moto;
import com.moto.service.servicio.MotoService;



@RestController
@RequestMapping("/moto")
public class MotoController {
	
	@Autowired	
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos(){
		List<Moto> motos = motoService.getAll();
		if(motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}

	@GetMapping("/{id}")	
	public ResponseEntity<Moto> obtenerrCarro(@PathVariable ("id") int id ){
		Moto motos = motoService.getCarroById(id);
		if(motos==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(motos);
	}	


	@PostMapping	
	public ResponseEntity<Moto> guardarCarro(@RequestBody Moto moto){
		Moto nuevaMoto = motoService.save(moto);
		return ResponseEntity.ok(nuevaMoto);

	}

	/**
	 * El sigueinte metodo es para acceder a los carros por usuario
	 * la sintaxis de como debemos definir el acceso al metódo es:
	 * accedemos al metodo "/usuario/{usuarioId}"
	 * con las llaves les estamos pasando el metodo que se creo en la clase CarroService
	 */
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listarCarrosPorUsuario(@PathVariable ("usuarioId") int id){
		List<Moto> motos = motoService.byUsuario(id);
		if(motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}
	
	

}
