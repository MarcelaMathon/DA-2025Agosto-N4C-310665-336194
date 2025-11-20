package obligatorio_da_310665_336194.controladores;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import obligatorio_da_310665_336194.utils.ConexionNavegador;
import obligatorio_da_310665_336194.utils.Respuesta;
import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.notificacion.Notificacion;
import obligatorio_da_310665_336194.dominio.observador.ObservableAbstracto;
import obligatorio_da_310665_336194.dominio.observador.Observador;
import obligatorio_da_310665_336194.dominio.propietario.EventoPropietario;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.transito.Transito;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;
import obligatorio_da_310665_336194.dtos.PropietarioTableroDTO;
import obligatorio_da_310665_336194.dtos.VehiculoTableroDTO;
import obligatorio_da_310665_336194.dtos.TransitoTableroDTO;
import obligatorio_da_310665_336194.dtos.BonificacionTableroDTO;
import obligatorio_da_310665_336194.dtos.NotificacionTableroDTO;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

@RestController
@RequestMapping("/tableroPropietario")
@Scope("session")
public class ControladorTableroPropietario implements Observador {

	private Fachada fachada = Fachada.getInstancia();
	private Propietario propietarioActual;

	private final ConexionNavegador conexionNavegador;

	public ControladorTableroPropietario(@Autowired ConexionNavegador conexionNavegador) {
		// La conexión se inicializa cuando se registra el SSE
		this.conexionNavegador = conexionNavegador;
	}

	@GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter registrarSSE() {
		// Establecer la conexión SSE con el navegador
		conexionNavegador.conectarSSE();
		return conexionNavegador.getConexionSSE();
	}

	@GetMapping("/inicializar")
	public List<Respuesta> inicializarTablero(
			@SessionAttribute(name = "PROPIETARIO_STATE_KEY", required = false) Propietario propietario)
			throws PeajesExceptions {
		// Verificar si hay un propietario en la sesión
		if (propietario == null) {
			return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
		}

		propietarioActual = propietario;

		// Registrar este controlador como observador del propietario
		propietarioActual.agregar(this);

		PropietarioTableroDTO propietarioDTO = new PropietarioTableroDTO(
				propietarioActual.getNombre(),
				propietarioActual.getNombreEstado(),
				propietarioActual.getSaldoActual());

		List<AsignacionDeBonificacion> bonificacionesOrig = fachada.obtenerBonificacionesPropietario(propietarioActual);
		List<BonificacionTableroDTO> bonificacionesDTO = new ArrayList<>();
		for (AsignacionDeBonificacion b : bonificacionesOrig) {
			bonificacionesDTO.add(BonificacionTableroDTO.desde(b));
		}

		// Convertir tránsitos a DTOs
		List<Transito> transitosOrig = fachada.getTransitosPropietario(propietarioActual);
		List<TransitoTableroDTO> transitosDTO = new ArrayList<>();
		for (Transito t : transitosOrig) {
			transitosDTO.add(TransitoTableroDTO.desde(t));
		}

		// Convertir vehículos a DTOs
		List<Vehiculo> vehiculosOrig = fachada.getVehiculosPropietario(propietarioActual);
		List<VehiculoTableroDTO> vehiculosDTO = new ArrayList<>();
		for (Vehiculo v : vehiculosOrig) {
			vehiculosDTO.add(VehiculoTableroDTO.desde(v));
		}

		List<NotificacionTableroDTO> notificacionesDTO = new ArrayList<>();
		for (Notificacion n : propietarioActual.getNotificaciones()) {
			notificacionesDTO.add(NotificacionTableroDTO.desde(n));
		}

		return Respuesta.lista(
				new Respuesta("propietario", propietarioDTO),
				new Respuesta("bonificaciones", bonificacionesDTO),
				new Respuesta("transitos", transitosDTO),
				new Respuesta("vehiculos", vehiculosDTO),
				new Respuesta("notificaciones", notificacionesDTO),
				new Respuesta("cedula", propietarioActual.getCedula()));
	}

	@PostMapping("/borrarNotificaciones")
	public List<Respuesta> borrarNotificaciones(
			@SessionAttribute(name = "PROPIETARIO_STATE_KEY", required = false) Propietario propietario)
			throws PeajesExceptions {
		if (propietario == null) {
			return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
		}

		fachada.borrarNotificaciones(propietario);

		return Respuesta.lista(new Respuesta("notificacionesBorradas", "Notificaciones borradas exitosamente"));
	}

	@Override
	public void actualizar(ObservableAbstracto origen, Object evento) {
		if (!(evento instanceof EventoPropietario)) {
			return;
		}

		EventoPropietario eventoPropietario = (EventoPropietario) evento;
		Propietario propietario = eventoPropietario.getPropietario();

		if (!propietario.equals(propietarioActual)) {
			return;
		}

		Propietario.EventosPropietario tipoEvento = eventoPropietario.getTipo();

		switch (tipoEvento) {
			case TRANSITO_REALIZADO:
				// Enviar tránsitos, propietario y notificaciones juntos
				conexionNavegador.enviarJSON(Respuesta.lista(transitos(), propietario(), notificaciones()));
				break;
			case SALDO_BAJO:
				// Enviar propietario y notificaciones juntos
				conexionNavegador.enviarJSON(Respuesta.lista(propietario(), notificaciones()));
				break;
			case ESTADO_CAMBIADO:
				// Enviar propietario y notificaciones juntos
				conexionNavegador.enviarJSON(Respuesta.lista(propietario(), notificaciones()));
				break;
			case BONIFICACION_ASIGNADA:
				// Enviar bonificaciones y notificaciones juntos
				conexionNavegador.enviarJSON(Respuesta.lista(bonificaciones(), notificaciones()));
				break;
		}
	}

	private Respuesta propietario() {
		PropietarioTableroDTO propietarioDTO = new PropietarioTableroDTO(
				propietarioActual.getNombre(),
				propietarioActual.getNombreEstado(),
				propietarioActual.getSaldoActual());
		return new Respuesta("propietario", propietarioDTO);
	}

	private Respuesta transitos() {
		List<Transito> transitosOrig = fachada.getTransitosPropietario(propietarioActual);
		List<TransitoTableroDTO> transitosDTO = new ArrayList<>();
		for (Transito t : transitosOrig) {
			transitosDTO.add(TransitoTableroDTO.desde(t));
		}
		return new Respuesta("transitos", transitosDTO);
	}

	private Respuesta notificaciones() {
		List<NotificacionTableroDTO> notificacionesDTO = new ArrayList<>();
		for (Notificacion n : propietarioActual.getNotificaciones()) {
			notificacionesDTO.add(NotificacionTableroDTO.desde(n));
		}
		return new Respuesta("notificaciones", notificacionesDTO);
	}

	private Respuesta bonificaciones() {
		List<AsignacionDeBonificacion> bonificacionesOrig = fachada.obtenerBonificacionesPropietario(propietarioActual);
		List<BonificacionTableroDTO> bonificacionesDTO = new ArrayList<>();
		for (AsignacionDeBonificacion b : bonificacionesOrig) {
			bonificacionesDTO.add(BonificacionTableroDTO.desde(b));
		}
		return new Respuesta("bonificaciones", bonificacionesDTO);
	}

}
