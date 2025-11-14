package obligatorio_da_310665_336194.dominio.puesto;

import java.util.List;

import lombok.Getter;
import obligatorio_da_310665_336194.dominio.bonificacion.AsignacionDeBonificacion;
import obligatorio_da_310665_336194.dominio.transito.Transito;

public class Puesto {

	@Getter
	private String nombre;
	@Getter
	private String direccion;
	@Getter
	private AsignacionDeBonificacion bonificacionAsignada;
	@Getter
	private List<Transito> transitos;
	@Getter
	private List<Tarifa> tarifas;

	public List<Tarifa> tarifasDePuesto(Puesto puesto) {
		return null;
	}

}
