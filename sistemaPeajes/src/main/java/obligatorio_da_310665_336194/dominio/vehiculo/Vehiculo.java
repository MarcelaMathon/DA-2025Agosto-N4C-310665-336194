package obligatorio_da_310665_336194.dominio.vehiculo;

import java.util.ArrayList;
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

	public Vehiculo(String matricula, String modelo, Propietario propietario, String color,
			CategoríaVehiculo categoría) {
		this.matricula = matricula;
		this.modelo = modelo;
		this.propietario = propietario;
		this.color = color;
		this.categoría = categoría;
		this.transitos = new ArrayList<>();
		if (propietario != null) {
			propietario.getVehículos().add(this);
		}
	}

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

	public int getCantidadTransitos() {
		return transitos != null ? transitos.size() : 0;
	}

	public Double getMontoTotalGastado() {
		if (transitos == null) {
			return 0.0;
		}

		Double total = 0.0;
		for (Transito transito : transitos) {
			total += transito.getCosto() != null ? transito.getCosto() : 0.0;
		}
		return total;
	}

}
