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
	
	private PersonaFisicaRepository personaFisicaRepository;
	
	private ClienteRepository clienteRepository;
	
	private String NO_SE_ENCONTRO_LA_PERSONA = "No se encontro la persona.";
	
	private String NO_SE_ENCONTRO_EL_CLIENTE = "No se encontro el cliente.";
	

    public Cliente001BZ(Cliente001BzDTO dto) {
		this.personaFisicaRepository = dto.getPersonaFisicaRepository();
		this.clienteRepository = dto.getClienteRepository();
	}

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
