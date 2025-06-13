package com.tecabix.bz.catalogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tecabix.bz.cliente.Cliente001BZ;
import com.tecabix.bz.cliente.dto.Cliente001BzDTO;
import com.tecabix.db.entity.Cliente;
import com.tecabix.db.entity.Persona;
import com.tecabix.db.entity.PersonaFisica;
import com.tecabix.db.entity.Sesion;
import com.tecabix.db.entity.Usuario;
import com.tecabix.db.entity.UsuarioPersona;
import com.tecabix.db.repository.ClienteRepository;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.res.b.RSB008;
import com.tecabix.sv.rq.RQSV013;

/**
*
* @author Ramirez Urrutia Angel Abinadi
*/
@ExtendWith(MockitoExtension.class)
class Cliente001BZTest {

    /**
     * Constante que representa el ID de una persona.
     */
    private static final Long PERSONA_ID = 1L;

    /**
     * Constante que representa el ID de persona 2.
     */
    private static final Long PF_ID_DOS = 2L;

    /**
     * Constante que representa el ID de persona 3.
     */
    private static final Long PF_ID_TRES = 3L;

    /**
     * Repositorio simulado para operaciones con personas físicas.
     */
    @Mock
    private PersonaFisicaRepository personaFisicaRepository;

    /**
     * Repositorio simulado para operaciones con clientes.
     */
    @Mock
    private ClienteRepository clienteRepository;

    /**
     * Objeto simulado de transferencia de datos para Cliente001Bz.
     */
    @Mock
    private Cliente001BzDTO dto;

    /**
     * Objeto simulado para la clase RQSV013.
     */
    @Mock
    private RQSV013 rqsv013;

    /**
     * Objeto simulado que representa una sesión de usuario.
     */
    @Mock
    private Sesion sesion;

    /**
     * Objeto simulado que representa un usuario.
     */
    @Mock
    private Usuario usuario;

    /**
     * Objeto simulado que representa la relación entre usuario y persona.
     */
    @Mock
    private UsuarioPersona usuarioPersona;

    /**
     * Objeto simulado que representa una persona.
     */
    @Mock
    private Persona persona;

    /**
     * Objeto simulado para la clase RSB008.
     */
    @Mock
    private RSB008 rsb008;

    /**
     * Instancia del servicio Cliente001BZ que se va a probar.
     */
    private Cliente001BZ service;

    @BeforeEach
    void setUp() {
        when(dto.getPersonaFisicaRepository())
            .thenReturn(personaFisicaRepository);
        when(dto.getClienteRepository()).thenReturn(clienteRepository);
        service = new Cliente001BZ(dto);

        when(rqsv013.getSesion()).thenReturn(sesion);
        when(rqsv013.getRsb008()).thenReturn(rsb008);
        when(sesion.getUsuario()).thenReturn(usuario);
        when(usuario.getUsuarioPersona()).thenReturn(usuarioPersona);
        when(usuarioPersona.getPersona()).thenReturn(persona);
        when(persona.getId()).thenReturn(PERSONA_ID);
    }

    @Test
    void cuandoPersonaFisicaNoExisteRetornaNotFound() {

        when(personaFisicaRepository.findByPersona(PERSONA_ID))
            .thenReturn(Optional.empty());

        ResponseEntity<RSB008> expected = ResponseEntity.notFound().build();
        when(rsb008.notFound("No se encontro la persona.")).thenReturn(expected);

        ResponseEntity<RSB008> response = service.obtener(rqsv013);

        assertEquals(expected, response);
        verify(personaFisicaRepository).findByPersona(PERSONA_ID);
        verify(rsb008).notFound("No se encontro la persona.");
        verifyNoInteractions(clienteRepository);
    }

    @Test
    void cuandoClienteNoExisteRetornaNotFound() {
        PersonaFisica pf = mock(PersonaFisica.class);
        when(personaFisicaRepository.findByPersona(PERSONA_ID))
            .thenReturn(Optional.of(pf));

        when(pf.getId()).thenReturn(PF_ID_DOS);

        when(clienteRepository.findByPersonaFisica(PF_ID_DOS))
            .thenReturn(Optional.empty());

        ResponseEntity<RSB008> expected = ResponseEntity.notFound().build();
        when(rsb008.notFound("No se encontro el cliente.")).thenReturn(expected);

        ResponseEntity<RSB008> response = service.obtener(rqsv013);

        assertEquals(expected, response);
        verify(personaFisicaRepository).findByPersona(PERSONA_ID);
        verify(clienteRepository).findByPersonaFisica(PF_ID_DOS);
        verify(rsb008).notFound("No se encontro el cliente.");
    }

    @Test
    void cuandoClienteExisteRetornaOk() {
        PersonaFisica pf = mock(PersonaFisica.class);
        when(personaFisicaRepository.findByPersona(PERSONA_ID))
            .thenReturn(Optional.of(pf));

        when(pf.getId()).thenReturn(PF_ID_TRES);

        Cliente cliente = mock(Cliente.class);
        when(clienteRepository.findByPersonaFisica(PF_ID_TRES))
            .thenReturn(Optional.of(cliente));

        ResponseEntity<RSB008> expected = ResponseEntity.ok().build();
        when(rsb008.ok(cliente)).thenReturn(expected);

        ResponseEntity<RSB008> response = service.obtener(rqsv013);

        assertEquals(expected, response);
        verify(personaFisicaRepository).findByPersona(PERSONA_ID);
        verify(clienteRepository).findByPersonaFisica(PF_ID_TRES);
        verify(rsb008).ok(cliente);
    }
}
