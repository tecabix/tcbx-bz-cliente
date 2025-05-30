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

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public PersonaFisicaRepository getPersonaFisicaRepository() {
		return personaFisicaRepository;
	}

	public void setPersonaFisicaRepository(PersonaFisicaRepository personaFisicaRepository) {
		this.personaFisicaRepository = personaFisicaRepository;
	}

	public CatalogoRepository getCatalogoRepository() {
		return catalogoRepository;
	}

	public void setCatalogoRepository(CatalogoRepository catalogoRepository) {
		this.catalogoRepository = catalogoRepository;
	}

	public List<Catalogo> getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(List<Catalogo> tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public Catalogo getActivo() {
		return activo;
	}

	public void setActivo(Catalogo activo) {
		this.activo = activo;
	}

	public Catalogo getPendiente() {
		return pendiente;
	}

	public void setPendiente(Catalogo pendiente) {
		this.pendiente = pendiente;
	}

	public Catalogo getRiesgoC7() {
		return riesgoC7;
	}

	public void setRiesgoC7(Catalogo riesgoC7) {
		this.riesgoC7 = riesgoC7;
	}

	public Catalogo getTipoFisica() {
		return tipoFisica;
	}

	public void setTipoFisica(Catalogo tipoFisica) {
		this.tipoFisica = tipoFisica;
	}

	public Perfil getPerfilCliente() {
		return perfilCliente;
	}

	public void setPerfilCliente(Perfil perfilCliente) {
		this.perfilCliente = perfilCliente;
	}

	public PersonaRepository getPersonaRepository() {
		return personaRepository;
	}

	public void setPersonaRepository(PersonaRepository personaRepository) {
		this.personaRepository = personaRepository;
	}

	public ContactoRepository getContactoRepository() {
		return contactoRepository;
	}

	public void setContactoRepository(ContactoRepository contactoRepository) {
		this.contactoRepository = contactoRepository;
	}

	public UsuarioPersonaRepository getUsuarioPersonaRepository() {
		return usuarioPersonaRepository;
	}

	public void setUsuarioPersonaRepository(UsuarioPersonaRepository usuarioPersonaRepository) {
		this.usuarioPersonaRepository = usuarioPersonaRepository;
	}

	public MunicipioRepository getMunicipioRepository() {
		return municipioRepository;
	}

	public void setMunicipioRepository(MunicipioRepository municipioRepository) {
		this.municipioRepository = municipioRepository;
	}

	public DireccionRepository getDireccionRepository() {
		return direccionRepository;
	}

	public void setDireccionRepository(DireccionRepository direccionRepository) {
		this.direccionRepository = direccionRepository;
	}

	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public InversionAutomaticaRepository getInversionAutomaticaRepository() {
		return inversionAutomaticaRepository;
	}

	public void setInversionAutomaticaRepository(InversionAutomaticaRepository inversionAutomaticaRepository) {
		this.inversionAutomaticaRepository = inversionAutomaticaRepository;
	}

	public CreditoRepository getCreditoRepository() {
		return creditoRepository;
	}

	public void setCreditoRepository(CreditoRepository creditoRepository) {
		this.creditoRepository = creditoRepository;
	}
}
