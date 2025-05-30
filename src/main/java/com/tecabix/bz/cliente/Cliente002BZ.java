package com.tecabix.bz.cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;

import com.tecabix.bz.cliente.dto.Cliente002BzDTO;
import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.entity.Cliente;
import com.tecabix.db.entity.Contacto;
import com.tecabix.db.entity.Credito;
import com.tecabix.db.entity.Cuenta;
import com.tecabix.db.entity.Direccion;
import com.tecabix.db.entity.InversionAutomatica;
import com.tecabix.db.entity.Municipio;
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
public abstract class Cliente002BZ {

	private UsuarioRepository usuarioRepository;
	
	private PersonaFisicaRepository personaFisicaRepository;
	
	private CatalogoRepository catalogoRepository;
	
	private List<Catalogo> tipoContacto;
	
	private Catalogo activo;
	
	private Catalogo pendiente;
	
	private Catalogo riesgoC7;
	
	private Catalogo tipoFisica;
	
	private Perfil perfilCliente;
	
	private PersonaRepository personaRepository;
	
	private ContactoRepository contactoRepository;
	
	private UsuarioPersonaRepository usuarioPersonaRepository;
	
	private MunicipioRepository municipioRepository;
	
	private DireccionRepository direccionRepository;
	
	private CuentaRepository cuentaRepository;
	
	private ClienteRepository clienteRepository;
	
	private InversionAutomaticaRepository inversionAutomaticaRepository;
	 
	private CreditoRepository creditoRepository;
	
	private String USERNAME_YA_EXISTE = "El username ya existe.";
	private String CURP_YA_EXISTE = "El CURP ya existe.";
	private String SIN_COINCIDENCIAS_CON_EL_TIPO_DE_CONTACTO = "Sin coincidencias con el tipo de contacto.";
	private String CORREO = "(?!\\s)([A-Za-zñÑáéíóúÁÉÍÓÚüÜ0-9]+([.-][A-Za-zñÑáéíóúÁÉÍÓÚüÜ0-9_]+)*@[A-Za-zñÑáéíóúÁÉÍÓÚüÜ0-9]+([.][A-Za-zñÑáéíóúÁÉÍÓÚüÜ0-9_]+)*[.][A-Za-zñÑáéíóúÁÉÍÓÚüÜ]{2,3})";
	private String URL = "(?=.{15,45}$)https?:\\/\\/[A-Za-zñÑáéíóúÁÉÍÓÚüÜ0-9]+[...]";
	private String TELEFONO = "\\d{10}";
	
	private int LIMITE_CREDITICIO = 350_00;
	
	public Cliente002BZ(Cliente002BzDTO dto) {
		this.usuarioRepository = dto.getUsuarioRepository();
		this.personaFisicaRepository = dto.getPersonaFisicaRepository();
		this.catalogoRepository = dto.getCatalogoRepository();
		this.tipoContacto = dto.getTipoContacto();
		this.activo = dto.getActivo();
		this.pendiente = dto.getPendiente();
		this.riesgoC7 = dto.getRiesgoC7();
		this.tipoFisica = dto.getTipoFisica();
		this.perfilCliente = dto.getPerfilCliente();
		this.personaRepository = dto.getPersonaRepository();
		this.contactoRepository = dto.getContactoRepository();
		this.usuarioPersonaRepository = dto.getUsuarioPersonaRepository();
		this.municipioRepository = dto.getMunicipioRepository();
		this.direccionRepository = dto.getDireccionRepository();
		this.cuentaRepository = dto.getCuentaRepository();
		this.clienteRepository = dto.getClienteRepository();
		this.inversionAutomaticaRepository = dto.getInversionAutomaticaRepository();
		this.creditoRepository = dto.getCreditoRepository();
	}

