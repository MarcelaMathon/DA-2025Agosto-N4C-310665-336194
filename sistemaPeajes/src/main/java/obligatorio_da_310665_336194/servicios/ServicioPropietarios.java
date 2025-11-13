package obligatorio_da_310665_336194.servicios;

import java.util.Collection;
import obligatorio_da_310665_336194.dominio.Propietario;
import obligatorio_da_310665_336194.dominio.EstadosPropietario;
import java.util.List;
import obligatorio_da_310665_336194.dominio.EstadoPropietario;

public class ServicioPropietarios {

	private Collection<Propietario> propietario;

	private EstadosPropietario estadosPropietario;

	public boolean puedeTransitar(Propietario propietario) {
		return false;
	}

	/**
	 *  
	 */
	public Propietario getPropietario() {
		return null;
	}

	public Propietario buscarPropietarioPorCedula(String cedula) {
		return null;
	}

	public List<EstadoPropietario> getEstados() {
		return null;
	}

	public Propietario cambiarEstado(Propietario propietario, EstadoPropietario nuevoEstado) {
		return null;
	}

}
