package com.tecabix.bz.cliente;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.tecabix.bz.cliente.dto.Cliente001BzDTO;
import com.tecabix.db.entity.Cliente;
import com.tecabix.db.entity.Persona;
import com.tecabix.db.entity.PersonaFisica;
import com.tecabix.db.entity.Sesion;
import com.tecabix.db.repository.ClienteRepository;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.res.b.RSB008;
import com.tecabix.sv.rq.RQSV013;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Cliente001BZ {

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link PersonaFisica}.
     */
    private final PersonaFisicaRepository personaFisicaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Cliente}.
     */
    private final ClienteRepository clienteRepository;

    /**
     * Persona no encontrada.
     */
    private static final String NO_SE_ENCONTRO_LA_PERSONA;

    static {
        NO_SE_ENCONTRO_LA_PERSONA = "No se encontro la persona.";
        NO_SE_ENCONTRO_EL_CLIENTE = "No se encontro el cliente.";
    }

    /**
     * Cliente no encontrado.
     */
    private static final String NO_SE_ENCONTRO_EL_CLIENTE;


    /**
     * Constructor de la clase {@code Cliente001BZ}.
     * <p>
     * Inicializa las dependencias necesarias a partir de un objeto
     * {@link Cliente001BzDTO}.
     * Este constructor extrae los repositorios de persona física y cliente
     * desde el DTO para ser utilizados en la lógica de negocio de esta clase.
     *
     * @param dto Objeto de transferencia de datos que contiene las dependencias
     *            necesarias como {@code personaFisicaRepository}
     *            y {@code clienteRepository}.
     */
    public Cliente001BZ(final Cliente001BzDTO dto) {
        this.personaFisicaRepository = dto.getPersonaFisicaRepository();
        this.clienteRepository = dto.getClienteRepository();
    }

    /**
     * Obtiene la información del cliente asociada a la sesión del usuario.
     * <p>
     * El proceso consiste en:
     * <ol>
     * <li>Recuperar la {@link Persona} vinculada al usuario de la sesión.</li>
     * <li>Buscar la entidad {@link PersonaFisica} relacionada a dicha
     * persona.</li>
     * <li>Obtener el {@link Cliente} asociado a la {@link PersonaFisica}
     * encontrada.</li>
     * </ol>
     * En caso de no encontrar la persona o el cliente, se retorna un error con
     * el mensaje correspondiente.
     * </p>
     *
     * @param rqsv013 objeto de solicitud que contiene la sesión del usuario y
     *                el objeto de respuesta {@link RSB008}.
     * @return un {@link ResponseEntity} con el objeto {@link RSB008} que
     *         envuelve la información del cliente, o un error en caso de que
     *         la búsqueda no sea exitosa.
     */
    public ResponseEntity<RSB008> obtener(final RQSV013 rqsv013) {

        Sesion sesion = rqsv013.getSesion();
        RSB008 rsb008 = rqsv013.getRsb008();
        Persona persona = sesion.getUsuario().getUsuarioPersona().getPersona();
        Optional<PersonaFisica> personaFisica;
        personaFisica = personaFisicaRepository.findByPersona(persona.getId());
        if (personaFisica.isEmpty()) {
            return rsb008.notFound(NO_SE_ENCONTRO_LA_PERSONA);
        }
        Optional<Cliente> cliente;
        Long idPersonaFisica = personaFisica.get().getId();
        cliente = clienteRepository.findByPersonaFisica(idPersonaFisica);
        if (cliente.isEmpty()) {
            return rsb008.notFound(NO_SE_ENCONTRO_EL_CLIENTE);
        }
        return rsb008.ok(cliente.get());
    }
}