	public ResponseEntity<RSB028> crear(final RQSV014 rqsv014) {

        RSB028 rsb028 = rqsv014.getRsb028();
        Sesion sesion = rqsv014.getSesion();
        LocalDateTime now = LocalDateTime.now();
        String idUsr = rqsv014.getUsuario();
        if (usuarioRepository.findByNameRegardlessOfStatus(idUsr).isPresent()) {
            return rsb028.badRequest(USERNAME_YA_EXISTE);
        }
        if (personaFisicaRepository.findByCurp(rqsv014.getCurp()).isPresent()) {
            return rsb028.badRequest(CURP_YA_EXISTE);
        }
        List<Contacto> contactos = procesarContactos(rqsv014.getContactos());
        if (contactos == null) {
            return rsb028.badRequest(SIN_COINCIDENCIAS_CON_EL_TIPO_DE_CONTACTO);
        }
        Usuario usuario = crearUsuario(rqsv014, sesion, now);
        Persona persona = crearPersona(sesion, now);
        asignarContactosAPersona(contactos, persona);
        crearUsuarioPersona(usuario, persona, sesion, now);
        Direccion direccion = crearDireccion(rqsv014, sesion, now);
        PersonaFisica personaFisica;
        personaFisica = crearPersonaFisica(rqsv014, persona, direccion, sesion);
        Cuenta cuenta = crearCuenta(usuario, sesion, now);
        Cliente cliente = crearCliente(personaFisica, cuenta, sesion, now);
        crearInversionAutomatica(cuenta, sesion, now);
        crearCredito(usuario, sesion, now);

        return rsb028.ok(cliente);
    }
	

    private List<Contacto> procesarContactos(
        final List<RQSV024> listaContactos) {

        if (listaContactos == null || listaContactos.isEmpty()) {
            return new ArrayList<>();
        }

        List<Contacto> contactosValidos = new ArrayList<>();
        for (RQSV024 contactoRq : listaContactos) {
            Contacto contacto = new Contacto();
            contacto.setValor(contactoRq.getValor());

            Optional<Catalogo> optionalTipo;
            optionalTipo = catalogoRepository.findByClave(contactoRq.getTipo());
            if (optionalTipo.isEmpty()) {
                return null;
            }
            Catalogo tipo = optionalTipo.get();
            contacto.setTipo(tipo);

            if (tipo == null || !tipoContacto.contains(tipo)) {
                return null;
            }
            if (!tipo.getEstatus().equals(activo)) {
                return null;
            }
            String key = tipo.getNombre();
            String valor = contacto.getValor();
            boolean invalido = switch (key) {
                case "E-MAIL" -> isNotValid(CORREO, valor);
                case "SITIO-WEB" -> isNotValid(URL, valor);
                default -> key.contains("TEL") && isNotValid(TELEFONO, valor);
            };
            if (invalido) {
                return null;
            }
            contactosValidos.add(contacto);
        }
        return contactosValidos;
    }
    

    private Usuario crearUsuario(final RQSV014 rqsv014,
            final Sesion sesion, final LocalDateTime now) {

        Usuario usuario = new Usuario();
        usuario.setNombre(rqsv014.getUsuario());
        usuario.setPassword(encoder(rqsv014.getPassword()));
        usuario.setCorreo(rqsv014.getCorreo());
        usuario.setTelefono(rqsv014.getTelefono());
        usuario.setPerfil(perfilCliente);
        usuario.setIdUsuarioModificado(sesion.getUsuario().getId());
        usuario.setFechaDeModificacion(now);
        usuario.setEstatus(pendiente);
        usuario.setClave(UUID.randomUUID());
        usuarioRepository.save(usuario);
        return usuario;
    }
    

    private Persona crearPersona(final Sesion sesion, final LocalDateTime now) {

        Persona persona = new Persona();
        persona.setTipo(tipoFisica);
        persona.setIdUsuarioModificado(sesion.getUsuario().getId());
        persona.setFechaDeModificacion(now);
        persona.setEstatus(activo);
        persona.setClave(UUID.randomUUID());
        personaRepository.save(persona);
        return persona;
    }
    

    private void asignarContactosAPersona(final List<Contacto> contactos,
            final Persona persona) {

        contactos.forEach(contacto -> {
            contacto.setPersona(persona);
            contactoRepository.save(contacto);
        });
    }
    

    private void crearUsuarioPersona(final Usuario usuario,
            final Persona persona, final Sesion sesion,
            final LocalDateTime now) {

        UsuarioPersona usuarioPersona = new UsuarioPersona();
        usuarioPersona.setUsuario(usuario);
        usuarioPersona.setPersona(persona);
        usuarioPersona.setIdUsuarioModificado(sesion.getUsuario().getId());
        usuarioPersona.setFechaDeModificacion(now);
        usuarioPersona.setEstatus(activo);
        usuarioPersona.setClave(UUID.randomUUID());
        usuarioPersonaRepository.save(usuarioPersona);
    }
    

