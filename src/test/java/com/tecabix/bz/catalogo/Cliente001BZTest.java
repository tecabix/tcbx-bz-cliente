package com.tecabix.bz.catalogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    private static final Long PERSONA_ID = 1L;
    
    @Mock
    private PersonaFisicaRepository personaFisicaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private Cliente001BzDTO dto;

    @Mock
    private RQSV013 rqsv013;

    @Mock
    private Sesion sesion;

    @Mock
    private Usuario usuario;

    @Mock
    private UsuarioPersona usuarioPersona;

    @Mock
    private Persona persona;

    @Mock
    private RSB008 rsb008;

    private Cliente001BZ service;
    

    @BeforeEach
    void setUp() {
        when(dto.getPersonaFisicaRepository()).thenReturn(personaFisicaRepository);
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
    void cuandoPersonaFisicaNoExiste_retornaNotFound() {
        when(personaFisicaRepository.findByPersona(PERSONA_ID)).thenReturn(Optional.empty());

        ResponseEntity<RSB008> expected = ResponseEntity.notFound().build();
        when(rsb008.notFound("No se encontro la persona.")).thenReturn(expected);

        ResponseEntity<RSB008> response = service.obtener(rqsv013);

        assertEquals(expected, response);
        verify(personaFisicaRepository).findByPersona(PERSONA_ID);
        verify(rsb008).notFound("No se encontro la persona.");
        verifyNoInteractions(clienteRepository);
    }

    @Test
    void cuandoClienteNoExiste_retornaNotFound() {
        PersonaFisica pf = mock(PersonaFisica.class);
        when(personaFisicaRepository.findByPersona(PERSONA_ID)).thenReturn(Optional.of(pf));

        Long PF_ID = 2L;
        when(pf.getId()).thenReturn(PF_ID);

        when(clienteRepository.findByPersonaFisica(PF_ID)).thenReturn(Optional.empty());
        ResponseEntity<RSB008> expected = ResponseEntity.notFound().build();
        when(rsb008.notFound("No se encontro el cliente.")).thenReturn(expected);

        ResponseEntity<RSB008> response = service.obtener(rqsv013);

        assertEquals(expected, response);
        verify(personaFisicaRepository).findByPersona(PERSONA_ID);
        verify(clienteRepository).findByPersonaFisica(PF_ID);
        verify(rsb008).notFound("No se encontro el cliente.");
    }

    @Test
    void cuandoClienteExiste_retornaOk() {
        PersonaFisica pf = mock(PersonaFisica.class);
        when(personaFisicaRepository.findByPersona(PERSONA_ID)).thenReturn(Optional.of(pf));

        Long PF_ID = 3L;
        when(pf.getId()).thenReturn(PF_ID);

        Cliente cliente = mock(Cliente.class);
        when(clienteRepository.findByPersonaFisica(PF_ID)).thenReturn(Optional.of(cliente));

        ResponseEntity<RSB008> expected = ResponseEntity.ok().build();
        when(rsb008.ok(cliente)).thenReturn(expected);

        ResponseEntity<RSB008> response = service.obtener(rqsv013);

        assertEquals(expected, response);
        verify(personaFisicaRepository).findByPersona(PERSONA_ID);
        verify(clienteRepository).findByPersonaFisica(PF_ID);
        verify(rsb008).ok(cliente);
    }
}
