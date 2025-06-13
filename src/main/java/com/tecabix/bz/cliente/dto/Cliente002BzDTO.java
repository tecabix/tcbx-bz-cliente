package com.tecabix.bz.cliente.dto;

import java.util.List;

import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.entity.Perfil;
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

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Cliente002BzDTO {

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Usuario}.
     */
    private UsuarioRepository usuarioRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link PersonaFisica}.
     */
    private PersonaFisicaRepository personaFisicaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Catalogo}.
     */
    private CatalogoRepository catalogoRepository;

    /**
     * Lista de catálogos que define los tipos de contacto disponibles. Cada
     * elemento representa un tipo de contacto permitido en el sistema.
     */
    private List<Catalogo> tipoContacto;

    /**
     * Catálogo que indica si la entidad está activa.
     */
    private Catalogo activo;

    /**
     * Catálogo que indica si la entidad está pendiente.
     */
    private Catalogo pendiente;

    /**
     * Catálogo que define el nivel de riesgo de crédito (C7) asignado a nuevos
     * clientes.
     */
    private Catalogo riesgoC7;

    /**
     * Catálogo que representa el tipo de persona física utilizado para crear
     * una entidad {@link Persona}.
     */
    private Catalogo tipoFisica;

    /**
     * Perfil asignado a los clientes en el sistema.
     */
    private Perfil perfilCliente;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Persona}.
     */
    private PersonaRepository personaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Contacto}.
     */
    private ContactoRepository contactoRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link UsuarioPersona}.
     */
    private UsuarioPersonaRepository usuarioPersonaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Municipio}.
     */
    private MunicipioRepository municipioRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Direccion}.
     */
    private DireccionRepository direccionRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Cuenta}.
     */
    private CuentaRepository cuentaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Cliente}.
     */
    private ClienteRepository clienteRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link InversionAutomatica}.
     */
    private InversionAutomaticaRepository inversionAutomaticaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad
     * {@link Credito}.
     */
    private CreditoRepository creditoRepository;

    /**
     * Obtiene el repositorio de usuarios.
     * @return el repositorio de usuarios.
     */
    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    /**
     * Establece el repositorio de usuarios.
     * @param repository el repositorio de usuarios a establecer.
     */
    public void setUsuarioRepository(final UsuarioRepository repository) {
        this.usuarioRepository = repository;
    }

    /**
     * Obtiene el repositorio de personas físicas.
     * @return el repositorio de personas físicas.
     */
    public PersonaFisicaRepository getPersonaFisicaRepository() {
        return personaFisicaRepository;
    }

    /**
     * Establece el repositorio de personas físicas.
     * @param repository el repositorio de personas físicas
     *                                a establecer.
     */
    public void setPersonaFisicaRepository(
        final PersonaFisicaRepository repository) {
        this.personaFisicaRepository = repository;
    }

    /**
     * Obtiene el repositorio de catálogos.
     * @return el repositorio de catálogos.
     */
    public CatalogoRepository getCatalogoRepository() {
        return catalogoRepository;
    }

    /**
     * Establece el repositorio de catálogos.
     * @param repository el repositorio de catálogos a establecer.
     */
    public void setCatalogoRepository(final CatalogoRepository repository) {
        this.catalogoRepository = repository;
    }

    /**
     * Obtiene la lista de tipos de contacto.
     * @return la lista de tipos de contacto.
     */
    public List<Catalogo> getTipoContacto() {
        return tipoContacto;
    }

    /**
     * Establece la lista de tipos de contacto.
     * @param contacto la lista de tipos de contacto a establecer.
     */
    public void setTipoContacto(final List<Catalogo> contacto) {
        this.tipoContacto = contacto;
    }

    /**
     * Obtiene el catálogo de estado activo.
     * @return el catálogo de estado activo.
     */
    public Catalogo getActivo() {
        return activo;
    }

    /**
     * Establece el catálogo de estado activo.
     * @param estatus el catálogo de estado activo a establecer.
     */
    public void setActivo(final Catalogo estatus) {
        this.activo = estatus;
    }

    /**
     * Obtiene el catálogo de estado pendiente.
     * @return el catálogo de estado pendiente.
     */
    public Catalogo getPendiente() {
        return pendiente;
    }

    /**
     * Establece el catálogo de estado pendiente.
     * @param estatus el catálogo de estado pendiente a establecer.
     */
    public void setPendiente(final Catalogo estatus) {
        this.pendiente = estatus;
    }

    /**
     * Obtiene el catálogo de riesgo C7.
     * @return el catálogo de riesgo C7.
     */
    public Catalogo getRiesgoC7() {
        return riesgoC7;
    }

    /**
     * Establece el catálogo de riesgo C7.
     * @param riesgo el catálogo de riesgo C7 a establecer.
     */
    public void setRiesgoC7(final Catalogo riesgo) {
        this.riesgoC7 = riesgo;
    }

    /**
     * Obtiene el catálogo de tipo física.
     * @return el catálogo de tipo física.
     */
    public Catalogo getTipoFisica() {
        return tipoFisica;
    }

    /**
     * Establece el catálogo de tipo física.
     * @param fisica el catálogo de tipo física a establecer.
     */
    public void setTipoFisica(final Catalogo fisica) {
        this.tipoFisica = fisica;
    }

    /**
     * Obtiene el perfil del cliente.
     * @return el perfil del cliente.
     */
    public Perfil getPerfilCliente() {
        return perfilCliente;
    }

    /**
     * Establece el perfil del cliente.
     * @param cliente el perfil del cliente a establecer.
     */
    public void setPerfilCliente(final Perfil cliente) {
        this.perfilCliente = cliente;
    }

    /**
     * Obtiene el repositorio de personas.
     * @return el repositorio de personas.
     */
    public PersonaRepository getPersonaRepository() {
        return personaRepository;
    }

    /**
     * Establece el repositorio de personas.
     * @param repository el repositorio de personas a establecer.
     */
    public void setPersonaRepository(final PersonaRepository repository) {
        this.personaRepository = repository;
    }

    /**
     * Obtiene el repositorio de contactos.
     * @return el repositorio de contactos.
     */
    public ContactoRepository getContactoRepository() {
        return contactoRepository;
    }

    /**
     * Establece el repositorio de contactos.
     * @param repository el repositorio de contactos a establecer.
     */
    public void setContactoRepository(final ContactoRepository repository) {
        this.contactoRepository = repository;
    }

    /**
     * Obtiene el repositorio de usuario-persona.
     * @return el repositorio de usuario-persona.
     */
    public UsuarioPersonaRepository getUsuarioPersonaRepository() {
        return usuarioPersonaRepository;
    }

    /**
     * Establece el repositorio de usuario-persona.
     * @param repository el repositorio de usuario-persona
     *                                 a establecer.
     */
    public void setUsuarioPersonaRepository(
        final UsuarioPersonaRepository repository) {

        this.usuarioPersonaRepository = repository;
    }

    /**
     * Obtiene el repositorio de municipios.
     * @return el repositorio de municipios.
     */
    public MunicipioRepository getMunicipioRepository() {
        return municipioRepository;
    }

    /**
     * Establece el repositorio de municipios.
     * @param repository el repositorio de municipios a establecer.
     */
    public void setMunicipioRepository(final MunicipioRepository repository) {
        this.municipioRepository = repository;
    }

    /**
     * Obtiene el repositorio de direcciones.
     * @return el repositorio de direcciones.
     */
    public DireccionRepository getDireccionRepository() {
        return direccionRepository;
    }

    /**
     * Establece el repositorio de direcciones.
     * @param repository el repositorio de direcciones a establecer.
     */
    public void setDireccionRepository(final DireccionRepository repository) {
        this.direccionRepository = repository;
    }

    /**
     * Obtiene el repositorio de cuentas.
     * @return el repositorio de cuentas.
     */
    public CuentaRepository getCuentaRepository() {
        return cuentaRepository;
    }

    /**
     * Establece el repositorio de cuentas.
     * @param repository el repositorio de cuentas a establecer.
     */
    public void setCuentaRepository(final CuentaRepository repository) {
        this.cuentaRepository = repository;
    }

    /**
     * Obtiene el repositorio de clientes.
     * @return el repositorio de clientes.
     */
    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    /**
     * Establece el repositorio de clientes.
     * @param repository el repositorio de clientes a establecer.
     */
    public void setClienteRepository(final ClienteRepository repository) {
        this.clienteRepository = repository;
    }

    /**
     * Obtiene el repositorio de inversiones automáticas.
     * @return el repositorio de inversiones automáticas.
     */
    public InversionAutomaticaRepository getInversionAutomaticaRepository() {
        return inversionAutomaticaRepository;
    }

    /**
     * Establece el repositorio de inversiones automáticas.
     * @param repository el repositorio de inversiones
     *                                      automáticas a establecer.
     */
    public void setInversionAutomaticaRepository(
        final InversionAutomaticaRepository repository) {

        this.inversionAutomaticaRepository = repository;
    }

    /**
     * Obtiene el repositorio de créditos.
     * @return el repositorio de créditos.
     */
    public CreditoRepository getCreditoRepository() {
        return creditoRepository;
    }

    /**
     * Establece el repositorio de créditos.
     * @param repository el repositorio de créditos a establecer.
     */
    public void setCreditoRepository(final CreditoRepository repository) {
        this.creditoRepository = repository;
    }

}
