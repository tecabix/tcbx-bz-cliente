package com.tecabix.bz.cliente.dto;

import com.tecabix.db.repository.ClienteRepository;
import com.tecabix.db.repository.PersonaFisicaRepository;

/**
*
* @author Ramirez Urrutia Angel Abinadi
*/
public class Cliente001BzDTO {

	private PersonaFisicaRepository personaFisicaRepository;
	
	private ClienteRepository clienteRepository;

	public PersonaFisicaRepository getPersonaFisicaRepository() {
		return personaFisicaRepository;
	}

	public void setPersonaFisicaRepository(PersonaFisicaRepository personaFisicaRepository) {
		this.personaFisicaRepository = personaFisicaRepository;
	}

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
}
