package com.tecabix.bz.cliente.dto;

import com.tecabix.db.repository.ClienteRepository;
import com.tecabix.db.repository.PersonaFisicaRepository;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Cliente001BzDTO {

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link PersonaFisica}.
     */
    private PersonaFisicaRepository personaFisicaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Cliente}.
     */
    private ClienteRepository clienteRepository;

    /**
     * Obtiene el repositorio de persona física.
     *
     * @return el repositorio de persona física.
     */
    public PersonaFisicaRepository getPersonaFisicaRepository() {
        return personaFisicaRepository;
    }

    /**
     * Establece el repositorio de persona física.
     *
     * @param repository repositorio de persona física a
     *                                establecer.
     */
    public void setPersonaFisicaRepository(
        final PersonaFisicaRepository repository) {
        this.personaFisicaRepository = repository;
    }

    /**
     * Obtiene el repositorio de cliente.
     *
     * @return el repositorio de cliente.
     */
    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    /**
     * Establece el repositorio de cliente.
     *
     * @param repository el repositorio de cliente a establecer.
     */
    public void setClienteRepository(final ClienteRepository repository) {
        this.clienteRepository = repository;
    }

}
