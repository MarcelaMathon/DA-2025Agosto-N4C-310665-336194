package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.utils.Respuesta;

import java.util.List;

import obligatorio_da_310665_336194.dominio.bonificacion.TipoBonificacion;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.puesto.Puesto;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

public class ControladorAsignarBonificacion {

	Fachada fachada = Fachada.getInstancia();

	public List<Respuesta> listarBonificaciones() {
		return Respuesta.lista(new Respuesta("bonificaciones", fachada.getBonificaciones()));
	}

	public List<Respuesta> listarPuestos() {
		return Respuesta.lista(new Respuesta("puestos", fachada.listarPuestos()));
	}

	public List<Respuesta> buscarPropietario(String cedula) {
		try {
			Propietario propietario = fachada.buscarPropietarioPorCedulaConValidacion(cedula);
			return Respuesta.lista(new Respuesta("propietario", propietario));
		} catch (PeajesExceptions e) {
			return Respuesta.lista(new Respuesta("error", e.getMessage()));
		}
	}

	public List<Respuesta> obtenerBonificacionesPropietario(Propietario propietario) {
		return Respuesta.lista(new Respuesta("bonificacionesAsignadas",
				fachada.obtenerBonificacionesPropietario(propietario)));
	}

	public List<Respuesta> asignarBonificacion(Propietario propietario, Puesto puesto,
			TipoBonificacion tipoBonificacion) {
		try {
			return Respuesta.lista(new Respuesta("asignarBonificacion",
					fachada.asignarBonificacion(propietario, puesto, tipoBonificacion)));
		} catch (PeajesExceptions e) {
			return Respuesta.lista(new Respuesta("error", e.getMessage()));
		}
	}

}
