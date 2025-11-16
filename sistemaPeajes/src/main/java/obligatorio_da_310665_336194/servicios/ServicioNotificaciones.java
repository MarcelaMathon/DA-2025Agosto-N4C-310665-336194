package obligatorio_da_310665_336194.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import obligatorio_da_310665_336194.dominio.ObservableAbstracto;
import obligatorio_da_310665_336194.dominio.Observador;
import obligatorio_da_310665_336194.dominio.notificacion.Notificacion;
import obligatorio_da_310665_336194.dominio.propietario.EventoPropietario;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;

public class ServicioNotificaciones implements Observador {

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
		notificaciones.removeIf(notificacion -> notificacion.getPropietario().equals(propietario));
	}

	@Override
	public void actualizar(ObservableAbstracto origen, Object evento) {
		if (!(evento instanceof EventoPropietario)) {
			return;
		}

		EventoPropietario eventoPropietario = (EventoPropietario) evento;
		Propietario propietario = eventoPropietario.getPropietario();
		Propietario.EventosPropietario tipoEvento = eventoPropietario.getTipo();

		switch (tipoEvento) {
			case SALDO_BAJO:
				procesarNotificacionSaldoBajo(propietario);
				break;
			case ESTADO_CAMBIADO:
				procesarNotificacionCambioEstado(propietario);
				break;
			case TRANSITO_REALIZADO:
				if (!propietario.esPenalizado()) {
					procesarNotificacionTransito(propietario);
				}
				break;
		}
	}

	private void procesarNotificacionSaldoBajo(Propietario propietario) {
		if (propietario.recibeNotificaciones() != null && propietario.recibeNotificaciones()) {
			String mensaje = "[" + new Date() + "] Tu saldo actual es de $" +
					propietario.getSaldoActual() + ". Te recomendamos hacer una recarga.";
			enviarNotificacion(propietario, mensaje);
		}
	}

	private void procesarNotificacionCambioEstado(Propietario propietario) {
		if (propietario.recibeNotificaciones() != null && propietario.recibeNotificaciones()) {
			String mensaje = "[" + new Date() + "] Se ha cambiado tu estado en el sistema. Tu estado actual es " +
					propietario.getNombreEstado();
			enviarNotificacion(propietario, mensaje);
		}
	}

	// TODO: arreglar mensaje para que devuelva puesto y matricula

	private void procesarNotificacionTransito(Propietario propietario) {
		if (propietario.recibeNotificaciones() != null &&
				propietario.recibeNotificaciones() &&
				!propietario.esPenalizado()) {

			String mensaje = "[" + new Date() + "] Has realizado un tránsito exitosamente. Saldo restante: $" +
					propietario.getSaldoActual();
			/*
			 * [Fecha y hora de la notificación] + “Pasaste por el puesto “ + número de
			 * puesto + “con el vehículo” + número de matrícula.
			 */
			enviarNotificacion(propietario, mensaje);
		}
	}

	public void enviarNotificacion(Propietario propietario, String mensaje) {
		Notificacion notificacion = new Notificacion(propietario, mensaje, new Date());
		notificaciones.add(notificacion);
		propietario.getNotificaciones().add(notificacion);

		// Log para debugging
		System.out.println("📧 Notificación enviada a " + propietario.getNombre() + ": " + mensaje);
	}

}
