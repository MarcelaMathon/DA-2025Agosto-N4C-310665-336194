package obligatorio_da_310665_336194.dominio.bonificacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;

public abstract class TipoBonificacion {

	private static final List<TipoBonificacion> tiposDisponibles = List.of(
			new Exonerado(),
			new Frecuente(),
			new Trabajador());

	public abstract String getTipoBonificacion();

	public abstract Double obtenerDescuento(AsignacionDeBonificacion asignacion, Vehiculo vehiculo,
			Date fechaHoraTransito);

	public static List<String> obtenerNombresDisponibles() {
		List<String> nombres = new ArrayList<>();
		for (TipoBonificacion tipo : tiposDisponibles) {
			nombres.add(tipo.getTipoBonificacion());
		}
		return nombres;
	}

	public static TipoBonificacion crearPorNombre(String nombre) {
		if (nombre == null) {
			return null;
		}
		for (TipoBonificacion tipo : tiposDisponibles) {
			if (tipo.getTipoBonificacion().equals(nombre)) {
				// Crear una nueva instancia del mismo tipo
				return crearNuevaInstancia(tipo);
			}
		}
		return null;
	}

	private static TipoBonificacion crearNuevaInstancia(TipoBonificacion tipo) {
		if (tipo instanceof Exonerado) {
			return new Exonerado();
		} else if (tipo instanceof Frecuente) {
			return new Frecuente();
		} else if (tipo instanceof Trabajador) {
			return new Trabajador();
		}
		return null;
	}

}
