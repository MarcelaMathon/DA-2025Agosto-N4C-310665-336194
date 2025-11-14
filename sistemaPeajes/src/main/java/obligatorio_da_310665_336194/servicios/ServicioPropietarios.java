package obligatorio_da_310665_336194.servicios;

import java.util.ArrayList;
import obligatorio_da_310665_336194.dominio.Propietario;
import obligatorio_da_310665_336194.dominio.EstadosPropietario;
import java.util.List;
import obligatorio_da_310665_336194.dominio.EstadoPropietario;

public class ServicioPropietarios {

	private ArrayList<Propietario> propietarios;

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
		for (Propietario p : propietarios) {
			if (p.getCedula().equals(cedula)) {
				return p;
			}
		}
		return null;
	}

	public List<EstadoPropietario> getEstados() {
		return estadosPropietario.getEstados();
	}

	public Propietario cambiarEstado(String cedula, EstadoPropietario nuevoEstado) {
		Propietario propietario = buscarPropietarioPorCedula(cedula);
		if (propietario != null) {
			propietario.cambiarEstado(nuevoEstado);
		}
		return propietario;
	}

}
