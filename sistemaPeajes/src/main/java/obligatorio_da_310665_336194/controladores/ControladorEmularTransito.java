package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.servicios.Fachada;
import java.util.List;
import obligatorio_da_310665_336194.dominio.Puesto;
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
		return Respuesta.lista(new Respuesta("transito", fachada.emularTransito(puesto, matricula, fechaHora)));
	}

}
