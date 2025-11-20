package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.dominio.puesto.Tarifa;
import obligatorio_da_310665_336194.dominio.transito.Transito;
import obligatorio_da_310665_336194.dtos.PuestoDTO;
import obligatorio_da_310665_336194.dtos.TarifaDTO;
import obligatorio_da_310665_336194.dtos.TransitoDTO;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;
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

import java.util.Date;

@RestController
@RequestMapping("/emularTransito")
@Scope("session")
public class ControladorEmularTransito {

	Fachada fachada = Fachada.getInstancia();
	private List<Puesto> puestos;

	@GetMapping("/vistaConectada")
	public List<Respuesta> inicializarVista(@SessionAttribute(name = "ADMINISTRADOR_STATE_KEY", required = false) Administrador admin) {
		if (admin == null) {
			return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
		}
		return listarPuestos();
	}

	public List<Respuesta> listarPuestos() {
		puestos = new ArrayList<>(fachada.listarPuestos());
		List<PuestoDTO> puestosDTO = new ArrayList<>();
		for (Puesto puesto : puestos) {
			puestosDTO.add(new PuestoDTO(puesto));
		}
		return Respuesta.lista(new Respuesta("puestos", puestosDTO));
	}

	@GetMapping("/tarifasDePuesto")
	public List<Respuesta> tarifasDePuesto(@RequestParam int posPuesto) {
		Puesto puesto = puestos.get(posPuesto);
		List<TarifaDTO> tarifasDTO = new ArrayList<>();
		for (Tarifa tarifa : fachada.tarifasDePuesto(puesto)) {
			tarifasDTO.add(new TarifaDTO(tarifa));
		}
		return Respuesta.lista(new Respuesta("tarifas", tarifasDTO));
	}

	@PostMapping("/emularTransito")
	public List<Respuesta> emularTransito(@RequestParam int posPuesto, @RequestParam String matricula,
			@RequestParam Long fechaHora) {
		try {
			Puesto puesto = puestos.get(posPuesto);
			Date fecha = new Date(fechaHora);
			Transito transito = fachada.emularTransito(puesto, matricula, fecha);
			Propietario propietario = fachada.getPropietario(matricula);
			return Respuesta.lista(transitoDto(transito, propietario));
		} catch (PeajesExceptions e) {
			return Respuesta.lista(new Respuesta("error", e.getMessage()));
		}
	}

	private Respuesta transitoDto(Transito transito, Propietario propietario) {
		return new Respuesta("transito", new TransitoDTO(transito, propietario));
	}

}
