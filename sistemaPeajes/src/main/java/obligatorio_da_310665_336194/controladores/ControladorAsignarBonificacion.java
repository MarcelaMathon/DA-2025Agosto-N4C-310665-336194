package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.dtos.BonificacionAsignadaDTO;
import obligatorio_da_310665_336194.dtos.PropietarioDTO;
import obligatorio_da_310665_336194.dtos.PuestoDTO;
import obligatorio_da_310665_336194.utils.Respuesta;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import obligatorio_da_310665_336194.dominio.usuario.Administrador;

import obligatorio_da_310665_336194.dominio.bonificacion.TipoBonificacion;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

@RestController
@RequestMapping("/asignarBonificacion")
@Scope("session")
public class ControladorAsignarBonificacion {

	private static final String PROPIETARIO_KEY = "asignarBonificacion_propietario";

	Fachada fachada = Fachada.getInstancia();
	private List<Puesto> puestos;

	@GetMapping("/vistaConectada")
	public List<Respuesta> inicializarVista(
			@SessionAttribute(name = "ADMINISTRADOR_STATE_KEY", required = false) Administrador admin,
			HttpSession sesion) {
		if (admin == null) {
			return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
		}
		// Cargar datos iniciales
		List<Respuesta> respuestas = new ArrayList<>();
		respuestas.add(bonificaciones());
		respuestas.add(puestos());

		// Si hay un propietario en sesión, devolverlo
		Propietario propietario = (Propietario) sesion.getAttribute(PROPIETARIO_KEY);
		if (propietario != null) {
			PropietarioDTO dto = new PropietarioDTO(propietario);
			respuestas.add(new Respuesta("propietario", dto));

			List<BonificacionAsignadaDTO> bonificacionesDTO = new ArrayList<>();
			for (AsignacionDeBonificacion asignacion : fachada.obtenerBonificacionesPropietario(propietario)) {
				bonificacionesDTO.add(new BonificacionAsignadaDTO(asignacion));
			}
			respuestas.add(new Respuesta("bonificacionesAsignadas", bonificacionesDTO));
		}

		return respuestas;
	}

	@PostMapping("/buscarPropietario")
	public List<Respuesta> buscarPropietario(@RequestParam String cedula, HttpSession sesion) throws PeajesExceptions {
		if (cedula.isBlank())
			throw new PeajesExceptions("Debe ingresar una cédula");
		sesion.setAttribute(PROPIETARIO_KEY, null); // Limpiar propietario previo
		Propietario propietario = fachada.buscarPropietarioPorCedula(cedula);
		sesion.setAttribute(PROPIETARIO_KEY, propietario); // Guardar en sesión
		PropietarioDTO dto = new PropietarioDTO(propietario);

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
