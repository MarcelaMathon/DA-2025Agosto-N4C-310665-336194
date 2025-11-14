package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.servicios.Fachada;
import obligatorio_da_310665_336194.utils.Respuesta;
import java.util.List;
import obligatorio_da_310665_336194.dominio.Propietario;
import obligatorio_da_310665_336194.dominio.Puesto;
import obligatorio_da_310665_336194.dominio.TipoBonificacion;

public class ControladorAsignarBonificacion {

	Fachada fachada = Fachada.getInstancia();

	public List<Respuesta> asignarBonificacion(Propietario propietario, Puesto puesto,
			TipoBonificacion tipoBonificacion) {

		return Respuesta.lista(new Respuesta("asignarBonificacion",
				fachada.asignarBonificacion(propietario, puesto, tipoBonificacion)));
	}

}
