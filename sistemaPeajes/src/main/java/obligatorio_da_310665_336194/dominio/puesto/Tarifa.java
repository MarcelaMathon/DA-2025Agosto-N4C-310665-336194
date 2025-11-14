package obligatorio_da_310665_336194.dominio.puesto;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.vehiculo.CategoríaVehiculo;

public class Tarifa {

	@Getter
	private Double monto;

	@Getter
	private CategoríaVehiculo categoríaVehiculo;

	@Getter
	private Puesto puesto;

}
