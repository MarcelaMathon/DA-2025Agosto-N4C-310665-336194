package obligatorio_da_310665_336194.dominio.puesto;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.vehiculo.CategoríaVehiculo;
import obligatorio_da_310665_336194.excepciones.PeajesExceptions;

public class Tarifa {

	@Getter
	private Double monto;

	@Getter
	private CategoríaVehiculo categoríaVehiculo;

	@Getter
	private Puesto puesto;

	public Tarifa(Puesto puesto, CategoríaVehiculo categoríaVehiculo, Double monto) {
		this.puesto = puesto;
		this.categoríaVehiculo = categoríaVehiculo;
		this.monto = monto;
	}

}
