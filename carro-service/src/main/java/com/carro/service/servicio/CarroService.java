package com.carro.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carro.service.entity.Carro;
import com.carro.service.repository.CarroRepository;

@Service
public class CarroService {
	
	@Autowired	
	private CarroRepository carroRepository;
	
	//Este metodo me trae todos los empleados 
		//C
		public List<Carro> getAll(){
			return carroRepository.findAll();
		} 
		
		//Este metodo recupera la consulra por ID de Carro
		//R
		public Carro getCarroById (int id) {		
			return carroRepository.findById(id).orElse(null);			
					
		}
		
		//Este metodo guarda un Carro
		//U
		public Carro save (Carro carro) {
			Carro nuevoCarro =  carroRepository.save(carro);		
			return nuevoCarro;
		}
		
		public List<Carro> byUsuario(int usuarioId){
			return carroRepository.findByUsuarioId(usuarioId);
		}
	

}
