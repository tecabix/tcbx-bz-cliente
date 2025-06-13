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

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Usuario}.
     */
    private final UsuarioRepository usuarioRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link PersonaFisica}.
     */
    private final PersonaFisicaRepository personaFisicaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Catalogo}.
     */
    private final CatalogoRepository catalogoRepository;

    /**
     * Lista de catálogos que define los tipos de contacto disponibles. Cada
     * elemento representa un tipo de contacto permitido en el sistema.
     */
    private final List<Catalogo> tipoContacto;

    /**
     * Catálogo que representa el estado "Activo" en el sistema.
     */
    private final Catalogo activo;

    /**
     * Catálogo que representa el estado "Pendiente" en el sistema.
     */
    private final Catalogo pendiente;

    /**
     * Catálogo que define el nivel de riesgo de crédito (C7) asignado a nuevos
     * clientes.
     */
    private final Catalogo riesgoC7;

    /**
     * Catálogo que representa el tipo de persona física utilizado para crear
     * una entidad {@link Persona}.
     */
    private final Catalogo tipoFisica;

    /**
     * Perfil asignado a los clientes en el sistema.
     */
    private final Perfil perfilCliente;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Persona}.
     */
    private final PersonaRepository personaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Contacto}.
     */
    private final ContactoRepository contactoRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link UsuarioPersona}.
     */
    private final UsuarioPersonaRepository usuarioPersonaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Municipio}.
     */
    private final MunicipioRepository municipioRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Direccion}.
     */
    private final DireccionRepository direccionRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Cuenta}.
     */
    private final CuentaRepository cuentaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Cliente}.
     */
    private final ClienteRepository clienteRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link InversionAutomatica}.
     */
    private final InversionAutomaticaRepository inversionAutomaticaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Credito}.
     */
    private final CreditoRepository creditoRepository;

    /**
     * Mensaje cuando el username ya existe.
     */
    private static final String USERNAME_YA_EXISTE;

    /**
     * Mensaje cuando el CURP ya existe.
     */
    private static final String CURP_YA_EXISTE;

    /**
     * Mensaje cuando no hay coincidencias con el tipo de contacto.
     */
    private static final String SIN_COINCIDENCIAS_CON_EL_TIPO_DE_CONTACTO;

    /**
     * Expresión para correo electrónico.
     */
    private static final String CORREO;

    /**
     * Expresión para URLs.
     */
    private static final String URL;

    /**
     * Expresión para teléfono.
     */
    private static final String TELEFONO;

    static {
        USERNAME_YA_EXISTE = "El username ya existe.";
        CURP_YA_EXISTE = "El CURP ya existe.";
        SIN_COINCIDENCIAS_CON_EL_TIPO_DE_CONTACTO = "Sin coincidencias con el tipo de contacto.";
        CORREO = "(?!\\s)([A-Za-zñÑáéíóúÁÉÍÓÚüÜ0-9]+([.-][A-Za-zñÑáéíóúÁÉÍÓÚüÜ0-9_]+)*@[A-Za-zñÑáéíóúÁÉÍÓÚüÜ0-9]+([.][A-Za-zñÑáéíóúÁÉÍÓÚüÜ0-9_]+)*[.][A-Za-zñÑáéíóúÁÉÍÓÚüÜ]{2,3})";
        URL = "(?=.{15,45}$)https?:\\/\\/[A-Za-zñÑáéíóúÁÉÍÓÚüÜ0-9]+[...]";
        TELEFONO = "\\d{10}";
    }

    /**
     * Límite crediticio por defecto.
     */
    private static final int LIMITE_CREDITICIO = 350_00;

    /**
     * Constructor de la clase {@code Cliente002BZ} que inicializa sus
     * dependencias a partir de un objeto {@code Cliente002BzDTO}.
     *
     * @param dto Objeto de transferencia de datos que contiene las dependencias
     *            necesarias para inicializar la instancia
     *            de {@code Cliente002BZ}.
     */
    public Cliente002BZ(final Cliente002BzDTO dto) {
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
        this.inversionAutomaticaRepository =
            dto.getInversionAutomaticaRepository();
        this.creditoRepository = dto.getCreditoRepository();
    }

    /**
     * Crea un nuevo cliente en el sistema, coordinando la creación y
     * persistencia  de varias entidades.
     * <p>
     * El proceso de creación incluye las siguientes operaciones:
     * <ul>
     * <li>Validación de que el nombre de usuario y CURP no existan
     * previamente.</li>
     * <li>
     * Creación del objeto {@link Usuario} con encriptación de
     * contraseña.
     * </li>
     * <li>
     * Registro de la {@link Persona} y sus {@link Contacto}s asociados.
     * </li>
     * <li>
     * Establecimiento de la relación entre {@link Usuario} y {@link Persona} a
     * través de {@link UsuarioPersona}.
     * </li>
     * <li>Persistencia de la {@link Direccion} del cliente.</li>
     * <li>Registro de la {@link PersonaFisica} con sus datos personales.</li>
     * <li>Creación y asociación de la {@link Cuenta} del cliente.</li>
     * <li>Registro del {@link Cliente} en el sistema.</li>
     * <li>Inicialización de la {@link InversionAutomatica} para la cuenta.</li>
     * <li>Creación del {@link Credito} con un límite predeterminado y nivel de
     * riesgo asignado.</li>
     * </ul>
     * Cada paso realiza las validaciones necesarias y utiliza los repositorios
     * para la persistencia de la información.
     * </p>
     *
     * @param rqsv014 objeto de solicitud que encapsula todos los datos
     *                necesarios para la creación del cliente.
     * @return un {@link ResponseEntity} con el objeto {@link RSB028} que
     *         contiene el resultado de la operación, o un error si alguna
     */
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

    /**
     * Procesa y valida la lista de contactos proporcionada.
     * <p>
     * Para cada contacto se realiza lo siguiente:
     * <ul>
     * <li>Se obtiene el catálogo del tipo de contacto.</li>
     * <li>Se valida que el tipo exista, sea válido y se encuentre entre
     * los tipos permitidos.</li>
     * <li>Se comprueba que el estatus del tipo sea "activo".</li>
     * <li>Se valida el formato del valor del contacto según su tipo:
     * <ul>
     * <li>"E-MAIL" se valida con el formato de correo.</li>
     * <li>"SITIO-WEB" se valida con el formato de URL.</li>
     * <li>Si el tipo contiene "TEL", se valida con el formato de teléfono.</li>
     * </ul>
     * </li>
     * </ul>
     * Si alguna validación falla, se retorna {@code null}.
     * </p>
     *
     * @param listaContactos Lista de contactos provenientes de la solicitud.
     * @return Lista de contactos válidos o {@code null} si se detecta un
     *         contacto no válido.
     */
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

    /**
     * Crea y persiste un objeto {@link Usuario} a partir de los datos
     * proporcionados en la solicitud.
     *
     * @param rqsv014 objeto de solicitud con los datos del usuario.
     * @param sesion  sesión actual del usuario que realiza la operación.
     * @param now     fecha y hora actual.
     * @return el objeto {@link Usuario} creado y persistido.
     */
    private Usuario crearUsuario(
        final RQSV014 rqsv014, final Sesion sesion, final LocalDateTime now) {

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

    private void asignarContactosAPersona(
        final List<Contacto> contactos, final Persona persona) {

        contactos.forEach(contacto -> {
            contacto.setPersona(persona);
            contactoRepository.save(contacto);
        });
    }

    private void crearUsuarioPersona(
        final Usuario usuario, final Persona persona, final Sesion sesion,
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

    private Direccion crearDireccion(
        final RQSV014 rqsv014, final Sesion sesion, final LocalDateTime now) {

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

    private PersonaFisica crearPersonaFisica(
        final RQSV014 rqsv014, final Persona persona, final Direccion direccion,
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

    private Cuenta crearCuenta(
        final Usuario usuario, final Sesion sesion, final LocalDateTime now) {

        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(usuario);
        cuenta.setIdUsuarioModificado(sesion.getUsuario().getId());
        cuenta.setFechaDeModificacion(now);
        cuenta.setEstatus(pendiente);
        cuenta.setClave(UUID.randomUUID());
        return cuentaRepository.save(cuenta);
    }

    private Cliente crearCliente(
        final PersonaFisica personaFisica, final Cuenta cuenta,
        final Sesion sesion, final LocalDateTime now) {

        Cliente cliente = new Cliente();
        cliente.setPersonaFisica(personaFisica);
        cliente.setCuenta(cuenta);
        cliente.setIdUsuarioModificado(sesion.getUsuario().getId());
        cliente.setFechaDeModificacion(now);
        cliente.setEstatus(pendiente);
        cliente.setClave(UUID.randomUUID());
        return clienteRepository.save(cliente);
    }

    private void crearInversionAutomatica(
        final Cuenta cuenta, final Sesion sesion, final LocalDateTime now) {

        InversionAutomatica inversionAutomatica = new InversionAutomatica();
        inversionAutomatica.setCuenta(cuenta);
        inversionAutomatica.setIdUsuarioModificado(sesion.getUsuario().getId());
        inversionAutomatica.setFechaModificado(now);
        inversionAutomatica.setEstatus(activo);
        inversionAutomatica.setClave(UUID.randomUUID());
        inversionAutomaticaRepository.save(inversionAutomatica);
    }

    private void crearCredito(
        final Usuario usuario, final Sesion sesion, final LocalDateTime now) {

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

    /**
     * Codifica el texto proporcionado utilizando una estrategia definida
     * por la implementación concreta de esta clase.
     *
     * @param texto El texto que se desea codificar.
     * @return Una cadena que representa el texto codificado.
     */
    public abstract String encoder(String texto);
}
