package obligatorio_da_310665_336194.dominio.vehiculo;

import lombok.Getter;

public class CategoríaVehiculo {

	@Getter
	private String nombre;

	public CategoríaVehiculo(String nombre) {
		this.nombre = nombre;
	}

}
