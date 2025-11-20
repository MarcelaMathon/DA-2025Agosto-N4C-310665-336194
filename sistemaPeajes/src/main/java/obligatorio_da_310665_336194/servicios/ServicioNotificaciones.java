package obligatorio_da_310665_336194.servicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import obligatorio_da_310665_336194.dominio.notificacion.Notificacion;
import obligatorio_da_310665_336194.dominio.observador.ObservableAbstracto;
import obligatorio_da_310665_336194.dominio.observador.Observador;
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
		notificacionesPropietario.sort(Comparator.comparing(Notificacion::getFechaHora).reversed());
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
					procesarNotificacionTransito(propietario, eventoPropietario);
				}
				break;
			case BONIFICACION_ASIGNADA:
				// No se envía notificación al asignar bonificación
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
		if (propietario != null) {
			String mensaje = "[" + new Date() + "] Se ha cambiado tu estado en el sistema. Tu estado actual es " +
					propietario.getNombreEstado();
			enviarNotificacion(propietario, mensaje);
		}
	}

	private void procesarNotificacionTransito(Propietario propietario, EventoPropietario eventoPropietario) {
		if (propietario.recibeNotificaciones() != null &&
				propietario.recibeNotificaciones() &&
				!propietario.esPenalizado()) {

			if (eventoPropietario.getTransito() != null) {
				var transito = eventoPropietario.getTransito();
				String mensaje = "[" + transito.getFechaHora() + "] Pasaste por el puesto " +
						transito.getPuesto().getNombre() + " con el vehículo " +
						transito.getVehiculo().getMatricula();
				enviarNotificacion(propietario, mensaje);
			}
		}
	}

	public void enviarNotificacion(Propietario propietario, String mensaje) {
		Notificacion notificacion = new Notificacion(propietario, mensaje, new Date());
		notificaciones.add(notificacion);
		propietario.getNotificaciones().add(notificacion);

	}

}
