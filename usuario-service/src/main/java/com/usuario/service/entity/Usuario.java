package com.usuario.service.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Usuario {



	/**
	 * Esta clase usuario para poder conectarte a la aplicación.
	 */
	@Id	//Especifica el nombre del campo que utilizarás para relacionar la llave primaria en la base de datos
	@GeneratedValue(strategy = GenerationType.IDENTITY) //
	private int id;

	private String nombre;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
