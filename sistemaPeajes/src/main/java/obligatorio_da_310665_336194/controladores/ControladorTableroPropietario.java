package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.servicios.Fachada;
import java.util.List;

import obligatorio_da_310665_336194.dominio.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.Propietario;
import obligatorio_da_310665_336194.utils.Respuesta;
import obligatorio_da_310665_336194.dominio.Transito;
import obligatorio_da_310665_336194.dominio.Vehiculo;

public class ControladorTableroPropietario {

	// TODO: hacer DTOS y ver como devolver la información en inicializar tablero
	Fachada fachada = Fachada.getInstancia();

	public List<Respuesta> inicializarTablero() {
		Propietario propietario = getPropietario();
		List<AsignacionDeBonificacion> bonificaciones = obtenerBonificacionesPropietario(propietario);
		List<Transito> transitos = getTransitosPropietario(propietario);
		List<Vehiculo> vehiculos = getVehiculosPropietario(propietario);

		return Respuesta.lista(
				new Respuesta("inicializarTablero",
						new Object[] { propietario, bonificaciones, transitos, vehiculos }));
	}

	private List<AsignacionDeBonificacion> obtenerBonificacionesPropietario(Propietario propietario) {
		return fachada.obtenerBonificacionesPropietario(propietario);
	}

	private List<Transito> getTransitosPropietario(Propietario propietario) {
		return fachada.getTransitosPropietario(propietario);
	}

	private List<Vehiculo> getVehiculosPropietario(Propietario propietario) {
		return fachada.getVehiculosPropietario(propietario);
	}

	private Propietario getPropietario() {
		return fachada.getPropietario();
	}

	public List<Respuesta> getNotificacionesPropietario(Propietario propietario) {
		return null;
	}

	public List<Respuesta> borrarNotificaciones(Propietario propietario) {
		return null;
	}

}
