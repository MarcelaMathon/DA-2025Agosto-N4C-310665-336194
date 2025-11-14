package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.dominio.transito.Transito;
import obligatorio_da_310665_336194.dtos.TransitoDTO;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

import java.util.List;

import obligatorio_da_310665_336194.utils.Respuesta;

import java.util.Date;

public class ControladorEmularTransito {

	Fachada fachada = Fachada.getInstancia();

	public List<Respuesta> listarPuestos() {
		return Respuesta.lista(new Respuesta("puestos", fachada.listarPuestos()));
	}

	public List<Respuesta> tarifasDePuesto(Puesto puesto) {
		return Respuesta.lista(new Respuesta("tarifas", fachada.tarifasDePuesto(puesto)));
	}

	public List<Respuesta> emularTransito(Puesto puesto, String matricula, Date fechaHora) {
		try {
			Transito transito = fachada.emularTransito(puesto, matricula, fechaHora);
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