    private Direccion crearDireccion(final RQSV014 rqsv014, final Sesion sesion,
            final LocalDateTime now) {

        Direccion direccion = new Direccion();
        direccion.setCalle(rqsv014.getCalle());
        direccion.setCodigoPostal(rqsv014.getCodigoPostal());
        rqsv014.getNumInt().ifPresent(direccion::setNumInt);
        direccion.setNumExt(rqsv014.getNumExt());
        direccion.setAsentamiento(rqsv014.getAsentamiento());
        direccion.setEntreCalle(rqsv014.getEntreCalle());
        rqsv014.getReferencia().ifPresent(direccion::setReferencia);

        Optional<Municipio> municipioOp;
        municipioOp = municipioRepository.findByClave(rqsv014.getMunicipio());
        direccion.setMunicipio(municipioOp.orElse(null));
        direccion.setLatitud(rqsv014.getLatitud());
        direccion.setLongitud(rqsv014.getLongitud());
        direccion.setIdUsuarioModificado(sesion.getUsuario().getId());
        direccion.setFechaDeModificacion(now);
        direccion.setEstatus(activo);
        direccion.setClave(UUID.randomUUID());
        return direccionRepository.save(direccion);
    }
    

    private PersonaFisica crearPersonaFisica(final RQSV014 rqsv014,
            final Persona persona, final Direccion direccion,
            final Sesion sesion) {

        LocalDateTime now = LocalDateTime.now();
        PersonaFisica personaFisica = new PersonaFisica();
        personaFisica.setPersona(persona);
        personaFisica.setNombre(rqsv014.getNombre());
        rqsv014.getApellidoPaterno().ifPresent(x -> {
            personaFisica.setApellidoPaterno(x);
        });
        rqsv014.getApellidoMaterno().ifPresent(x -> {
            personaFisica.setApellidoMaterno(x);
        });
        Optional<Catalogo> sexoOpt;
        sexoOpt = catalogoRepository.findByClave(rqsv014.getSexo());
        personaFisica.setSexo(sexoOpt.orElse(null));
        personaFisica.setFechaNacimiento(rqsv014.getFechaNacimiento());
        personaFisica.setCurp(rqsv014.getCurp());
        personaFisica.setDireccion(direccion);
        personaFisica.setIdUsuarioModificado(sesion.getUsuario().getId());
        personaFisica.setFechaDeModificacion(now);
        personaFisica.setEstatus(activo);
        personaFisica.setClave(UUID.randomUUID());
        return personaFisicaRepository.save(personaFisica);
    }
    

    private Cuenta crearCuenta(final Usuario usuario, final Sesion sesion,
            final LocalDateTime now) {

        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(usuario);
        cuenta.setIdUsuarioModificado(sesion.getUsuario().getId());
        cuenta.setFechaDeModificacion(now);
        cuenta.setEstatus(pendiente);
        cuenta.setClave(UUID.randomUUID());
        return cuentaRepository.save(cuenta);
    }
    

    private Cliente crearCliente(final PersonaFisica personaFisica,
            final Cuenta cuenta, final Sesion sesion, final LocalDateTime now) {

        Cliente cliente = new Cliente();
        cliente.setPersonaFisica(personaFisica);
        cliente.setCuenta(cuenta);
        cliente.setIdUsuarioModificado(sesion.getUsuario().getId());
        cliente.setFechaDeModificacion(now);
        cliente.setEstatus(pendiente);
        cliente.setClave(UUID.randomUUID());
        return clienteRepository.save(cliente);
    }
    

    private void crearInversionAutomatica(final Cuenta cuenta,
            final Sesion sesion, final LocalDateTime now) {

        InversionAutomatica inversionAutomatica = new InversionAutomatica();
        inversionAutomatica.setCuenta(cuenta);
        inversionAutomatica.setIdUsuarioModificado(sesion.getUsuario().getId());
        inversionAutomatica.setFechaModificado(now);
        inversionAutomatica.setEstatus(activo);
        inversionAutomatica.setClave(UUID.randomUUID());
        inversionAutomaticaRepository.save(inversionAutomatica);
    }
    

    private void crearCredito(final Usuario usuario, final Sesion sesion,
            final LocalDateTime now) {

        Credito credito = new Credito();
        credito.setUsuario(usuario);
        credito.setLimite(LIMITE_CREDITICIO);
        credito.setDisponible(credito.getLimite());
        credito.setTipo(riesgoC7);
        credito.setCalificacion(-1);
        credito.setIdUsuarioModificado(sesion.getUsuario().getId());
        credito.setFechaModificado(now);
        credito.setEstatus(activo);
        credito.setClave(UUID.randomUUID());
        creditoRepository.save(credito);
    }
    private boolean isNotValid(final String regex, final String valor) {
        return !Pattern.matches(regex, valor);
    }
    
    public abstract String encoder(String texto);
}
