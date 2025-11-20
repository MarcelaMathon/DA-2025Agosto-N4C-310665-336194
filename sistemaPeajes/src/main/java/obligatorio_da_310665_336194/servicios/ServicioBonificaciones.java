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
		return propietario.getAsignacionesDeBonificacion();
	}

	public void agregar(Bonificacion bonificacion) {
		if (bonificacion != null) {
			this.bonificaciones.add(bonificacion);
		}
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
		propietario.getAsignacionesDeBonificacion().add(nuevaAsignacion);
		puesto.getAsignacionesDeBonificacion().add(nuevaAsignacion);
		asignacionDeBonificaciones.add(nuevaAsignacion);

		propietario.notificarBonificacion();

		return nuevaAsignacion;
	}

	public List<String> obtenerNombresTiposBonificacion() {
		return TipoBonificacion.obtenerNombresDisponibles();
	}

	public TipoBonificacion crearTipoBonificacion(String nombre) throws PeajesExceptions {
		TipoBonificacion tipo = TipoBonificacion.crearPorNombre(nombre);
		if (tipo == null) {
			throw new PeajesExceptions("Tipo de bonificación no válido: " + nombre);
		}
		return tipo;
	}

}
