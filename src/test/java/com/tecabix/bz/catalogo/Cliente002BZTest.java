package com.tecabix.bz.catalogo;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tecabix.bz.cliente.Cliente002BZ;
import com.tecabix.bz.cliente.dto.Cliente002BzDTO;
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
@ExtendWith(MockitoExtension.class)
public class Cliente002BZTest {

	@Mock
	private UsuarioRepository usuarioRepository;

	@Mock
	private PersonaFisicaRepository personaFisicaRepository;

	@Mock
	private CatalogoRepository catalogoRepository;

	@Mock
	private List<Catalogo> tipoContacto;

	@Mock
	private Catalogo activo;

	@Mock
	private Catalogo pendiente;

	@Mock
	private Catalogo riesgoC7;

	@Mock
	private Catalogo tipoFisica;

	@Mock
	private Perfil perfilCliente;

	@Mock
	private PersonaRepository personaRepository;

	@Mock
	private ContactoRepository contactoRepository;

	@Mock
	private UsuarioPersonaRepository usuarioPersonaRepository;

	@Mock
	private MunicipioRepository municipioRepository;

	@Mock
	private DireccionRepository direccionRepository;

	@Mock
	private CuentaRepository cuentaRepository;

	@Mock
	private ClienteRepository clienteRepository;

	@Mock
	private InversionAutomaticaRepository inversionAutomaticaRepository;

	@Mock
	private CreditoRepository creditoRepository;
	
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
	    
		Cliente002BZ cliente002bz = new Cliente002BZ(dto) {
			@Override
			public String encoder(String texto) {
				return texto;
			}
		};
		
	}

}
