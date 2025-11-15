package obligatorio_da_310665_336194.dominio.vehiculo;

import java.util.List;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.transito.Transito;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;

public class Vehiculo {

	@Getter
	private String matricula;

	@Getter
	private String modelo;

	@Getter
	private String color;

	@Getter
	private Propietario propietario;

	@Getter
	private CategoríaVehiculo categoría;

	@Getter
	private List<Transito> transitos;

	public CategoríaVehiculo getCategoria() {
		return categoría;
	}

	public Propietario getPropietario(String matricula) {
		return null;
	}

	public static void validarExistencia(Vehiculo vehiculo) throws PeajesExceptions {
		if (vehiculo == null) {
			throw new PeajesExceptions("No existe el vehículo");
		}
	}

}
