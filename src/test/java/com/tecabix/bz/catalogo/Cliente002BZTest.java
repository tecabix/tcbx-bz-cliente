package com.tecabix.bz.catalogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tecabix.bz.cliente.Cliente002BZ;
import com.tecabix.bz.cliente.dto.Cliente002BzDTO;
import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.entity.Cliente;
import com.tecabix.db.entity.Credito;
import com.tecabix.db.entity.Cuenta;
import com.tecabix.db.entity.Direccion;
import com.tecabix.db.entity.InversionAutomatica;
import com.tecabix.db.entity.Perfil;
import com.tecabix.db.entity.Persona;
import com.tecabix.db.entity.PersonaFisica;
import com.tecabix.db.entity.Sesion;
import com.tecabix.db.entity.Usuario;
import com.tecabix.db.entity.UsuarioPersona;
import com.tecabix.db.repository.CatalogoRepository;
import com.tecabix.db.repository.ClienteRepository;
import com.tecabix.db.repository.ContactoRepository;
import com.tecabix.db.repository.CreditoRepository;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.DireccionRepository;
import com.tecabix.db.repository.InversionAutomaticaRepository;
import com.tecabix.db.repository.MunicipioRepository;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.db.repository.PersonaRepository;
import com.tecabix.db.repository.UsuarioPersonaRepository;
import com.tecabix.db.repository.UsuarioRepository;
import com.tecabix.res.b.RSB028;
import com.tecabix.sv.rq.RQSV014;
import com.tecabix.sv.rq.RQSV024;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
@ExtendWith(MockitoExtension.class)
public class Cliente002BZTest {

    /**
     * Repositorio simulado para operaciones con usuarios.
     */
    @Mock
    private UsuarioRepository usuarioRepository;

    /**
     * Repositorio simulado para operaciones con personas físicas.
     */
    @Mock
    private PersonaFisicaRepository personaFisicaRepository;

    /**
     * Repositorio simulado para operaciones con catálogos.
     */
    @Mock
    private CatalogoRepository catalogoRepository;

    /**
     * Lista simulada de catálogos que representan tipos de contacto.
     */
    @Mock
    private List<Catalogo> tipoContacto;

    /**
     * Catálogo simulado que representa el estado "activo".
     */
    @Mock
    private Catalogo activo;

    /**
     * Catálogo simulado que representa el estado "pendiente".
     */
    @Mock
    private Catalogo pendiente;

    /**
     * Catálogo simulado que representa el riesgo C7.
     */
    @Mock
    private Catalogo riesgoC7;

    /**
     * Catálogo simulado que representa el tipo de persona física.
     */
    @Mock
    private Catalogo tipoFisica;

    /**
     * Objeto simulado que representa el perfil del cliente.
     */
    @Mock
    private Perfil perfilCliente;

    /**
     * Repositorio simulado para operaciones con personas.
     */
    @Mock
    private PersonaRepository personaRepository;

    /**
     * Repositorio simulado para operaciones con contactos.
     */
    @Mock
    private ContactoRepository contactoRepository;

    /**
     * Repositorio simulado para operaciones con relaciones usuario-persona.
     */
    @Mock
    private UsuarioPersonaRepository usuarioPersonaRepository;

    /**
     * Repositorio simulado para operaciones con municipios.
     */
    @Mock
    private MunicipioRepository municipioRepository;

    /**
     * Repositorio simulado para operaciones con direcciones.
     */
    @Mock
    private DireccionRepository direccionRepository;

    /**
     * Repositorio simulado para operaciones con cuentas.
     */
    @Mock
    private CuentaRepository cuentaRepository;

    /**
     * Repositorio simulado para operaciones con clientes.
     */
    @Mock
    private ClienteRepository clienteRepository;

    /**
     * Repositorio simulado para operaciones con inversiones automáticas.
     */
    @Mock
    private InversionAutomaticaRepository inversionAutomaticaRepository;

    /**
     * Repositorio simulado para operaciones con créditos.
     */
    @Mock
    private CreditoRepository creditoRepository;

    /**
     * Constante que representa el ID persona.
     */
    private static final Long PERSONA_ID = 99L;

