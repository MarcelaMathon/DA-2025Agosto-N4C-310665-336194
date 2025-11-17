package obligatorio_da_310665_336194.controladores;

import java.util.List;
import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import obligatorio_da_310665_336194.utils.Respuesta;
import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.notificacion.Notificacion;
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
public class ControladorTableroPropietario {

	private Fachada fachada = Fachada.getInstancia();

	@PostMapping("/inicializar")
	public List<Respuesta> inicializarTablero(@RequestParam String cedula) throws PeajesExceptions {
		Propietario propietario = fachada.buscarPropietarioPorCedula(cedula);
		
		PropietarioTableroDTO propietarioDTO = new PropietarioTableroDTO(
				propietario.getNombre(),
				propietario.getNombreEstado(), 
				propietario.getSaldoActual()
		);
		
		List<AsignacionDeBonificacion> bonificacionesOrig = fachada.obtenerBonificacionesPropietario(propietario);
		List<BonificacionTableroDTO> bonificacionesDTO = new ArrayList<>();
		for (AsignacionDeBonificacion b : bonificacionesOrig) {
			bonificacionesDTO.add(BonificacionTableroDTO.desde(b));
		}
		
		// Convertir tránsitos a DTOs
		List<Transito> transitosOrig = fachada.getTransitosPropietario(propietario);
		List<TransitoTableroDTO> transitosDTO = new ArrayList<>();
		for (Transito t : transitosOrig) {
			transitosDTO.add(TransitoTableroDTO.desde(t));
		}
		
		// Convertir vehículos a DTOs
		List<Vehiculo> vehiculosOrig = fachada.getVehiculosPropietario(propietario);
		List<VehiculoTableroDTO> vehiculosDTO = new ArrayList<>();
		for (Vehiculo v : vehiculosOrig) {
			vehiculosDTO.add(VehiculoTableroDTO.desde(v));
		}
		
		List<NotificacionTableroDTO> notificacionesDTO = new ArrayList<>();
		for (Notificacion n : propietario.getNotificaciones()) {
			notificacionesDTO.add(NotificacionTableroDTO.desde(n));
		}

		return Respuesta.lista(
				new Respuesta("propietario", propietarioDTO), 
				new Respuesta("bonificaciones", bonificacionesDTO),
				new Respuesta("transitos", transitosDTO),
				new Respuesta("vehiculos", vehiculosDTO),
				new Respuesta("notificaciones", notificacionesDTO),
				new Respuesta("cedula", propietario.getCedula())
		);
	}
	
	@PostMapping("/borrarNotificaciones")
	public List<Respuesta> borrarNotificaciones(@RequestParam String cedula) throws PeajesExceptions {
		Propietario propietario = fachada.buscarPropietarioPorCedula(cedula);
		
		fachada.borrarNotificaciones(propietario);
		
		return Respuesta.lista(new Respuesta("notificacionesBorradas", "Notificaciones borradas exitosamente"));
	}

}
