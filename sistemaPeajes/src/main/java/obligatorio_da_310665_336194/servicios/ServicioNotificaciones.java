package obligatorio_da_310665_336194.servicios;

import java.util.ArrayList;

import obligatorio_da_310665_336194.dominio.notificacion.Notificacion;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;

import java.util.Date;
import java.util.List;

public class ServicioNotificaciones {

	private ArrayList<Notificacion> notificaciones = new ArrayList<>();

	public void enviarNotificacion(Propietario propietario, String mensaje) {
		Notificacion notificacion = new Notificacion(propietario, mensaje, new Date());
		notificaciones.add(notificacion);
	}

	public void enviarSaldoBajo(Propietario propietario) {
		String mensaje = new Date() + " Tu saldo actual es de $ " + propietario.getSaldoActual()
				+ " Te recomendamos hacer una recarga";
		enviarNotificacion(propietario, mensaje);
	}

	public List<Notificacion> getNotificacionesPropietario(Propietario propietario) {
		return null;
	}

	public void borrarNotificaciones(Propietario propietario) {

	}

}
