package com.moto.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.entity.Moto;
import com.moto.service.repository.MotoRepository;


@Service
public class MotoService {

	@Autowired	
	private MotoRepository motoRepository;

	//Este metodo me trae todos los empleados 
	//C
	public List<Moto> getAll(){
		return motoRepository.findAll();
	} 

	//Este metodo recupera la consulra por ID de Carro
	//R
	public Moto getCarroById (int id) {		
		return motoRepository.findById(id).orElse(null);			

	}

	//Este metodo guarda un Carro
	//U
	public Moto save (Moto moto) {
		Moto nuevoMoto =  motoRepository.save(moto);		
		return nuevoMoto;
	}

	public List<Moto> byUsuario(int usuarioId){
		return motoRepository.findByUsuarioId(usuarioId);
	}

}
