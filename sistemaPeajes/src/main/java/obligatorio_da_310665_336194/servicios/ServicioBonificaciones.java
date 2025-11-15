package obligatorio_da_310665_336194.servicios;

import java.util.ArrayList;

import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.bonificacion.Bonificacion;
import obligatorio_da_310665_336194.dominio.bonificacion.TipoBonificacion;

import java.util.List;

import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;

public class ServicioBonificaciones {

	private ArrayList<AsignacionDeBonificacion> asignacionDeBonificaciones;

	private ArrayList<Bonificacion> bonificaciones;

	public ServicioBonificaciones() {
		this.asignacionDeBonificaciones = new ArrayList<>();
		this.bonificaciones = new ArrayList<>();
	}

	public AsignacionDeBonificacion obtenerBonificacion(Puesto puesto, Propietario propietario) {
		for (AsignacionDeBonificacion asignacion : asignacionDeBonificaciones) {
			if (asignacion.getPropietario().equals(propietario) &&
					asignacion.getPuesto().equals(puesto)) {
				return asignacion;
			}
		}
		return null;
	}

	public List<AsignacionDeBonificacion> obtenerBonificacionesPropietario(Propietario propietario) {
		List<AsignacionDeBonificacion> bonificacionesPropietario = new ArrayList<>();
		for (AsignacionDeBonificacion asignacion : asignacionDeBonificaciones) {
			if (asignacion.getPropietario().equals(propietario)) {
				bonificacionesPropietario.add(asignacion);
			}
		}
		return bonificacionesPropietario;
	}

	public List<Bonificacion> getBonificaciones() {
		return new ArrayList<>(bonificaciones);
	}

	public AsignacionDeBonificacion asignarBonificacion(Propietario propietario, Puesto puesto,
			TipoBonificacion tipoBonificacion) throws PeajesExceptions {

		AsignacionDeBonificacion asignacionExistente = obtenerBonificacion(puesto, propietario);
		if (asignacionExistente != null) {
			throw new PeajesExceptions(
					"Ya tiene una bonificación asignada para ese puesto");
		}

		AsignacionDeBonificacion nuevaAsignacion = new AsignacionDeBonificacion(propietario, puesto, tipoBonificacion);

		asignacionDeBonificaciones.add(nuevaAsignacion);

		return nuevaAsignacion;
	}

}
