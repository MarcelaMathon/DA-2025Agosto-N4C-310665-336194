package obligatorio_da_310665_336194.servicios;

import java.util.Collection;
import obligatorio_da_310665_336194.dominio.Vehiculo;
import obligatorio_da_310665_336194.dominio.Propietario;
import java.util.List;

public class ServicioVehiculos {

	private Collection<Vehiculo> vehiculos;

	public Vehiculo getVehiculo(String matricula) {
		for (Vehiculo v : vehiculos) {
			if (v.getMatricula().equals(matricula)) {
				return v;
			}
		}
		return null;
	}

	public Propietario getPropietario(String matricula) {
		return null;
	}

	public List<Vehiculo> getVehiculosPropietario(Propietario propietario) {
		return null;
	}

}
