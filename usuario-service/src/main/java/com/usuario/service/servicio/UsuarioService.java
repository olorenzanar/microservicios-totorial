package com.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.model.Carro;
import com.usuario.service.model.Moto;
import com.usuario.service.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	/**
	 * Inyectamos el restTemplate de configuración que se creo
	 */
	@Autowired
	private RestTemplate restTemplate;
	
	//Vamos a realizar la inyección del servicio creado del repository
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/**
	 * Inyectamos CarroFeignClient de configuración que se creo
	 */
	@Autowired
	private CarroFeignClient carroFeignClient;
	
	@Autowired
	private MotoFeignClient motoFeignClient;

	
	
	//Este metodo me trae todos los empleados 
	//C
	public List<Usuario> getAll(){
		return usuarioRepository.findAll();
	} 
	
	//Este metodo recupera la consulra por ID de usuario
	//R
	public Usuario getUsuarioById (int id) {		
		return usuarioRepository.findById(id).orElse(null);			
				
	}
	
	//Este metodo guarda un usuario
	//U
	public Usuario save (Usuario usuario) {
		Usuario nuevoUsuario =  usuarioRepository.save(usuario);		
		return nuevoUsuario;
	}
	
	
	/**
	 * Los primeros 2 metodos se comunican con los 2 microservicios carros y motos desde usuarioService
	 * gracias a que se uso resttemplate mediante el metodo getForObject que nos pide un http y ahí
	 * es donde debemos meter la direción y puerto que se configuro en el application.properties de cada uno de los proyectos
	 * @param usuarioId
	 * @return
	 */
	
	
	//********************* REST TEMPLATE*****************************
	
	public List<Carro> getCarros(int usuarioId){
		List<Carro> carros = restTemplate.getForObject("http://localhost:8082/carro/usuario/"+usuarioId, List.class);
		return carros;
	}	
	
	public List<Moto> getMotos(int usuarioId){
		List<Moto> motos = restTemplate.getForObject("http://localhost:8083/moto/usuario/"+usuarioId, List.class);
		return motos;
	}	
	
	//********************* FEIGN CLIENT*****************************
	
	public Carro saveCarro(int usuarioId, Carro carro) {		
		carro.setUsuarioId(usuarioId);
		Carro nuevoCarro = carroFeignClient.save(carro);
		return nuevoCarro;
	}
	
	
	public Moto saveMoto(int usuarioId, Moto moto){
		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto = motoFeignClient.save(moto);
		return nuevaMoto;		
	}
	
	
	//Implementacion de de metodo que obtiene todo
	
	public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
		Map<String,Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		
		if(usuario == null) {
			resultado.put("Mensaje", "El usuario no existe");
			return resultado;
		}
		
		resultado.put("Usuario",usuario);
		List<Carro> carros = carroFeignClient.getCarros(usuarioId);
		if(carros.isEmpty()) {
			resultado.put("Carros", "El usuario no tiene carros");
		}else {
			resultado.put("Carros", carros);
		}
		
		List<Moto> motos = motoFeignClient.getMotos(usuarioId);
		if(motos.isEmpty()) {
			resultado.put("Motos", "El usuario no tiene motos");
		}else {
			resultado.put("Motos", motos);
		}
		return resultado;
	}
	
}