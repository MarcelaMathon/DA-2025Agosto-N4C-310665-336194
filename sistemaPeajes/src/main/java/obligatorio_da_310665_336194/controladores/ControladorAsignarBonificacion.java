package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.dtos.BonificacionAsignadaDTO;
import obligatorio_da_310665_336194.dtos.PropietarioDTO;
import obligatorio_da_310665_336194.dtos.PuestoDTO;
import obligatorio_da_310665_336194.utils.Respuesta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.bonificacion.Exonerado;
import obligatorio_da_310665_336194.dominio.bonificacion.Frecuente;
import obligatorio_da_310665_336194.dominio.bonificacion.TipoBonificacion;
import obligatorio_da_310665_336194.dominio.bonificacion.Trabajador;
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

		Propietario propietario = fachada.buscarPropietarioPorCedulaConValidacion(cedula);
		PropietarioDTO dto = new PropietarioDTO(propietario);

		// Obtener bonificaciones asignadas
		List<BonificacionAsignadaDTO> bonificacionesDTO = fachada.obtenerBonificacionesPropietario(propietario)
				.stream()
				.map(BonificacionAsignadaDTO::new)
				.collect(Collectors.toList());

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

		// Buscar propietario
		Propietario propietario = fachada.buscarPropietarioPorCedulaConValidacion(cedula);

		// Obtener puesto por posición
		Puesto puesto = puestos.get(posPuesto);

		// Crear tipo de bonificación
		TipoBonificacion tipoBonificacion = crearTipoBonificacion(nombreBonificacion);

		// Asignar bonificación
		AsignacionDeBonificacion asignacion = fachada.asignarBonificacion(propietario, puesto, tipoBonificacion);
		BonificacionAsignadaDTO dto = new BonificacionAsignadaDTO(asignacion);

		// Obtener bonificaciones actualizadas
		List<BonificacionAsignadaDTO> bonificacionesDTO = fachada.obtenerBonificacionesPropietario(propietario)
				.stream()
				.map(BonificacionAsignadaDTO::new)
				.collect(Collectors.toList());

		return Respuesta.lista(
				new Respuesta("mensaje", "Bonificación asignada correctamente"),
				new Respuesta("bonificacionesAsignadas", bonificacionesDTO));
	}

	private Respuesta bonificaciones() {
		List<String> nombresBonificaciones = List.of("Exonerado", "Frecuente", "Trabajador");
		return new Respuesta("bonificaciones", nombresBonificaciones);
	}

	private Respuesta puestos() {
		puestos = new ArrayList<>(fachada.listarPuestos());
		List<PuestoDTO> puestosDTO = puestos.stream()
				.map(PuestoDTO::new)
				.collect(Collectors.toList());
		return new Respuesta("puestos", puestosDTO);
	}

	private TipoBonificacion crearTipoBonificacion(String nombre) {
		switch (nombre) {
			case "Exonerado":
				return new Exonerado();
			case "Frecuente":
				return new Frecuente();
			case "Trabajador":
				return new Trabajador();
			default:
				return null;
		}
	}

}
