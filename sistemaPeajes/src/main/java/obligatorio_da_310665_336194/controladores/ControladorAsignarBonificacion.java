package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.dtos.BonificacionAsignadaDTO;
import obligatorio_da_310665_336194.dtos.PropietarioDTO;
import obligatorio_da_310665_336194.dtos.PuestoDTO;
import obligatorio_da_310665_336194.utils.Respuesta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import obligatorio_da_310665_336194.dominio.bonificacion.TipoBonificacion;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

@RestController
@RequestMapping("/asignarBonificacion")
@Scope("session")
public class ControladorAsignarBonificacion {

	Fachada fachada = Fachada.getInstancia();
	private List<Puesto> puestos;

	@PostMapping("/vistaConectada")
	public List<Respuesta> inicializarVista() {
		// Cargar datos iniciales
		return Respuesta.lista(bonificaciones(), puestos());
	}

	@PostMapping("/buscarPropietario")
	public List<Respuesta> buscarPropietario(@RequestParam String cedula) throws PeajesExceptions {
		if (cedula.isBlank())
			throw new PeajesExceptions("Debe ingresar una cédula");

		Propietario propietario = fachada.buscarPropietarioPorCedula(cedula);
		PropietarioDTO dto = new PropietarioDTO(propietario);

		// Obtener bonificaciones asignadas
		List<BonificacionAsignadaDTO> bonificacionesDTO = new ArrayList<>();
		for (AsignacionDeBonificacion asignacion : fachada.obtenerBonificacionesPropietario(propietario)) {
			bonificacionesDTO.add(new BonificacionAsignadaDTO(asignacion));
		}

		return Respuesta.lista(
				new Respuesta("propietario", dto),
				new Respuesta("bonificacionesAsignadas", bonificacionesDTO));
	}

	@PostMapping("/asignar")
	public List<Respuesta> asignarBonificacion(
			@RequestParam String cedula,
			@RequestParam int posPuesto,
			@RequestParam String nombreBonificacion) throws PeajesExceptions {

		if (posPuesto < 0)
			throw new PeajesExceptions("Debe especificar un puesto");
		if (nombreBonificacion.isBlank())
			throw new PeajesExceptions("Debe especificar una bonificación");

		Propietario propietario = fachada.buscarPropietarioPorCedula(cedula);
		Puesto puesto = puestos.get(posPuesto);
		TipoBonificacion tipoBonificacion = fachada.crearTipoBonificacion(nombreBonificacion);

		fachada.asignarBonificacion(propietario, puesto, tipoBonificacion);

		List<BonificacionAsignadaDTO> bonificacionesDTO = new ArrayList<>();
		for (AsignacionDeBonificacion asignacion : fachada.obtenerBonificacionesPropietario(propietario)) {
			bonificacionesDTO.add(new BonificacionAsignadaDTO(asignacion));
		}

		return Respuesta.lista(
				new Respuesta("mensaje", "Bonificación asignada correctamente"),
				new Respuesta("bonificacionesAsignadas", bonificacionesDTO));
	}

	private Respuesta bonificaciones() {
		List<String> nombresBonificaciones = fachada.obtenerNombresTiposBonificacion();
		return new Respuesta("bonificaciones", nombresBonificaciones);
	}

	private Respuesta puestos() {
		puestos = new ArrayList<>(fachada.listarPuestos());
		List<PuestoDTO> puestosDTO = new ArrayList<>();
		for (Puesto puesto : puestos) {
			puestosDTO.add(new PuestoDTO(puesto));
		}
		return new Respuesta("puestos", puestosDTO);
	}

}
