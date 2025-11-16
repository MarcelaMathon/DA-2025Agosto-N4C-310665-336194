package obligatorio_da_310665_336194.servicios;

import java.util.ArrayList;

import obligatorio_da_310665_336194.dominio.notificacion.Notificacion;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;

import java.util.List;

public class ServicioNotificaciones {

	private ArrayList<Notificacion> notificaciones = new ArrayList<>();

	public List<Notificacion> getNotificacionesPropietario(Propietario propietario) {
		List<Notificacion> notificacionesPropietario = new ArrayList<>();
		for (Notificacion notificacion : notificaciones) {
			if (notificacion.getPropietario().equals(propietario)) {
				notificacionesPropietario.add(notificacion);
			}
		}
		return notificacionesPropietario;
	}

	public void borrarNotificaciones(Propietario propietario) {

	}

}
