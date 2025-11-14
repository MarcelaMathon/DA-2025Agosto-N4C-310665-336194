package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.utils.Respuesta;
import java.util.List;

import obligatorio_da_310665_336194.dominio.bonificacion.TipoBonificacion;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

public class ControladorAsignarBonificacion {

	Fachada fachada = Fachada.getInstancia();

	public List<Respuesta> asignarBonificacion(Propietario propietario, Puesto puesto,
			TipoBonificacion tipoBonificacion) {

		return Respuesta.lista(new Respuesta("asignarBonificacion",
				fachada.asignarBonificacion(propietario, puesto, tipoBonificacion)));
	}

}