    @BeforeEach
    void setUp() {

    }

    @Test
    void crearCliente() {
        Cliente002BzDTO dto = new Cliente002BzDTO();
        dto.setUsuarioRepository(usuarioRepository);
        dto.setPersonaFisicaRepository(personaFisicaRepository);
        dto.setCatalogoRepository(catalogoRepository);
        dto.setTipoContacto(tipoContacto);
        dto.setActivo(activo);
        dto.setPendiente(pendiente);
        dto.setRiesgoC7(riesgoC7);
        dto.setTipoFisica(tipoFisica);
        dto.setPerfilCliente(perfilCliente);
        dto.setPersonaRepository(personaRepository);
        dto.setContactoRepository(contactoRepository);
        dto.setUsuarioPersonaRepository(usuarioPersonaRepository);
        dto.setMunicipioRepository(municipioRepository);
        dto.setDireccionRepository(direccionRepository);
        dto.setCuentaRepository(cuentaRepository);
        dto.setClienteRepository(clienteRepository);
        dto.setInversionAutomaticaRepository(inversionAutomaticaRepository);
        dto.setCreditoRepository(creditoRepository);

    }

    @Test
    void crearOk() {

        Cliente002BzDTO dto = new Cliente002BzDTO();
        dto.setUsuarioRepository(usuarioRepository);
        dto.setPersonaFisicaRepository(personaFisicaRepository);
        dto.setCatalogoRepository(catalogoRepository);
        dto.setTipoContacto(tipoContacto);
        dto.setActivo(activo);
        dto.setPendiente(pendiente);
        dto.setRiesgoC7(riesgoC7);
        dto.setTipoFisica(tipoFisica);
        dto.setPerfilCliente(perfilCliente);
        dto.setPersonaRepository(personaRepository);
        dto.setContactoRepository(contactoRepository);
        dto.setUsuarioPersonaRepository(usuarioPersonaRepository);
        dto.setMunicipioRepository(municipioRepository);
        dto.setDireccionRepository(direccionRepository);
        dto.setCuentaRepository(cuentaRepository);
        dto.setClienteRepository(clienteRepository);
        dto.setInversionAutomaticaRepository(inversionAutomaticaRepository);
        dto.setCreditoRepository(creditoRepository);

        Cliente002BZ cliente002bz = new Cliente002BZ(dto) {
            @Override
            public String encoder(final String texto) {
                return texto;
            }
        };

        RQSV014 rqsv014 = mock(RQSV014.class);
        RSB028 rsb028 = mock(RSB028.class);
        Sesion sesion = mock(Sesion.class);
        Usuario usuarioSesion = new Usuario();
        usuarioSesion.setId(1L);
        when(sesion.getUsuario()).thenReturn(usuarioSesion);

        when(rqsv014.getRsb028()).thenReturn(rsb028);
        when(rqsv014.getSesion()).thenReturn(sesion);
        when(rqsv014.getUsuario()).thenReturn("usuarioTest");
        when(rqsv014.getCurp()).thenReturn("CURP123456");
        when(rqsv014.getContactos()).thenReturn(null);

        when(usuarioRepository.findByNameRegardlessOfStatus("usuarioTest"))
            .thenReturn(Optional.empty());

        when(personaFisicaRepository.findByCurp("CURP123456"))
            .thenReturn(Optional.empty());

        Usuario usuario = new Usuario();
        Persona persona = new Persona();
        Direccion direccion = new Direccion();
        PersonaFisica personaFisica = new PersonaFisica();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();

        when(usuarioRepository.save(any())).thenReturn(usuario);
        when(personaRepository.save(any())).thenReturn(persona);
        when(usuarioPersonaRepository.save(any()))
            .thenReturn(new UsuarioPersona());

        when(direccionRepository.save(any())).thenReturn(direccion);
        when(personaFisicaRepository.save(any())).thenReturn(personaFisica);
        when(cuentaRepository.save(any())).thenReturn(cuenta);
        when(clienteRepository.save(any())).thenReturn(cliente);
        when(inversionAutomaticaRepository.save(any()))
            .thenReturn(new InversionAutomatica());

        when(creditoRepository.save(any())).thenReturn(new Credito());

        when(rsb028.ok(cliente)).thenReturn(ResponseEntity.ok(rsb028));

        ResponseEntity<RSB028> response = cliente002bz.crear(rqsv014);

        verify(rsb028).ok(cliente);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void crearCuandoNombreUsuarioYaExiste() {

        Cliente002BzDTO dto = new Cliente002BzDTO();
        dto.setUsuarioRepository(usuarioRepository);
        dto.setPersonaFisicaRepository(personaFisicaRepository);
        dto.setCatalogoRepository(catalogoRepository);
        dto.setTipoContacto(tipoContacto);
        dto.setActivo(activo);
        dto.setPendiente(pendiente);
        dto.setRiesgoC7(riesgoC7);
        dto.setTipoFisica(tipoFisica);
        dto.setPerfilCliente(perfilCliente);
        dto.setPersonaRepository(personaRepository);
        dto.setContactoRepository(contactoRepository);
        dto.setUsuarioPersonaRepository(usuarioPersonaRepository);
        dto.setMunicipioRepository(municipioRepository);
        dto.setDireccionRepository(direccionRepository);
        dto.setCuentaRepository(cuentaRepository);
        dto.setClienteRepository(clienteRepository);
        dto.setInversionAutomaticaRepository(inversionAutomaticaRepository);
        dto.setCreditoRepository(creditoRepository);

        Cliente002BZ cliente002bz = new Cliente002BZ(dto) {
            @Override
            public String encoder(final String texto) {
                return texto;
            }
        };

        RQSV014 rqsv014 = mock(RQSV014.class);
        RSB028 rsb028 = mock(RSB028.class);
        Sesion sesion = mock(Sesion.class);

        when(rqsv014.getRsb028()).thenReturn(rsb028);
        when(rqsv014.getSesion()).thenReturn(sesion);
        when(rqsv014.getUsuario()).thenReturn("usuarioTest");

        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(PERSONA_ID);
        when(usuarioRepository.findByNameRegardlessOfStatus("usuarioTest"))
            .thenReturn(Optional.of(usuarioExistente));

        when(rsb028.badRequest("El username ya existe."))
            .thenReturn(ResponseEntity.badRequest().body(rsb028));

        ResponseEntity<RSB028> response = cliente002bz.crear(rqsv014);

        verify(rsb028).badRequest("El username ya existe.");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void crearCuandoContactosVacio() {

        Cliente002BzDTO dto = new Cliente002BzDTO();
        dto.setUsuarioRepository(usuarioRepository);
        dto.setPersonaFisicaRepository(personaFisicaRepository);
        dto.setCatalogoRepository(catalogoRepository);
        dto.setTipoContacto(tipoContacto);
        dto.setActivo(activo);
        dto.setPendiente(pendiente);
        dto.setRiesgoC7(riesgoC7);
        dto.setTipoFisica(tipoFisica);
        dto.setPerfilCliente(perfilCliente);
        dto.setPersonaRepository(personaRepository);
        dto.setContactoRepository(contactoRepository);
        dto.setUsuarioPersonaRepository(usuarioPersonaRepository);
        dto.setMunicipioRepository(municipioRepository);
        dto.setDireccionRepository(direccionRepository);
        dto.setCuentaRepository(cuentaRepository);
        dto.setClienteRepository(clienteRepository);
        dto.setInversionAutomaticaRepository(inversionAutomaticaRepository);
        dto.setCreditoRepository(creditoRepository);

        Cliente002BZ cliente002bz = new Cliente002BZ(dto) {
            @Override
            public String encoder(final String texto) {
                return texto;
            }
        };

        RQSV014 rqsv014 = mock(RQSV014.class);
        RSB028 rsb028 = mock(RSB028.class);
        Sesion sesion = mock(Sesion.class);

        RQSV024 contactoInvalido = new RQSV024();
        UUID clave = UUID.randomUUID();
        contactoInvalido.setTipo(clave);
        List<RQSV024> listaContactos = List.of(contactoInvalido);

        when(rqsv014.getRsb028()).thenReturn(rsb028);
        when(rqsv014.getSesion()).thenReturn(sesion);
        when(rqsv014.getUsuario()).thenReturn("usuarioNuevo");
        when(rqsv014.getCurp()).thenReturn("CURP123");
        when(rqsv014.getContactos()).thenReturn(listaContactos);

        when(usuarioRepository.findByNameRegardlessOfStatus("usuarioNuevo"))
            .thenReturn(Optional.empty());

        when(personaFisicaRepository.findByCurp("CURP123"))
            .thenReturn(Optional.empty());

        when(catalogoRepository.findByClave(clave))
            .thenReturn(Optional.empty());

        when(rsb028.badRequest("Sin coincidencias con el tipo de contacto."))
                .thenReturn(ResponseEntity.badRequest().body(rsb028));

        ResponseEntity<RSB028> response = cliente002bz.crear(rqsv014);

        verify(rsb028).badRequest("Sin coincidencias con el tipo de contacto.");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void crearCuandoCurpYaExiste() {

        Cliente002BzDTO dto = new Cliente002BzDTO();
        dto.setUsuarioRepository(usuarioRepository);
        dto.setPersonaFisicaRepository(personaFisicaRepository);
        dto.setCatalogoRepository(catalogoRepository);
        dto.setTipoContacto(tipoContacto);
        dto.setActivo(activo);
        dto.setPendiente(pendiente);
        dto.setRiesgoC7(riesgoC7);
        dto.setTipoFisica(tipoFisica);
        dto.setPerfilCliente(perfilCliente);
        dto.setPersonaRepository(personaRepository);
        dto.setContactoRepository(contactoRepository);
        dto.setUsuarioPersonaRepository(usuarioPersonaRepository);
        dto.setMunicipioRepository(municipioRepository);
        dto.setDireccionRepository(direccionRepository);
        dto.setCuentaRepository(cuentaRepository);
        dto.setClienteRepository(clienteRepository);
        dto.setInversionAutomaticaRepository(inversionAutomaticaRepository);
        dto.setCreditoRepository(creditoRepository);

        Cliente002BZ cliente002bz = new Cliente002BZ(dto) {
            @Override
            public String encoder(final String texto) {
                return texto;
            }
        };

        RQSV014 rqsv014 = mock(RQSV014.class);
        RSB028 rsb028 = mock(RSB028.class);
        Sesion sesion = mock(Sesion.class);
        Usuario usuarioSesion = new Usuario();
        usuarioSesion.setId(1L);

        when(rqsv014.getRsb028()).thenReturn(rsb028);
        when(rqsv014.getSesion()).thenReturn(sesion);
        when(rqsv014.getUsuario()).thenReturn("usuarioTest");
        when(rqsv014.getCurp()).thenReturn("curpExiste");

        PersonaFisica personaExistente = new PersonaFisica();
        when(personaFisicaRepository.findByCurp("curpExiste"))
            .thenReturn(Optional.of(personaExistente));

        when(rsb028.badRequest("El CURP ya existe."))
            .thenReturn(ResponseEntity.badRequest().body(rsb028));

        ResponseEntity<RSB028> response = cliente002bz.crear(rqsv014);

        verify(rsb028).badRequest("El CURP ya existe.");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void crearConContactosOk() {

        Cliente002BzDTO dto = new Cliente002BzDTO();
        dto.setUsuarioRepository(usuarioRepository);
        dto.setPersonaFisicaRepository(personaFisicaRepository);
        dto.setCatalogoRepository(catalogoRepository);
        dto.setPersonaRepository(personaRepository);
        dto.setContactoRepository(contactoRepository);
        dto.setUsuarioPersonaRepository(usuarioPersonaRepository);
        dto.setMunicipioRepository(municipioRepository);
        dto.setDireccionRepository(direccionRepository);
        dto.setCuentaRepository(cuentaRepository);
        dto.setClienteRepository(clienteRepository);
        dto.setInversionAutomaticaRepository(inversionAutomaticaRepository);
        dto.setCreditoRepository(creditoRepository);

        UUID tipoContactoUUID = UUID.randomUUID();
        Catalogo tipoCatalogo = new Catalogo();
        tipoCatalogo.setClave(tipoContactoUUID);
        tipoCatalogo.setNombre("E-MAIL");
        tipoCatalogo.setEstatus(activo);

        List<Catalogo> tiposContacto = List.of(tipoCatalogo);
        dto.setTipoContacto(tiposContacto);
        dto.setActivo(activo);

        Cliente002BZ cliente002bz = new Cliente002BZ(dto) {
            @Override
            public String encoder(final String texto) {
                return texto;
            }
        };

        RQSV014 rqsv014 = mock(RQSV014.class);
        RSB028 rsb028 = mock(RSB028.class);
        RQSV024 rqsv024 = new RQSV024();
        rqsv024.setTipo(tipoContactoUUID);
        rqsv024.setValor("correo@ejemplo.com");

        Sesion sesion = mock(Sesion.class);
        Usuario usuarioSesion = new Usuario();
        usuarioSesion.setId(1L);

        when(rqsv014.getRsb028()).thenReturn(rsb028);
        when(rqsv014.getSesion()).thenReturn(sesion);
        when(rqsv014.getUsuario()).thenReturn("usuarioTest");
        when(rqsv014.getCurp()).thenReturn("CURP123456");
        when(rqsv014.getContactos()).thenReturn(List.of(rqsv024));
        when(sesion.getUsuario()).thenReturn(usuarioSesion);

        when(usuarioRepository.findByNameRegardlessOfStatus("usuarioTest"))
            .thenReturn(Optional.empty());

        when(personaFisicaRepository.findByCurp("CURP123456"))
            .thenReturn(Optional.empty());

        when(catalogoRepository.findByClave(tipoContactoUUID))
            .thenReturn(Optional.of(tipoCatalogo));

        Usuario usuario = new Usuario();
        Persona persona = new Persona();
        Direccion direccion = new Direccion();
        PersonaFisica personaFisica = new PersonaFisica();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();

        when(usuarioRepository.save(any())).thenReturn(usuario);
        when(personaRepository.save(any())).thenReturn(persona);
        when(usuarioPersonaRepository.save(any()))
            .thenReturn(new UsuarioPersona());

        when(direccionRepository.save(any())).thenReturn(direccion);
        when(personaFisicaRepository.save(any())).thenReturn(personaFisica);
        when(cuentaRepository.save(any())).thenReturn(cuenta);
        when(clienteRepository.save(any())).thenReturn(cliente);
        when(inversionAutomaticaRepository.save(any()))
            .thenReturn(new InversionAutomatica());

        when(creditoRepository.save(any())).thenReturn(new Credito());

        when(rsb028.ok(cliente)).thenReturn(ResponseEntity.ok(rsb028));

        ResponseEntity<RSB028> response = cliente002bz.crear(rqsv014);

        verify(rsb028).ok(cliente);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void crearConContactosVacio() {

        Cliente002BzDTO dto = new Cliente002BzDTO();
        dto.setUsuarioRepository(usuarioRepository);
        dto.setPersonaFisicaRepository(personaFisicaRepository);
        dto.setCatalogoRepository(catalogoRepository);
        dto.setPersonaRepository(personaRepository);
        dto.setContactoRepository(contactoRepository);
        dto.setUsuarioPersonaRepository(usuarioPersonaRepository);
        dto.setMunicipioRepository(municipioRepository);
        dto.setDireccionRepository(direccionRepository);
        dto.setCuentaRepository(cuentaRepository);
        dto.setClienteRepository(clienteRepository);
        dto.setInversionAutomaticaRepository(inversionAutomaticaRepository);
        dto.setCreditoRepository(creditoRepository);

        Cliente002BZ cliente002bz = new Cliente002BZ(dto) {
            @Override
            public String encoder(final String texto) {
                return texto;
            }
        };

        RQSV014 rqsv014 = mock(RQSV014.class);
        RSB028 rsb028 = mock(RSB028.class);

        Sesion sesion = mock(Sesion.class);
        Usuario usuarioSesion = new Usuario();
        usuarioSesion.setId(1L);

        when(rqsv014.getRsb028()).thenReturn(rsb028);
        when(rqsv014.getSesion()).thenReturn(sesion);
        when(rqsv014.getUsuario()).thenReturn("usuarioTest");
        when(rqsv014.getCurp()).thenReturn("CURP123456");
        when(rqsv014.getContactos()).thenReturn(null);
        when(sesion.getUsuario()).thenReturn(usuarioSesion);

        when(usuarioRepository.findByNameRegardlessOfStatus("usuarioTest"))
            .thenReturn(Optional.empty());

        when(personaFisicaRepository.findByCurp("CURP123456"))
            .thenReturn(Optional.empty());

        when(rqsv014.getContactos()).thenReturn(Collections.emptyList());

        Usuario usuario = new Usuario();

        PersonaFisica personaFisica = new PersonaFisica();
        Cliente cliente = new Cliente();

        when(usuarioRepository.save(any())).thenReturn(usuario);
        when(usuarioPersonaRepository.save(any()))
           .thenReturn(new UsuarioPersona());

        when(personaFisicaRepository.save(any())).thenReturn(personaFisica);
        when(clienteRepository.save(any())).thenReturn(cliente);

        when(rsb028.ok(cliente)).thenReturn(ResponseEntity.ok(rsb028));

        ResponseEntity<RSB028> response = cliente002bz.crear(rqsv014);
    }

    @Test
    void crearConTipoContactoNoPermitido() {
        Cliente002BzDTO dto = new Cliente002BzDTO();
        dto.setUsuarioRepository(usuarioRepository);
        dto.setPersonaFisicaRepository(personaFisicaRepository);
        dto.setCatalogoRepository(catalogoRepository);
        dto.setPersonaRepository(personaRepository);
        dto.setContactoRepository(contactoRepository);
        dto.setUsuarioPersonaRepository(usuarioPersonaRepository);
        dto.setMunicipioRepository(municipioRepository);
        dto.setDireccionRepository(direccionRepository);
        dto.setCuentaRepository(cuentaRepository);
        dto.setClienteRepository(clienteRepository);
        dto.setInversionAutomaticaRepository(inversionAutomaticaRepository);
        dto.setCreditoRepository(creditoRepository);

        UUID tipoContactoUUID = UUID.randomUUID();
        Catalogo tipoCatalogo = new Catalogo();
        tipoCatalogo.setClave(tipoContactoUUID);
        tipoCatalogo.setNombre("E-MAIL");
        tipoCatalogo.setEstatus(activo);

        dto.setTipoContacto(List.of());
        dto.setActivo(activo);

        Cliente002BZ cliente002bz = new Cliente002BZ(dto) {
            @Override
            public String encoder(final String texto) {
                return texto;
            }
        };

        RQSV014 rqsv014 = mock(RQSV014.class);
        RSB028 rsb028 = mock(RSB028.class);
        RQSV024 rqsv024 = new RQSV024();
        rqsv024.setTipo(tipoContactoUUID);
        rqsv024.setValor("correo@ejemplo.com");

        Sesion sesion = mock(Sesion.class);
        Usuario usuarioSesion = new Usuario();
        usuarioSesion.setId(1L);

        when(rqsv014.getRsb028()).thenReturn(rsb028);
        when(rqsv014.getSesion()).thenReturn(sesion);
        when(rqsv014.getUsuario()).thenReturn("usuarioTest");
        when(rqsv014.getCurp()).thenReturn("CURP123456");
        when(rqsv014.getContactos()).thenReturn(List.of(rqsv024));

        when(usuarioRepository.findByNameRegardlessOfStatus("usuarioTest"))
            .thenReturn(Optional.empty());

        when(personaFisicaRepository.findByCurp("CURP123456"))
            .thenReturn(Optional.empty());

        when(catalogoRepository.findByClave(tipoContactoUUID))
            .thenReturn(Optional.of(tipoCatalogo));

        when(rsb028.badRequest(any()))
            .thenReturn(ResponseEntity.badRequest().build());

        ResponseEntity<RSB028> response = cliente002bz.crear(rqsv014);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
