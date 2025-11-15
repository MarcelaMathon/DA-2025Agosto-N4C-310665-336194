package obligatorio_da_310665_336194.servicios;

import java.util.ArrayList;
import java.util.Collection;

import obligatorio_da_310665_336194.dominio.propietario.Propietario;
import obligatorio_da_310665_336194.dominio.vehiculo.Vehiculo;

import java.util.List;

public class ServicioVehiculos {

	private Collection<Vehiculo> vehiculos;

	public ServicioVehiculos() {
		this.vehiculos = new ArrayList<>();
	}

	public void agregar(Vehiculo vehiculo) {
		if (vehiculo != null) {
			this.vehiculos.add(vehiculo);
		}
	}

	public Vehiculo getVehiculo(String matricula) {
		for (Vehiculo v : vehiculos) {
			if (v.getMatricula().equals(matricula)) {
				return v;
			}
		}
		return null;
	}

	public Propietario getPropietario(String matricula) {
		return getVehiculo(matricula).getPropietario();
	}

	public List<Vehiculo> getVehiculosPropietario(Propietario propietario) {
		return null;
	}

}
