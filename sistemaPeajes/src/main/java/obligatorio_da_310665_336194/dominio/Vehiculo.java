package obligatorio_da_310665_336194.dominio;

import java.util.List;

import lombok.Getter;

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

}
