package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.dominio.propietario.EstadoPropietario;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dtos.PropietarioDTO;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import obligatorio_da_310665_336194.dominio.usuario.Administrador;

import obligatorio_da_310665_336194.utils.Respuesta;

@RestController
@RequestMapping("/cambiarEstado")
@Scope("session")
public class ControladorEstadoPropietario {

	Fachada fachada = Fachada.getInstancia();
	private List<EstadoPropietario> estados;

	@GetMapping("/vistaConectada")
	public List<Respuesta> inicializarVista(@SessionAttribute(name = "ADMINISTRADOR_STATE_KEY", required = false) Administrador admin) {
		if (admin == null) {
			return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
		}
		estados = fachada.getEstados();
		return estados();
	}

	@PostMapping("/buscarPropietarioPorCedula")
	public List<Respuesta> buscarPropietarioPorCedula(@RequestParam String cedula) throws Exception {
		Propietario propietario = fachada.buscarPropietarioPorCedula(cedula);
		PropietarioDTO dto = new PropietarioDTO(propietario);
		return Respuesta.lista(new Respuesta("propietario", dto));
	}

	@PostMapping("/cambiarEstado")
	public List<Respuesta> cambiarEstado(@RequestParam String cedula, @RequestParam int posNuevoEstado)
			throws Exception {
		EstadoPropietario nuevoEstado = estados.get(posNuevoEstado);
		Propietario propietario = fachada.cambiarEstado(cedula, nuevoEstado);
		PropietarioDTO dto = new PropietarioDTO(propietario);
		return Respuesta.lista(
				new Respuesta("mensaje", "Estado cambiado exitosamente"),
				new Respuesta("propietario", dto));
	}

	public List<Respuesta> estados() {
		List<String> nombresEstados = new ArrayList<>();
		for (EstadoPropietario estado : estados) {
			nombresEstados.add(estado.getNombreEstado());
		}
		return Respuesta.lista(new Respuesta("estados", nombresEstados));
	}
}
