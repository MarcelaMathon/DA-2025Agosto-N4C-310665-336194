package obligatorio_da_310665_336194.servicios;

import java.util.ArrayList;

import obligatorio_da_310665_336194.dominio.propietario.EstadoPropietario;
import obligatorio_da_310665_336194.dominio.propietario.EstadosPropietario;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;

import java.util.List;

public class ServicioPropietarios {

	private ArrayList<Propietario> propietarios;

	private EstadosPropietario estadosPropietario;

	public ServicioPropietarios() {
		this.propietarios = new ArrayList<>();
	}

	public void agregar(Propietario propietario) {
		if (propietario != null) {
			this.propietarios.add(propietario);
		}
	}

	public boolean puedeTransitar(Propietario propietario) {
		return propietario.puedeTransitar();
	}

	public Propietario buscarPropietarioPorCedula(String cedula) throws PeajesExceptions {
		for (Propietario p : propietarios) {
			if (p.getCedula().equals(cedula)) {
				return p;
			}
		}
		throw new PeajesExceptions("No existe el propietario");
	}

	public List<EstadoPropietario> getEstados() {
		return estadosPropietario.getEstados();
	}

	public Propietario cambiarEstado(String cedula, EstadoPropietario nuevoEstado) throws PeajesExceptions {
		Propietario propietario = buscarPropietarioPorCedula(cedula);
		if (propietario != null) {
			propietario.cambiarEstado(nuevoEstado);
		}
		return propietario;
	}

}
