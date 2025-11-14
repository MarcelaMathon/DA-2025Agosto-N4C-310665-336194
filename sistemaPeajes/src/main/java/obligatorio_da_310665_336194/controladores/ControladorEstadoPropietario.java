package obligatorio_da_310665_336194.controladores;

import obligatorio_da_310665_336194.dominio.propietario.EstadoPropietario;
import obligatorio_da_310665_336194.servicios.fachada.Fachada;

import java.util.List;

import obligatorio_da_310665_336194.utils.Respuesta;

public class ControladorEstadoPropietario {

	Fachada fachada = Fachada.getInstancia();

	public List<Respuesta> buscarPropietarioPorCedula(String cedula) {
		return Respuesta.lista(new Respuesta("buscarPropietarioPorCedula",
				fachada.buscarPropietarioPorCedula(cedula)));
	}

	public List<Respuesta> getEstados() {
		return Respuesta.lista(new Respuesta("getEstados", fachada.getEstados()));
	}

	public List<Respuesta> cambiarEstado(String cedula, EstadoPropietario nuevoEstado) {
		return Respuesta.lista(new Respuesta("cambiarEstado", fachada.cambiarEstado(cedula, nuevoEstado)));
	}

}
